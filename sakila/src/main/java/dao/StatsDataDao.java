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
	//1.고객이 소비한 총 금액
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
	//2.대여료별 영화개수
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
	//3.등급별 영화개수
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
	//4.언어별 영화 수
	public List<Map<String,Object>> filmCountByLanguage(){
		List <Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		conn = DBUtil.getConnection();
		String sql="SELECT l.name language, COUNT(*)cnt"
				+ " FROM film f INNER JOIN language l"
				+ " ON f.language_id = l.language_id"
				+ " GROUP BY l.name";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("language", rs.getString("language"));
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
	//5.영화길이별 영화 수
	public List<Map<String,Object>> filmCountByLength(){
		List <Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		conn = DBUtil.getConnection();
		String sql="SELECT t.length2 length, COUNT(*) cnt"
				+ " FROM (SELECT title, LENGTH, "
				+ " CASE WHEN LENGTH < 60 THEN '.60m under'"
				+ "	WHEN LENGTH BETWEEN 61 AND 120 THEN '1~2h'"
				+ "	WHEN LENGTH BETWEEN 121 AND 180 THEN '2~3h'"
				+ "	ELSE 'More than 3h '"
				+ "	END LENGTH2"
				+ " FROM film) t"
				+ " GROUP BY t.LENGTH2"
				+ " ORDER BY LENGTH2 ASC";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("length", rs.getString("length"));
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
	//6.배우별 영화 수(상위5명)
	public List<Map<String,Object>> actorByFilm(){
		List<Map<String, Object>> list = new ArrayList<>();
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		conn = DBUtil.getConnection();
		String sql="SELECT fa.actor_id actorId,CONCAT(a.first_name, ' ', a.last_name) name, COUNT(*) cnt"
				+ " FROM film_actor fa"
				+ " left JOIN actor a"
				+ " ON fa.actor_id = a.actor_id"
				+ " GROUP BY fa.actor_id"
				+ " ORDER BY cnt DESC LIMIT 0,5";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("actorId", rs.getString("actorId"));
				m.put("name", rs.getString("name"));
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
	//7.대여기간 별 영화개수
	public List<Map<String, Object>> rentalByFilm(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql="SELECT rental_duration rentalDate, COUNT(*) cnt"
				+ " FROM film"
				+ " GROUP BY rental_duration";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("rentalDate", rs.getInt("rentalDate"));
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
	//8.매점별 고객수
	public List<Map<String, Object>> storeByCustomer(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql="SELECT s.store_id storeId, COUNT(*) cnt"
				+ " FROM store s"
				+ " INNER JOIN customer c"
				+ " ON s.store_id = c.store_id"
				+ " GROUP BY s.store_id";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("storeId", rs.getInt("storeId"));
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
	//9.영화별로 출연한 배우 수 (상위5개 영화)
	public List<Map<String, Object>> filmByActor(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql="SELECT film_id filmId,COUNT(*) cnt"
				+ " FROM film_actor fa"
				+ " GROUP BY film_id"
				+ " ORDER BY cnt desc LIMIT 0,7";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("filmId", rs.getInt("filmId"));
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
	//10.나라별 고객수(고객이 가장 많은 국가5개)
	public List<Map<String, Object>> countryByCoustomer(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql="SELECT country, COUNT(*) cnt"
				+ " FROM customer cu"
				+ " left JOIN address a"
				+ " on a.address_id = cu.address_id"
				+ " LEFT JOIN city c"
				+ " on a.city_id = c.city_id"
				+ " LEFT JOIN country ct"
				+ " ON c.country_id = ct.country_id"
				+ " GROUP BY country"
				+ " ORDER BY cnt DESC LIMIT 0,5";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("country", rs.getString("country"));
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
