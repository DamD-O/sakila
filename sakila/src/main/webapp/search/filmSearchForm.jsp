<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	CategoryDao searchDao = new CategoryDao();
	List<Category> categoryList = searchDao.selectCategoryList();	
	FilmListDao filmListDao = new FilmListDao();
	List<Double> priceList = filmListDao.selectfilmPriceList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h2 class="text-dark">필름 목록(view) 검색</h2></p>
   <form action="<%=request.getContextPath()%>/search/filmSerachAction.jsp" method="post" class="form-inline">
      <table class="table table-bordered">
         <tr>
            <td>category</td>
            <td>
               <select name="category" class="form-control">
                  <option value="" >카테고리선택</option>
                  <%
                     for(Category c : categoryList) {
                  %>
                        <option value="<%=c.getName()%>"><%=c.getName()%></option>
                  <%      
                     }
                  %>
               </select>            
            </td>
         </tr>
         <tr>
            <td>rating</td>
            <td>
               <select name="rating" class="form-control">
                  <option value="">등급선택</option>
                  <option value="G">G(전체 관람가)</option>
                  <option value="PG">PG(전체관람가-보호자동반필요)</option>
                  <option value="PG-13">PG-13(13세이상)</option>
                  <option value="R">R(청불/17세미만 보호자동반필요)</option>
                  <option value="NC-17">NC-17(청불)</option>
               </select>
            </td>
         </tr>
         <tr>
         	<td>대여료</td>
         	<td>
         		<div><input type="radio" name="price" value="" class="form-check-input" checked="checked">선택안함</div>
         		<%
         			for(Double p : priceList){
         		%>
         			<div><input type="radio" name="price" value="<%=p%>" class="form-check-input"><%=p%></div>
         		<%		
         			}
         		%>
         	</td>
         </tr>
         <tr>
         	<td>영화시간</td>
         	<td>
         		<div><input type="radio" name="length" value="" class="form-check-input" checked="checked">선택안함</div>
         		<div><input type="radio" name="length" value="0" class="form-check-input">1시간 미만</div> <!-- length < 60 -->
         		<div><input type="radio" name="length" value="1" class="form-check-input">1시간 이상</div> <!-- length >= 60 -->
         	</td>
         </tr>
         <tr>
         	<td>영화 제목</td>
         	<td><input type="text" name="title" class="form-control"></td>
         </tr>
          <tr>
         	<td>영화 배우</td>
         	<td><input type="text" name="actor" class="form-control"></td>
         </tr>
      </table>
      <button type="submit" class="btn btn-primary">검색</button>
   </form>
</div>
</body>
</html>