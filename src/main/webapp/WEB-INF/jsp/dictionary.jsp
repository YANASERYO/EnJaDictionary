<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.EjDict, java.util.List" %>
<%
List<EjDict> dictList = (List<EjDict>) request.getAttribute("dictList");
List<String> errorList = (List<String>) request.getAttribute("errorList");
String maxCount = (String) request.getAttribute("maxCount");
String word = (String) request.getAttribute("word");
String mean = (String) request.getAttribute("mean");
if (maxCount == null) {
	maxCount = "20";
}
if (word == null) {
	word = "";
}
if (mean == null) {
	mean = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>英和辞典</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>英和辞典</h1>
<form action="EjDictServlet" method="get" class="search">
	<p>
		最大検索件数：<input type="text" name="maxCount" value="<%= maxCount %>">
	</p>
	<p>
		英単語：<input type="text" name="word" value="<%= word %>">
	</p>
	<p>
		意味検索：<input type="text" name="mean" value"<%= mean %>">
	</p>
	<p>
		<input type="submit" value="検索">
	</p>
</form>
<% if (errorList != null) { %>
	<ul>
		<% for (String error : errorList) { %>
			<li><%= error %></li>
		<% } %>
	</ul>
<% } %>

<% if (dictList != null) { %>
	<hr>

	<% for (EjDict dict : dictList) { %>
		<p class="dict-word">
			<%= dict.getWord() %>
		</p>

		<p class="dict-explanation">
			<%= dict.getExplanation() %>
		</p>

		<br>
	<% } %>
<% } %>
</body>
</html>