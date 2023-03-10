package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Main {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			conn.setAutoCommit(false);
			
			int rows1 = st.executeUpdate("UPDATE seller set BaseSalary = 950 WHERE DepartmentId = 2");
			
//			int x = 1;
//			if (x < 2) {
//				throw new SQLException("Fake Error");
//			}
			
			int rows2 = st.executeUpdate("UPDATE seller set BaseSalary = 1560 WHERE DepartmentId = 3");
			
			System.out.println("rows1: " + rows1);
			System.out.println("rows2: " + rows2);
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back, caudes by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback, caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
