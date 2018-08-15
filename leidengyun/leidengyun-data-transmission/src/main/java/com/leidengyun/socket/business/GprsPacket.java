package com.leidengyun.socket.business;
import com.leidengyun.common.StringUtis;
import com.leidengyun.model.DevDataApp;
import com.leidengyun.model.Instrument;
import com.leidengyun.socket.JdbcDao;
import com.leidengyun.socket.util.JdbcUtis;
import com.leidengyun.socket.util.newStringUtil;
import com.mysql.jdbc.Connection;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Arrays;
/**
 * 设备上传解析数据包
 * @author swl
 * 20180401
 */
public class GprsPacket {
	public static Logger logger = Logger.getLogger(GprsPacket.class);
	/**
	 *  设备编号
	 */
	public static int Log_DevId;
	/**
	 * 设备所在省
	 */
	public static int Log_AreaProv;
	/**
	 * 设备所在市
	 */
	public static int Log_AreaCity;
	/**
	 * 设备类型
	 */
	public static int Log_DevType;
	/**
	 *  采集日期数组
	 */
	public static int[] Log_TimeStamp = new int[6];
	/**
	 *  日期
	 */
	public static String TimeDate;
	/**
	 *  电池电压
	 */
	public static double Log_Volt;
	/**
	 * 传送状态
	 */
	public static int dev = 0;
	public static int gprsPacketParse(byte[] gprsRxBuf, int gprsRecCnt) {
		try {
			JdbcDao dao = new JdbcDao();
			String[] toHexStrings = newStringUtil.bytesToHexStrings(gprsRxBuf);
			int len=22;
			String len1 = newStringUtil.addZeroForNum(toHexStrings[12], 2);
			String len2 = newStringUtil.addZeroForNum(toHexStrings[13], 2);
			int len3 = newStringUtil.hexStringToAlgorism(len1 + len2);
			logger.info("数据域长度：" + len3 + "接收长度：" + gprsRecCnt);
			if (len3 + len != gprsRecCnt) {
				return 1;
			}
			//数据库连接
			Connection con = null;
			try {con = JdbcUtis.getJdbcConn();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Log_AreaProv = gprsRxBuf[0];
			Log_AreaCity = gprsRxBuf[1];
			Log_DevId = 10000000 * Log_AreaProv + 100000 * Log_AreaCity + (gprsRxBuf[2] << 8 | gprsRxBuf[3]);
			logger.info("Log_DevId：" + Log_DevId + "正在解析......");
			int timeSize=6;
			String devCode = newStringUtil.addZeroForNum(toHexStrings[4], 2) +
					newStringUtil.addZeroForNum(toHexStrings[5], 2);
			for (int i = 0; i < timeSize; i++) {
				Log_TimeStamp[i] = gprsRxBuf[(6 + i)];
			}
			int size = Log_TimeStamp.length;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				int time00 = Log_TimeStamp[i];
				String time = newStringUtil.addZeroForNum(time00+"", 2);
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
			String t5 = toHexStrings[(14 + len3)];
			String t6 = toHexStrings[(14 + len3 + 1)];
			String volt = t5 + t6;
			Log_Volt = Double.valueOf(newStringUtil.hexStringToAlgorism(volt)).doubleValue();
			Log_Volt /= 100.0;
			int n = 0;
			StringBuilder devTypeArray = new StringBuilder();
			StringBuilder devValueArray = new StringBuilder();
			StringBuilder devNameArray = new StringBuilder();
			while (n < len3) {
				int address = gprsRxBuf[(14 + n++)];
				String typeStr = Integer.toHexString(gprsRxBuf[(14 + n)] << 8 | gprsRxBuf[(14 + n + 1)]);
				String type = newStringUtil.addZeroForNum(typeStr, 4);
				n += 2;
				String t = newStringUtil.addZeroForNum(toHexStrings[(14 + n)], 2);
				String t1 = newStringUtil.addZeroForNum(toHexStrings[(14 + n + 1)], 2);
				String t2 = t + t1;
				double val = newStringUtil.hexStringToAlgorism(t2);
				n += 2;
				double val1 = 0.0;
				String type0 = type.substring(0, 2);
				String type1 = type.substring(2, 4);
				Instrument inst = dao.query(con, type0, type1);
				if (null != inst) {
					if (Integer.parseInt(inst.getPramNum())== 2) {
						String t3 = newStringUtil.addZeroForNum(toHexStrings[(14 + n)], 2);
						String t4 = newStringUtil.addZeroForNum(toHexStrings[(14 + n + 1)], 2);
						val1 = newStringUtil.hexStringToAlgorism(t3 + t4);
						n += 2;
					}
					//设备类型集合 地址+“-”+设备类型 多个中间以@符号分隔
					if (Integer.parseInt(inst.getPramNum()) == 2) {
						devTypeArray.append(address + "-" + type );
						devTypeArray.append("@");
						devTypeArray.append(address + "-" + type );
						devTypeArray.append("-@");
						devValueArray.append(StringUtis.formatZero(val,
								Double.valueOf(inst.getMagNitude())));
						devValueArray.append("@");
						devValueArray.append(StringUtis.formatZero(val1,
								Double.valueOf(inst.getMagNitude())));
						devValueArray.append("@");
						devNameArray.append("(" + address + ")"
								+ inst.getInsName().substring(0, 2) + "温度" + "["
								+ inst.getUnit().split(",")[0] + "]");
						devNameArray.append("@");
						devNameArray.append("(" + address + ")"
								+ inst.getInsName().substring(0, 2) + "湿度" + "["
								+ inst.getUnit().split(",")[1] + "]");
						devNameArray.append("@");
					} else {
						devTypeArray.append(address + "-" + type );
						devTypeArray.append("@");
						devValueArray.append(StringUtis.formatZero(val,
						Double.valueOf(inst.getMagNitude())));
						devValueArray.append("@");
						devNameArray.append("(" + address + ")"
								+ inst.getInsName() + "["
								+ inst.getUnit().split(",")[0] + "]");
						devNameArray.append("@");
					}
				}
			}
			//封装设备主表
			DevDataApp app = new DevDataApp();
			app.setDevId(Log_DevId+"");
			app.setDevCode(devCode);
			app.setDevTime(TimeDate);
			app.setDevLength(len3+"");
			app.setDevData(Arrays.toString(toHexStrings));
			app.setLoVolt(Log_Volt);
			app.setUuid(newStringUtil.getUuid());
			app.setDevTypeArray(devTypeArray.substring(0, devTypeArray.length() - 1));
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
