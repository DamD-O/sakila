<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();

	//1. 배우가 출연한 영화 개수
	List<Map<String,Object>> actorByFilm =statsDataDao.actorByFilm();
	//2.대여기간 별 영화개수
	List<Map<String,Object>> rentalByFilm = statsDataDao.rentalByFilm();
	//3.매점별 고객수
	List<Map<String,Object>> storeByCustomer =statsDataDao.storeByCustomer();
	//4.영화별로 출연한 배우수
	List<Map<String,Object>> filmByActor =statsDataDao.filmByActor();
	//5.나라별 고객수
	List<Map<String,Object>> countryByCoustomer =statsDataDao.countryByCoustomer();


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통계</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<!-- 1 -->
	<p><h3 class="text-dark">actorByFilm(제일 많이 출연한 배우 5명)</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>배우아이디</th>
			<th>배우이름</th>
			<th>출연한 영화 개수</th>
		</tr>
	<%
		for(Map<String,Object> m : actorByFilm){
	%>
		<tr>
			<td><%=m.get("actorId") %></td>
			<td><%=m.get("name") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
	<% 		
		}
	%>
	</table>
	<!-- 2 -->
	<p><h3 class="text-dark">rentalByFilm</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>대여기간</th>
			<th>영화 수</th>
		</tr>
	<%
		for(Map<String,Object> m : rentalByFilm){
	%>
		<tr>
			<td><%=m.get("rentalDate") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
	<% 		
		}
	%>
	</table>
	<!-- 3 -->
	<p><h3 class="text-dark">storeByCustomer</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>매점번호</th>
			<th>고객 수</th>
		</tr>
	<%
		for(Map<String,Object> m : storeByCustomer){
	%>
		<tr>
			<td><%=m.get("storeId") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
	<% 		
		}
	%>
	</table>
	<!-- 4 -->
	<p><h3 class="text-dark">filmByActor</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>영화번호</th>
			<th>출연한 배우수(상위 5명)</th>
		</tr>
	<%
		for(Map<String,Object> m : filmByActor){
	%>
		<tr>
			<td><%=m.get("filmId") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
	<% 		
		}
	%>
	</table>
	<!-- 5 -->
	<p><h3 class="text-dark">countryByCoustomer(가장고객이 많은 나라5개국)</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>나라</th>
			<th>고객 수</th>
		</tr>
			<%
				for(Map<String,Object> m : countryByCoustomer){
			%>
				<tr>
					<td><%=m.get("country") %></td>
					<td><%=m.get("cnt") %></td>
				</tr>
			<% 		
				}
			%>
	</table>
	<ul class="pagination">
	  <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/statistics/statsData.jsp">이전페이지</a></li>
	</ul>
</div>
</body>
</html>