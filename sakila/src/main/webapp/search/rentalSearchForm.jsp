<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	StoreDao storeDao = new StoreDao(); //dao객체
	List<Integer> storeIdList = storeDao.selectStoreIdList();//메소드 호출
	
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
	<p><h2 class="text-dark">대여상세 검색</h2></p>
	<form action="<%=request.getContextPath()%>/search/rentalSearchAction.jsp" method="post" class="form-inline">
		<table class="table table-bordered">
			<tr>
				<td>매점 ID</td>
				<td>
					<%
						for(int i : storeIdList){
					%>
						<div>
							<input type="radio" name="storeId" class="form-check-input" value="<%=i%>"><%=i%>번 가게
						</div>
					<%	
						}
					%>
			</tr>
	         <!-- 고객이름 검색 -->      
	         <tr>
	            <td>고객이름</td>
	            <td>
	               <input type="text" name="customerName" class="form-control">
	            </td>
	         </tr>
	         <!-- 대여일자 -->
	         <tr>
	            <td>대여일자</td>
	            <td>
	               <input type="date" name="beginDate" class="form-control"> ~ <input type="date" name="endDate" class="form-control">
	            </td>
	         </tr>
	         <tr>
	            <td colspan="2"><button type="submit" class="btn btn-primary">검색</button></td>
	         </tr>		
		</table>
	</form>
	
</div>
</body>
</html>