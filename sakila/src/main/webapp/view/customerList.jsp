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
	CustomerListDao customerListDao = new CustomerListDao();
	List<CustomerList> list = customerListDao.selectCustomerList(beginRow, rowPerPage); //dao에 생성된 메소드 호출
	
	//마지막 페이지
	int lastPage = 0;
	int totalRow = customerListDao.totalRow(); //dao에 생성된 메소드 호출
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
<title>Customer List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">Customer List</h1></p>
	<table class="table table-striped table-hover">
		<thead>
			<th>customerId</th>
			<th>name</th>
			<th>address</th>
			<th>postalCode</th>
			<th>phone</th>
			<th>city</th>
			<th>country</th>
			<th>notes</th>
			<th>storeId</th>
		</thead>
		<tbody>
			<%for(CustomerList c : list){
			%>
			<tr>
				<td><%=c.getCustomerId()%></td>
				<td><%=c.getName()%></td>
				<td><%=c.getAddress()%></td>
				<td><%=c.getPostalCode()%></td>
				<td><%=c.getPhone()%></td>
				<td><%=c.getCity()%></td>
				<td><%=c.getCountry()%></td>
				<td><%=c.getNotes()%></td>
				<td><%=c.getStoreId()%></td>
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
			<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-outline-info">이전</a>
		<%
			}
		%>
		<!-- 다음 버튼 -->
		<%
		 	if(currentPage < lastPage){
		 %>
		 	<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-info">다음</a>
		 <% 
		 	}
		%>
	</div>
</div>
</body>
</html>