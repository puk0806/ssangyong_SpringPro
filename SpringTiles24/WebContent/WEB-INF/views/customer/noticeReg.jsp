<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항등록</li>
	</ul>
	<!-- 파일업로드를 위해  enctype="multipart/form-data" 속성 추가 -->
	<form action="" method="post" enctype="multipart/form-data">
		<div id="notice-article-detail" class="article-detail margin-large">
			<dl class="article-detail-row">
				<dt class="article-detail-title">제목</dt>
				<dd class="article-detail-data">
					&nbsp;<input name="title" />
				</dd>
			</dl>

			<dl class="article-detail-row">
				<dt class="article-detail-title">첨부파일</dt>
				<dd class="article-detail-data">
					<!-- name 속성 기억 file -->
					&nbsp;<input type="file" id="txtFile" name="file" />
				</dd>
			</dl>

			<div class="article-content">
				<textarea id="txtContent" class="txtContent" name="content"></textarea>
			</div>

		</div>
		<p class="article-comment margin-small">
			<input class="btn-save button" type="submit" value="저장" /> <a
				class="btn-cancel button" href="notice.htm">취소</a>
		</p>
	</form>
</div>
