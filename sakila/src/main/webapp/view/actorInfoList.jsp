<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import = "vo.*" %>
<%
	int beginRow =0;
	int rowPerPage =10; //페이지 행 개수
	
	/*  페이지 이전 또는 다음 버튼  */
	int curPage =1; //현재페이지 기본값
	if(request.getParameter("curPage") != null){
		//이전,다음링크 클릭
		curPage = Integer.parseInt(request.getParameter("curPage")); //현재페이지로 하기
	}
	System.out.println("현재페이지" + curPage);
	
	//다음페이지 or 마지막 페이지
	beginRow = (curPage-1)*rowPerPage; //이전 페이지의 행 개수는 현재페이지에서 1을 뺀 값에 한페이지당 행 개수(10)를 곱한다.?

	ActorInfoDao actorInfoDao = new ActorInfoDao(); //daoㅅ객체생성
	List<ActorInfo> list= actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage);
	
	int lastPage =0; //마지막 페이지
	int totalRow = actorInfoDao.totalRow(); //총 행 개수 
	
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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<title>actorInfo</title>
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">Actor Info</h1></p>
	<table class="table table-striped table-hover">
		<thead>
			<th>actorId</th>
			<th>firstName</th>
			<th>lastName</th>
			<th>filmInfo</th>
		</thead>
		<tbody>
			<%for(ActorInfo a : list){
			%>
			<tr>
				<td><%=a.getActorId()%></td>
				<td><%=a.getFirstName()%></td>
				<td><%=a.getLastName() %></td>
				<td><%=a.getFilmInfo() %></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<!-- 이전 버튼 -->
	<div class="btn-group ">
		<%
			if(curPage >1){
		%>
			<a href="<%=request.getContextPath()%>/actorInfoList.jsp?curPage=<%=curPage-1%>" class="btn btn-outline-info">이전</a>
		<%
			}
		%>
		<!-- 다음 버튼 -->
		<%
		 	if(curPage < lastPage){
		 %>
		 	<a href="<%=request.getContextPath()%>/actorInfoList.jsp?curPage=<%=curPage+1%>" class="btn btn-outline-info">다음</a>
		 <% 
		 	}
		%>
	</div>
</div>
</body>
</html>