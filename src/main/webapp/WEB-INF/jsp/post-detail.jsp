<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<br />
<br />

<div align="center">
<h1>${post.name}</h1>
<p>By ${post.user.name}</p>
<p>Published on : ${post.publishedDate}</p>
<h4>${post.post_msg}</h4>

<br />
<div class="fb-like" data-href="http://localhost:9696/${post.name}"
	data-layout="standard" data-action="like" data-size="small"
	data-show-faces="true" data-share="false"></div>
<br />

<div class="fb-comments" data-href="http://localhost:9696/${post.name}"
	data-numposts="5"></div>
	
</div>