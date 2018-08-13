package com.leidengyun.socket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.leidengyun.model.DevDataApp;
import com.leidengyun.model.Instrument;

public class JdbcDao {
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	public JdbcDao() {
	}
	
	public void insert(	Connection con,DevDataApp app) throws SQLException,IOException, ParseException {
		String sql="insert into sys_device_data(devId,devCode,loVolt,devData,devLength,devTime,devTypeArray,"
				+ "devNameArray,devValueArray,ifRevice,uuid) values(?,?,?,?,?,?,?,?,?,?,?) ";
		try{
			
			st=con.prepareStatement(sql);
			st.setString(1, app.getDevId());
			st.setString(2, app.getDevCode());
			st.setDouble(3, app.getLoVolt());
			st.setString(4, app.getDevData());
			st.setString(5, app.getDevLength());
			st.setString(6, app.getDevTime());
			st.setObject(7, app.getDevTypeArray());
			st.setObject(8, app.getDevNameArray());
			st.setObject(9, app.getDevValueArray());
			st.setString(10, app.getIfRevice());
			st.setString(11, app.getUuid());
			st.executeUpdate();
		}finally{
			if (null!=st) {
				st.close();
			}
			
		}
		
	}
	
	public void delete(Connection con,int id) throws SQLException,IOException {
		String sql="delete from sys_device_data where id=?";
		try{
			st=con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		}finally{
			if (null!=st) {
				st.close();
			}
			
		}
	}
	
//	public void update(	Connection con,int id,String name) throws SQLException,IOException {
//		String sql="update user set name=? where id=?";
//		
//		try{
//			st=con.prepareStatement(sql);
//			st.setString(1, name);
//			st.setInt(2, id);
//			st.executeUpdate();
//		}finally{
//			if (null!=st) {
//				st.close();
//			}
//		}
//
//	}
	
	public Instrument query(Connection con,String insType,String insId) throws SQLException,IOException{
		Instrument inst=new Instrument();
		String sql="select insName,insDm,unit,magNitude,pramNum from sys_instrument where insType=? and insId=?";
		try{
			st=con.prepareStatement(sql);
			st.setString(1, insType);
			st.setString(2, insId);
			rs=st.executeQuery();
			while (rs.next()) {
				inst.setInsName(rs.getString(1));
				inst.setInsDm(rs.getString(2));
				inst.setUnit(rs.getString(3));
				inst.setMagNitude(rs.getString(4));
				inst.setPramNum(rs.getString(5));
				break;
			}
			
		}finally{
			if (null!=rs) {
				rs.close();
			}
			
			if (null!=st) {
				st.close();
			}
		}
		
		return inst;
		
	}
	
//	public List<User> queryAll(Connection con) throws SQLException,IOException {
//		List<User> list=new ArrayList<>();
//		String sql="select * from user";
//		try {
//			st=con.prepareStatement(sql);
//			rs=st.executeQuery();
//			while (rs.next()) {
//				User user=new User();
//				user.setId(rs.getInt(1));
//				user.setName(rs.getString(2));
//				list.add(user);
//			}
//			
//		}finally{
//			if (null!=rs) {
//				rs.close();
//			}
//			
//			if (null!=st) {
//				st.close();
//			}
//		}
//		
//		return list;
//		
//
//	}
	
}
