<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	int beginRow =0; //이전페이지의 행
	int rowPerPage =10; //처음 한페이지 행수
	
	//현재페이지
	int currentPage = 1; //현재페이지 기본값 1
	if(request.getParameter("currentPage") != null){ //현재페이지가 null이 아니면
		currentPage = Integer.parseInt(request.getParameter("currentPage")); //가져온 현재페이지를 숫자로 변환
	}
	//디버깅
	System.out.println("현재페이지 : " + currentPage);
	
	//이전 행
	beginRow = (currentPage-1) * rowPerPage; // 이전페이지 행은 현재페이지에서 1을 뺸 값에 한페이지 행수 곱한값
	//dao객체 생성, 메소드 호출
	NicerButSlowerFilmListDao nicerButSlowerFilmListDao = new NicerButSlowerFilmListDao();
	//dao에 생성된 메소드 호출
	List<NicerButSlowerFilmList> list = nicerButSlowerFilmListDao.selectNicerFilmList(beginRow, rowPerPage); 
	
	//마지막 페이지
	int lastPage = 0;
	int totalRow = nicerButSlowerFilmListDao.totalRow(); //dao에 생성된 메소드 호출
	System.out.println("총 행수 : " + totalRow);
	//다음 페이지
	//페이지가 rowPerPage로 나누어떨어지지 않으면 1페이지 추가
	if(totalRow % rowPerPage == 0){
		lastPage = totalRow / rowPerPage;
	}else{
		lastPage =(totalRow / rowPerPage )+1;
	}
	System.out.println("마지막페이지 : " + lastPage);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NicerButSlowerFilmList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark table-responsive">
<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">NicerButSlowerFilmList</h1></p>
	<table class="table table-striped table-hover">
	<thead>
			<th>filmId</th>
			<th>title</th>
			<th>description</th>
			<th>categoryName</th>
			<th>price</th>
			<th>length</th>
			<th>rating</th>
			<th>actorName</th>
		</thead>
		<tbody>
			<%
				for(NicerButSlowerFilmList n :list){
			%>
				<tr>
				<td><%=n.getFilmId()%></td>
				<td><%=n.getTitle()%></td>
				<td><%=n.getDescription()%></td>
				<td><%=n.getCategory()%></td>
				<td><%=n.getPrice()%></td>
				<td><%=n.getLength() %></td>
				<td><%=n.getRating()%></td>
				<td><%=n.getActors()%></td>
				</tr>
			<%		
				}
			%>
		</tbody>
	</table>
	<!-- 이전 버튼 -->
	<div class="btn-group ">
		<%
			if(currentPage >1){
		%>
			<a href="<%=request.getContextPath()%>/view/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-outline-info">이전</a>
		<%
			}
		%>
		<!-- 다음 버튼 -->
		<%
		 	if(currentPage < lastPage){
		 %>
		 	<a href="<%=request.getContextPath()%>/view/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-info">다음</a>
		 <% 
		 	}
		%>
	</div>
</div>
</body>
</html>