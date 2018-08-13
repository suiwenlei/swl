package com.leidengyun.socket.util;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class JdbcUtis {

	public static Connection getJdbcConn() throws SQLException {

		Connection con = null;
		try {
			con = (Connection) ConnectionFactory.getInstance().getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con.isClosed()) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return con;
	}
}
