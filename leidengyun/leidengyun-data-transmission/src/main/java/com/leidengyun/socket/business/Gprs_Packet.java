package com.leidengyun.socket.business;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.leidengyun.common.StringUtis;
import com.leidengyun.model.DevDataApp;
import com.leidengyun.model.Instrument;
import com.leidengyun.socket.JdbcDao;
import com.leidengyun.socket.util.JdbcUtis;
import com.leidengyun.socket.util.newStringUtil;

/**
 * 设备上传解析数据包
 * @author swl
 * 20180401
 */
public class Gprs_Packet {
	
	
	public static Logger logger = Logger.getLogger(Gprs_Packet.class);
	
	public static int Log_DevId;  //设备编号
	public static int Log_AreaProv; //设备所在省
	public static int Log_AreaCity; //设备所在市
	public static int Log_DevType; //设备类型
	public static int[] Log_TimeStamp = new int[6];  //采集日期数组
	public static String TimeDate; //日期
	public static double Log_Volt;  //电池电压
	public static int dev = 0;  //传送状态
	
	
	public static int Gprs_PacketParse(byte[] Gprs_RxBuf, int Gprs_RecCnt) {
		try {
		
			String[] toHexStrings = newStringUtil.bytesToHexStrings(Gprs_RxBuf);
			
			
			//logger.info(Arrays.toString(toHexStrings).toString());
			String len1 = newStringUtil.addZeroForNum(toHexStrings[12], 2);
			String len2 = newStringUtil.addZeroForNum(toHexStrings[13], 2);
			String data_length = len1 + len2;
			int data_len = newStringUtil.hexStringToAlgorism(data_length);
			
			
			logger.info("数据域长度：" + data_len + "接收长度：" + Gprs_RecCnt);
			if (data_len + 22 != Gprs_RecCnt) {
				return 1;
			}
			//数据库连接
			Connection con=null;
			try {con = JdbcUtis.getJdbcConn();
			} catch (SQLException e1) {e1.printStackTrace();}
			JdbcDao dao = new JdbcDao();
			
			Log_AreaProv = Gprs_RxBuf[0];
			Log_AreaCity = Gprs_RxBuf[1];
			Log_DevId = 10000000 * Log_AreaProv + 100000 * Log_AreaCity + (Gprs_RxBuf[2] << 8 | Gprs_RxBuf[3]);
			logger.info("Log_DevId：" + Log_DevId + "正在解析......");

			String devCode = newStringUtil.addZeroForNum(toHexStrings[4], 2) +
					newStringUtil.addZeroForNum(toHexStrings[5], 2);
			for (int i = 0; i < 6; i++) {
				Log_TimeStamp[i] = Gprs_RxBuf[(6 + i)];
			}
			int size = Log_TimeStamp.length;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				int _time = Log_TimeStamp[i];
				String time = newStringUtil.addZeroForNum(_time+"", 2);
				if (i < 2) {
					sb.append(time);
					sb.append("-");
				} else if (i == 2) {
					sb.append(time);
					sb.append(" ");
				} else {
					sb.append(time);
					sb.append(":");
				}
			}
			TimeDate = sb.toString().substring(0, sb.length() - 1);

			String t5 = toHexStrings[(14 + data_len)];
			String t6 = toHexStrings[(14 + data_len + 1)];
			String volt = t5 + t6;
			Log_Volt = Double.valueOf(newStringUtil.hexStringToAlgorism(volt)).doubleValue();
			Log_Volt /= 100.0;

			int n = 0;
			StringBuilder devTypeAarry = new StringBuilder();
			StringBuilder devValueArray = new StringBuilder();
			StringBuilder devNameArray = new StringBuilder();
			
			while (n < data_len) {
				int adrr = Gprs_RxBuf[(14 + n++)];

				int _type = Gprs_RxBuf[(14 + n)] << 8 | Gprs_RxBuf[(14 + n + 1)];

				String type00 = Integer.toHexString(_type);
				String type = newStringUtil.addZeroForNum(type00, 4);

				n += 2;
				String t = newStringUtil.addZeroForNum(toHexStrings[(14 + n)], 2);
				String t1 = newStringUtil.addZeroForNum(toHexStrings[(14 + n + 1)], 2);
				String instr = t + t1;
				double insrt1 = newStringUtil.hexStringToAlgorism(instr);

				n += 2;
				double insrt2 = 0.0;
				
				String type0 = type.substring(0, 2);
				String type1 = type.substring(2, 4);
				Instrument inst = dao.query(con, type0, type1);
			
				if (null != inst) {
					if (Integer.parseInt(inst.getPramNum())== 2) {
						String t2 = newStringUtil.addZeroForNum(toHexStrings[(14 + n)], 2);
						String t3 = newStringUtil.addZeroForNum(toHexStrings[(14 + n + 1)], 2);
						String instr2 = t2 + t3;
						insrt2 = newStringUtil.hexStringToAlgorism(instr2);
						n += 2;
					}
				
					//设备类型集合 地址+“-”+设备类型 多个中间以@符号分隔
	
					if (Integer.parseInt(inst.getPramNum()) == 2) {

						
						
						devTypeAarry.append(adrr + "-" + type );
						devTypeAarry.append("@");
						devTypeAarry.append(adrr + "-" + type );
						devTypeAarry.append("-@");
						

						devValueArray.append(StringUtis.formatZero(insrt1,
								Double.valueOf(inst.getMagNitude())));
						devValueArray.append("@");
						devValueArray.append(StringUtis.formatZero(insrt2,
								Double.valueOf(inst.getMagNitude())));
						devValueArray.append("@");
						
						devNameArray.append("(" + adrr + ")"
								+ inst.getInsName().substring(0, 2) + "温度" + "["
								+ inst.getUnit().toString().split(",")[0] + "]");
						devNameArray.append("@");
						
						devNameArray.append("(" + adrr + ")"
								+ inst.getInsName().toString().substring(0, 2) + "湿度" + "["
								+ inst.getUnit().toString().split(",")[1] + "]");
						devNameArray.append("@");
				
					} else {
						
						
						devTypeAarry.append(adrr + "-" + type );
						devTypeAarry.append("@");
						
						devValueArray.append(StringUtis.formatZero(insrt1,
						Double.valueOf(inst.getMagNitude())));
						devValueArray.append("@");
						
						devNameArray.append("(" + adrr + ")"
								+ inst.getInsName() + "["
								+ inst.getUnit().toString().split(",")[0] + "]");
						devNameArray.append("@");
					}
				}
			}
			
			//封装设备主表
			
			DevDataApp app = new DevDataApp();
			app.setDevId(Log_DevId+"");
			app.setDevCode(devCode);
			app.setDevTime(TimeDate);
			app.setDevLength(data_len+"");
			app.setDevData(Arrays.toString(toHexStrings).toString());
			app.setLoVolt(Log_Volt);
			app.setUuid(newStringUtil.getUuid());
			
			app.setDevTypeArray(devTypeAarry.substring(0, devTypeAarry.length() - 1));
			app.setDevValueArray(devValueArray.substring(0, devValueArray.length() - 1));
			app.setDevNameArray(devNameArray.substring(0, devNameArray.length() - 1));
			app.setIfRevice("Y");
			//插入
			dao.insert(con, app);
			logger.info("Log_DevId：" + Log_DevId + "解析完毕!......");
		} catch (Exception e) {
			logger.debug("数据包解析失败!", e);
		}
		return 0;
	}
}
