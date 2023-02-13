<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<fmt:requestEncoding value="utf-8"/>     
<!DOCTYPE html>
<%--


 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<style>
	td{text-align:center;}
</style>
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://developers.google.com/web/ilt/pwa/working-with-the-fetch-api" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		<%-- 
		
		--%>	
		$("#regBtn").click(function(){
			location.href="${path}/insertFrm.do"
		})
	});
	function goDetail(no){
		location.href="${path}/board.do?no="+no
	}	
</script>
</head>

<body>
<div class="jumbotron text-center">
  <h2>게시판</h2>
  <!-- 
  <h2 data-toggle="modal" data-target="#exampleModalCenter">타이틀</h2>
 	-->
</div>
<div class="container">
  	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  		<div class="container-fluid">
		    <form id="frm01" class="d-flex"  method="post"> 			
			    <input name="subject" value="${sch.subject}" class="form-control mr-sm-2" placeholder="제목" />
			    <input name="writer" value="${sch.writer}" class="form-control mr-sm-2" placeholder="내용" />
			    <button class="btn btn-success" id="regBtn" type="button">등록</button>
			    &nbsp;
			    <button class="btn btn-primary" type="submit">Search</button>
			</form>   

	    </div>
 	</nav>
 	

   <table class="table table-hover table-striped">
   	<col width="10%">
   	<col width="45%">
   	<col width="15%">
   	<col width="15%">
   	<col width="15%">
    <thead>
    
      <tr class="table-success text-center">
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
    </thead>	
    <tbody>
    	<c:forEach var="board" items="${list}">
    	<tr  ondblclick="goDetail(${board.no})">
    		<td>${board.cnt}</td>
    		<td style="text-align:left;">
    			<c:if test="${board.level>1}">		
	      			<c:forEach begin="2" end="${board.level}">
	    				&nbsp;&nbsp;&nbsp;
	    			</c:forEach>  
    				<img src="${path}/a01_img/re.png" 
    					width="5%" height="5%">
    			</c:if>
    			${board.subject}</td>
    		<td>${board.writer}</td>
    		<td><fmt:formatDate value="${board.regdte}"/></td>
    		<td>${board.readcnt}</td></tr>
    	</c:forEach>
    </tbody>
	</table>    
	<ul class="pagination  justify-content-end">
	  <li class="page-item"><a class="page-link" href="#">Previous</a></li>
	  <li class="page-item"><a class="page-link" href="#">1</a></li>
	  <li class="page-item active"><a class="page-link" href="#">2</a></li>
	  <li class="page-item"><a class="page-link" href="#">3</a></li>
	  <li class="page-item"><a class="page-link" href="#">Next</a></li>
	</ul>    
</div>
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">타이틀</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="frm02" class="form"  method="post">
	     <div class="row">
	      <div class="col">
	        <input type="text" class="form-control" placeholder="사원명 입력" name="ename">
	      </div>
	      <div class="col">
	        <input type="text" class="form-control" placeholder="직책명 입력" name="job">
	      </div>
	     </div>
	    </form> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>