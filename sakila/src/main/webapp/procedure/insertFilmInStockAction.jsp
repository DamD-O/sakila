<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	//인코딩
	request.setCharacterEncoding("utf-8"); 

	FilmInStockDao filmInStockDao = new FilmInStockDao(); //객체생성
	//값 가져오기
	int filmId = Integer.parseInt(request.getParameter("filmId"));
	int storeId = Integer.parseInt(request.getParameter("storeId"));
	
	Map<String, Object> map = filmInStockDao.filmInStockCall(filmId, storeId);
	List<Integer> list = (List<Integer>) map.get("list");
	int count = (Integer) map.get("count");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">영화 재고 현황</h1></p>
	<table class="table table-bordered">
		<thead>
			<th>영화 번호</th>
			<th>가게 번호</th>
			<th>재고 현황</th>
			<th>재고 남음 장소</th>
		</thead>
		<tbody>
			<tr>
				<td><%=filmId%>번</td>
				<td><%=storeId%>번</td>
				<td><%=count %>개</td>
				<td>
				<%
					for(int id :list) {
				%>
					<%=id%>,
				<%
				}
				%>
				</td>
			</tr>
		</tbody>
	</table>
</div>	
</body>
</html>
