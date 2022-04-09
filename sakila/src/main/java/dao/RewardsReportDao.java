package dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
public class RewardsReportDao {
	public Map<String,Object> rewardReportCall(int minMonthlyPurchase, double minDollarAmountPurchase){
		//리터타입 여러개는 Map으로 
		List<Map<String, Object>> list = new ArrayList<>();
		
		//DB자원
		Connection conn =null; //연결
		CallableStatement stmt =null; //프로시저 실행
		ResultSet rs = null; //프로시저결과
		
		Map<String,Object> map = null;
		//count
		Integer count =0;
		//DB연결 호출
		conn = DBUtil.getConnection();
		
		try {
			stmt= conn.prepareCall("{CALL rewards_report(?,?,?)}"); //프로시저 호출
			stmt.setInt(1, minMonthlyPurchase); 
			stmt.setDouble(2, minDollarAmountPurchase);
			stmt.registerOutParameter(3, Types.INTEGER); //Integer타입으로 리턴, out매개변수
			//실행
			rs = stmt.executeQuery(); 
			//프로시저 3번째 out변수 값 받아오기, 
			count = stmt.getInt(3); 
			while(rs.next()) {
				Map<String,Object> tempMap = new HashMap<String,Object>();
	        	tempMap.put("customerId",rs.getInt("customer_id"));
	        	tempMap.put("storeId",rs.getInt("store_id"));
	        	tempMap.put("firstName",rs.getString("first_name"));
	        	tempMap.put("lastName",rs.getString("last_name"));
	        	tempMap.put("email",rs.getString("email"));
	        	tempMap.put("addressId",rs.getInt("address_id"));
	        	tempMap.put("active",rs.getInt("active"));
	        	tempMap.put("createDate",rs.getString("create_date"));
	        	tempMap.put("lastUpdate",rs.getString("last_update"));
	            list.add(tempMap);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	    	  try {
	              // 데이터베이스 자원 반환
	              rs.close();
	              stmt.close();
	              conn.close();
	           } catch (SQLException e) {
	              e.printStackTrace();
	           }
	        }
		
		//map에 추가
		map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count); //프로시저 결과물
		
		return map;
	}
	
}
