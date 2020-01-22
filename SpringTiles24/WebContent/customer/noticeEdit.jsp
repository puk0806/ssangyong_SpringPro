<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항수정</li>
	</ul>
	<!-- POST방식 /SpringMVC15/customer/noticeEdit.htm?seq=4 -->
	<!-- form태그 안에 title, content , file input 태그 -->
	<form action="" method="post" enctype="multipart/form-data">
		<div id="notice-article-detail" class="article-detail margin-large">
			<dl class="article-detail-row">
				<dt class="article-detail-title">제목</dt>
				<dd class="article-detail-data">
					&nbsp;<input name="title" value="${ notice.title } " />
				</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">작성자</dt>
				<dd class="article-detail-data half-data">${ notice.writer }</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">조회수</dt>
				<dd class="article-detail-data half-data">${ notice.hit }</dd>
			</dl>
			<dl class="article-detail-row">
				<dt class="article-detail-title">첨부파일</dt>
				<dd class="article-detail-data">
					&nbsp;<input type="file" id="txtFile" name="file" />
					<!--  추가  1  -->
					<input type="hidden" value="${ notice.filesrc }" name="oFileSrc" />
				</dd>
			</dl>

			<div class="article-content">
				<textarea id="txtContent" class="txtContent" name="content">${ notice.content }</textarea>
			</div>
		</div>
		<p class="article-comment margin-small">
			<!-- <a class="btn-save button" href="noticeEditProc.jsp">수정</a> -->
			<input class="btn-save button" type="submit" value="수정" />

			<!-- 수정페이지로 이동-> 수정권한( 관리자, 작성자) -->
			<!-- jquery 브라우저 상에서 이전페이지로 이동 -->
			<a class="btn-cancel button"
				href="noticeDetail.htm?seq=${ notice.seq }" id="cancel">취소</a>
		</p>
	</form>
</div>
<script>
 $(document).ready(function() {
 	$("#cancel").on("click", function(event) {
 		event.preventDefault();
 		history.go(-1);
 		//history.back();
 	});
 });
 </script>
