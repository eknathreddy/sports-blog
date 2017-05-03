<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp" %>

<br/><br/>

<script type="text/javascript">
	$(document).ready(function(){
		$('.nav-tabs a:first').tab('show'); // Select first tab
		$('.triggerRemove').click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href",$(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>

<div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs">
  	<c:forEach items="${user.posts}" var="post">
  		<li><a href="#post_${post.id}" aria-controls="#post_${post.id}" data-toggle="tab">${post.name}</a></li>
  	</c:forEach>
  </ul>

  <!-- Tab panes -->
	<div class="tab-content">
		<c:forEach items="${user.posts}" var="post">
			<div class="tab-pane" id="post_${post.id}">
				<h1>${post.name}</h1>
				<p>
					${post.post_msg}
					<a href='<spring:url value="/post/remove/${post.id}.html" />' class="btn btn-danger triggerRemove" >Remove post</a>
				</p>

			</div>
		</c:forEach>
	</div>
</div>

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
