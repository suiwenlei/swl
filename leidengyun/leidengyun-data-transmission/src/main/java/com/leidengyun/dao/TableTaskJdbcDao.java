package com.leidengyun.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * jdbcDao 数据库操作
 */
public class TableTaskJdbcDao {
	private PreparedStatement st = null;
	private ResultSet rs = null;
	public TableTaskJdbcDao() {
	}
	/**
	 * 数据插入
	 * @param con
	 * @param app
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
    */
	public void insert(	Connection con,Map map) throws SQLException,IOException, ParseException {
		String sql="insert into sys_dev_title(devId,type,NameArray,TypeArray) values(?,?,?,?) ";
		try{
			st=con.prepareStatement(sql);
			st.setString(1, map.get("devId").toString());
			st.setString(2, map.get("type").toString());
			st.setObject(3, map.get("NameArray").toString());
			st.setObject(4, map.get("TypeArray").toString());
			st.executeUpdate();
		}finally{
			if (null!=st) {
				st.close();
			}
		}
	}
	/**
	 * 数据删除
	 * @param con
	 * @param id
	 * @throws SQLException
	 * @throws IOException
    */
	public void delete(Connection con,Integer id,String type) throws SQLException,IOException {
		String sql="delete from sys_dev_title where devId=? and type=?";
		try{
			st=con.prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2, type);
			st.executeUpdate();
		}finally{
			if (null!=st) {
				st.close();
			}
		}
	}
	
	
	public List<Map<String, Object>> read(Connection con,String sql)throws SQLException,IOException {
 

        try {
        	st=con.prepareStatement(sql);
            rs = st.executeQuery();

            // 构造泛型结果集
            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
            // 循环结果集
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                datas.add(rowData);
            }
            return datas;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
        	if (null!=st) {
				st.close();
			}
        }
    }
}
