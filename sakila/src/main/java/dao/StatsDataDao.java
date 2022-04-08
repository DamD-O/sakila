package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class StatsDataDao {
	public List<Map<String, Object>> amountByCoustomer(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		conn = DBUtil.getConnection();
		String sql ="SELECT p.customer_id customerId, "
				+ "	concat(c.first_name,' ', c.last_name) name,"
				+ "	sum(p.amount) total"
				+ "	FROM payment p INNER JOIN customer c"
				+ "	ON p.customer_id = c.customer_id"
				+ "	GROUP BY p.customer_id"
				+ "	HAVING sum(amount) >= 180"
				+ "	ORDER BY SUM(p.amount) DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("customerId", rs.getInt("customerId"));
				m.put("name", rs.getString("name"));
				m.put("total", rs.getInt("total"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public List<Map<String,Object>> filmCountByRentalRate(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		conn = DBUtil.getConnection();
		String sql="SELECT rental_rate rentalRate, COUNT(*) cnt"
				+ "	FROM film"
				+ "	GROUP BY rental_rate"
				+ "	ORDER BY COUNT(*) DESC";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("rentalRate", rs.getDouble("rentalRate"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public List<Map<String,Object>> filmCountByRating(){
List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		conn = DBUtil.getConnection();
		String sql="SELECT rating, COUNT(*) cnt"
				+ "	FROM film"
				+ "	GROUP BY rating"
				+ "	ORDER BY COUNT(*) DESC";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("rating", rs.getString("rating"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
