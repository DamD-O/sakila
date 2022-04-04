package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//공통되는 db
public class DBUtil {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");//드라이버 생성
			//db연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
