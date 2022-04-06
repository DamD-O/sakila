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

public class FilmInStockDao {
	public Map<String, Object> filmInStockCall(int filmId, int storeId){//리런타입 여러개: MAP
		Map<String, Object> map = new HashMap<String, Object>();
		
		//DB자원 준비
		Connection conn = null; //연결변수
		//PreparedStatement :쿼리결과를 저장하는 타입
		//CallableStatement : 프로시저 결과를 저장하는 타입
		CallableStatement stmt = null; //call변수
		ResultSet rs = null; //결과저장 변수
		//select inventory_id 
		List<Integer> list = new ArrayList<>(); //숫자값 리턴, rs저장항 list변수 생성
		//select count(inventory_id)
		Integer count =0;//숫자값, out변수 값 저장할 변수 생성
		conn = DBUtil.getConnection(); //db연결호출
		try {
			//? : 이름을 알 수 없음
			stmt =conn.prepareCall("{CALL film_in_stock(?,?,?)}"); //{}안 프로시저 이름(호출 코드)
			stmt.setInt(1, filmId); 
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER); //Integer타입으로 리턴, out매개변수
			rs = stmt.executeQuery(); //실행
			while(rs.next()) {
				list.add(rs.getInt(1));  //1번째 ?값 가져오기, rs.getInt("inventory_id");
			}
			count = stmt.getInt(3); //프로시저 3번째 out변수 값 받아오기, 
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
		int filmId = 12;
		int storeId = 1;
		FilmInStockDao filmInStockDao = new FilmInStockDao();
		Map<String, Object> map = filmInStockDao.filmInStockCall(filmId, storeId);
		List<Integer> list = (List<Integer>) map.get("list");
		int count = (Integer) map.get("count");
		
		System.out.println(filmId+"번 영화가 "+storeId + "번 가게에 " +count +"개 남음");
		
		for(int id :list) {
			System.out.println("id :"+ id);
		}
	}
}
