<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp" %>

<h3>Hi ${user.name} !!</h3>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  New Post
</button>

<form:form commandName="post" cssClass="form-horizontal postForm">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">New Post Details</h4>
      </div>
      <div class="modal-body">
      	<div class="form-group">
			<label for="name" class="col-sm-2 control-label"> Name : </label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>		
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"> Post : </label>
			<div class="col-sm-10">
				<form:input path="post_msg" cssClass="form-control" />
				<form:errors path="post_msg" />
			</div>		
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="submit" class="btn btn-primary" value="Save" />
      </div>
    </div>
  </div>
</div>
</form:form>

<br/><br/>

<script type="text/javascript">
	$(document).ready(function(){
		$('.nav-tabs a:first').tab('show'); // Select first tab
		$('.triggerRemove').click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href",$(this).attr("href"));
			$("#modalRemove").modal();
		});
		$(".postForm").validate({
			rules: {
				name : {
					required:true,
					minlength:2
				},
				post_msg : {
					required:true,
					minlength:2
				}
			},
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
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
