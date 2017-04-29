<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<!-- <script type="text/javascript">
$(document).ready(function() { 
var date    = new Date(post.publishedDate),
yr      = date.getFullYear(),
month   = date.getMonth() < 10 ? '0' + date.getMonth() : date.getMonth(),
day     = date.getDate()  < 10 ? '0' + date.getDate()  : date.getDate(),
newDate = yr + '-' + month + '-' + day;
});
</script> -->

<input hidden id="hide" />
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Name</th>
			<th>Post</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach  items="${posts}" var="post">
		<c:if test="${post.reviewed eq true}">
			<tr>
				<td>${post.name}</td>
				<td><a href='<spring:url value="/index/${post.id}.html" />'>
					<h4> <c:out value="${post.post_msg}"/> </h4>
					<br>
					<c:out value="Published on: ${post.publishedDate}"/>
					<br>
					<c:out value="By ${post.user.name}" /> 
					</a>
				 </td>
			</tr>
		</c:if>
		</c:forEach>
	</tbody>
</table>
