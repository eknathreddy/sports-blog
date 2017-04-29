<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp" %>

<br/><br/>

<script type="text/javascript">

	$(document).ready(function(){
		
		$('.triggerAccept').click(function(e){
			e.preventDefault();
			$("#modalAccept .acceptBtn").attr("href",$(this).attr("href"));
			$("#modalAccept").modal();
		});
		
		//$('.nav-tabs a:first').tab('show'); // Select first tab
		$('.triggerRemove').click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href",$(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Post name</th>
			<th>Post</th>
			<th>Posted by</th>
			<th>Posted on</th>
			<th>Operations</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${posts}" var="post">
		<c:if test="${post.reviewed eq false}">
			<tr>
				<td>
					<c:out value="${post.name}" /> 
				</td>
				<td>
					<c:out value="${post.post_msg}" /> 
				</td>
				<td>
					<c:out value="${post.user.name}" /> 
				</td>
				<td>
					<c:out value="${post.publishedDate}" /> 
				</td>
				<td>
					<a href='<spring:url value="/post/accept/${post.id}.html" />' class="btn btn-success triggerAccept" >
						Accept
					</a>
					<a href='<spring:url value="/post/remove/${post.id}.html" />' class="btn btn-danger triggerRemove" >
						Remove
					</a>
				</td>
			</tr>
		</c:if>
		</c:forEach>
	</tbody>
</table>


<!-- Remove Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove post</h4>
      </div>
      <div class="modal-body">
      	Do you really want to remove this post?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>

<!-- Accept Modal -->
<div class="modal fade" id="modalAccept" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Accept post</h4>
      </div>
      <div class="modal-body">
      	Do you really want to Accept this post?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-success acceptBtn">Accept</a>
      </div>
    </div>
  </div>
</div>
