<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	//이전,다음페이지
	int rowPerPage = 10;
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage + "<-- currentPage");
	int beginRow = (currentPage-1)*rowPerPage;

	int storeId = -1; //기본값
	if(!request.getParameter("storeId").equals("")) {
		storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	String customerName = request.getParameter("customerName");
	String beginDate = request.getParameter("beginDate");
	String endDate = request.getParameter("endDate");
	//디버깅
	System.out.println("storeId->" + storeId);
	System.out.println("customerName->" + customerName);
	System.out.println("beginDate->" + beginDate);
	System.out.println("endDate->" + endDate);
	//dao객체생성
	//rental정보가져오기
	RentalDao rentalDao = new RentalDao(); 
	List<Map<String, Object>> list = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate, beginRow, rowPerPage);
	///storeId가져오기
	StoreDao storeDao = new StoreDao();
	List<Integer> storeList = storeDao.selectStoreIdList();

	//마지막행, 현재페이지
	int lastPage =0; //마지막 페이지
	int totalRow = rentalDao.totalRow(storeId, customerName, beginDate, endDate);
	
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="widtd=device-widtd, initial-scale=1.0">
<title>Rental Search</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark table-responsive"">
	<table class="table table-bordered">
	<tr>
	<td colspan="11">
		<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/search/rentalSearchForm.jsp" class="nav-link text-white">검색창</a>
		</li>
	</ul>
	</td>
	</tr>
		<tr>
			<td class="text-primary" >rentalId</td>
			<td class="text-primary">rentalDate</td>
			<td class="text-primary">inventoryId</td>
			<td class="text-primary">customerId</td>	
			<td class="text-primary">returnDate</td>	
			<td class="text-primary">staffId</td>	
			<td class="text-primary">lastUpdate</td>	
			<td class="text-primary">customerName</td>	
			<td class="text-primary">storeId</td>	
			<td class="text-primary">filmId</td>	
			<td class="text-primary">title</td>	
		</tr>
		<%
			for( Map m : list ) {
		%>
			<tr>
				<td><%=m.get("rentalId")%></td>
				<td><%=m.get("rentalDate")%></td>
				<td><%=m.get("inventoryId")%></td>
				<td><%=m.get("customerId")%></td>
				<td><%=m.get("returnDate")%></td>
				<td><%=m.get("staffId")%></td>
				<td><%=m.get("lastUpdate")%></td>
				<td><%=m.get("customerName")%></td>
				<td><%=m.get("storeId")%></td>
				<td><%=m.get("filmId")%></td>
				<td><%=m.get("title")%></td>
			</tr>
		<%		
			}
		%>
	</table>
	<!-- 이전 버튼 -->
	<div class="btn-group ">
		<%
			if(currentPage > 1) { 
		%>
			<a href="<%=request.getContextPath()%>/search/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>" class="btn btn-outline-info">이전</a>
		<%	
			}
		%>
		<!-- 다음 버튼 -->
		<%
			if(currentPage < lastPage) {
		%>
			<a href="<%=request.getContextPath()%>/search/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>"  class="btn btn-outline-info">다음</a>
		<%		
			}
		%>
	</div>
</div>
</body>
</html>