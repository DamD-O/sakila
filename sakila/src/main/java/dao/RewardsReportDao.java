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
		Map<String, Object> map = new HashMap<String, Object>();
		
		//DB자원
		Connection conn =null; //연결
		CallableStatement stmt =null; //프로시저 실행
		ResultSet rs = null; //프로시저결과
		
		//select
		//rs값 저장할 변수 생성 : list
		List<Integer> list = new ArrayList<>();
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
			while(rs.next()) {
					list.add(rs.getInt(1));  //1번째 ?값 가져오기, 
				}
			//프로시저 3번째 out변수 값 받아오기, 
			count = stmt.getInt(3); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//map에 추가
		map.put("list", list);
		map.put("count", count); //프로시저 결과물
		
		return map;
	}
	//테스트 코드
	public static void main(String[]args) {
		int minMonthlyPurchase = 5; //이전달 최소 구매횟수
		double minDollarAmountPurchase = 100.0; //최소 구매 금액
		RewardsReportDao rewardsReportDao = new RewardsReportDao();
		Map<String, Object> map = rewardsReportDao.rewardReportCall(minMonthlyPurchase, minDollarAmountPurchase);
		List<Integer> list = (List<Integer>) map.get("list");
		int count = (Integer) map.get("count");
		
		System.out.println("최소" +minMonthlyPurchase + "번 구매하였고 "+"최소 "+ minDollarAmountPurchase + "$ 만큼 구매했습니다.");
		
	}
	
}
