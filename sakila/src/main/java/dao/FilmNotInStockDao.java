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

public class FilmNotInStockDao {
	public Map<String, Object> filmNotInStockCall(int filmId, int storeId) {
		//리터타입 여러개는 Map으로 
		Map<String, Object> map = new HashMap<String, Object>();
	
		//DB자원
		Connection conn =null; //연결
		CallableStatement stmt =null; //프로시저 실행
		ResultSet rs = null; //프로시저결과
		
		//inventory_id 출력
		//rs값 저장할 변수 생성 : list
		List<Integer> list = new ArrayList<>();
		//inventory_id count출력
		//out변수 값 저장
		Integer count =0; 
		
		//DB연결 호출
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{CALL film_not_in_stock(?,?,?)}"); //프로시저 호출
			stmt.setInt(1, filmId); 
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER); //Integer타입으로 리턴, out매개변수
			//실행
			rs = stmt.executeQuery(); 
			while(rs.next()) {
					list.add(rs.getInt(1));  //1번째 ?값 가져오기, rs.getInt("inventory_id");
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
			int filmId = 26;
			int storeId = 1;
			FilmInStockDao filmInStockDao = new FilmInStockDao();
			Map<String, Object> map = filmInStockDao.filmInStockCall(filmId, storeId);
			List<Integer> list = (List<Integer>) map.get("list");
			int count = (Integer) map.get("count");
			
			System.out.println(filmId+"번 영화가 "+storeId + "번 가게에 " +count +" 개의 재고가 없습니다.");
			
			for(int id :list) {
				System.out.println("id :"+ id);
			}
		}
}
