<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import = "dao.*"%>
<%@ page import = "vo.*"%>
<%
	//값 가져오기
	String category = request.getParameter("category");
	String rating = request.getParameter("rating");
	double price = -1; // price 데이터가 입력되지 않았을때
	if(!request.getParameter("price").equals("")) {
		price = Double.parseDouble(request.getParameter("price"));
	}
	int length = -1; // length 데이터가 입력되지 않았을때
	if(!request.getParameter("length").equals("")) {
		length = Integer.parseInt(request.getParameter("length"));
	}
	String title = request.getParameter("title");
	String actor = request.getParameter("actor");
	
	
	//이전 또는 다음 버튼
	int beginRow = 0; 
	int rowPerPage = 10; //페이지당 행 개수
	
	FilmListDao filmListDao = new FilmListDao(); //dao객체 생성
	//list 메소드 생성
	List<FilmList> list = filmListDao.selectFilmListSearch(beginRow ,rowPerPage ,category, rating, price, length, title, actor);
	System.out.println(list.size()); // 0
	
	/*  페이지 이전 또는 다음 버튼  */
	int curPage =1; //현재페이지 기본값
	if(request.getParameter("curPage") != null){
		//이전,다음링크 클릭
		curPage = Integer.parseInt(request.getParameter("curPage")); //현재페이지로 하기
	}
	System.out.println("현재페이지" + curPage);
	
	//다음페이지 or 마지막 페이지
	beginRow = (curPage-1)*rowPerPage; //이전 페이지의 행 개수는 현재페이지에서 1을 뺀 값에 한페이지당 행 개수(10)를 곱한다.?
			
	int lastPage =0; //마지막 페이지
	CategoryDao categoryDao = new CategoryDao();
	int totalRow = categoryDao.totalRow(category, rating, price, length, title, actor); //총 행 개수 
	
	//다음페이지로 넘어가게
	if(totalRow % rowPerPage == 0){
		lastPage = totalRow / rowPerPage;
	}else{
		lastPage =(totalRow / rowPerPage )+1;
	}
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container border border-dark">
	<table class="table table-bordered">
	<tr>
	<td colspan="8">
		<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/search/filmSearchForm.jsp" class="nav-link text-white">검색창</a>
		</li>
	</ul>
	</td>
	</tr>
		<tr>
			<td>filmId</td>
			<td>Title</td>
			<td>Description</td>
			<td>Category</td>
			<td>Price</td>
			<td>Length</td>
			<td>Rating</td>
			<td>Actors</td>
		</tr>
		<%
			for(FilmList f : list) {
		%>
				<tr>
					<td><%=f.getFid()%></td>
					<td><%=f.getTitle()%></td>
					<td><%=f.getDescription()%></td>
					<td><%=f.getCategory()%></td>
					<td><%=f.getPrice()%></td>
					<td><%=f.getLength()%></td>
					<td><%=f.getRating()%></td>
					<td><%=f.getActors()%></td>
				</tr>
		<%		
			}
		%>
	</table>
	<!-- 이전 버튼 -->
	<div class="btn-group ">
		<%
			if(curPage >1){
		%>
			<a href="<%=request.getContextPath()%>/view/filmSerachAction.jsp.jsp?curPage=<%=curPage-1%>" class="btn btn-outline-info">이전</a>
		<%
			}
		%>
		<!-- 다음 버튼 -->
		<%
		 	if(curPage < lastPage){
		 %>
		 	<a href="<%=request.getContextPath()%>/view/filmSerachAction.jsp.jsp?curPage=<%=curPage+1%>" class="btn btn-outline-info">다음</a>
		 <% 
		 	}
		%>
	</div>
</div>
</body>
</html>