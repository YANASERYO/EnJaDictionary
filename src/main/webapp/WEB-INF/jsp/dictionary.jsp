<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.EjDict,modelEjDictLogic,java.util.List" %>
<!--<% -->
<!--List<dict> dictList =-->
<!--(List<dict>application.getAttribute("dictList");-->
<!--%>-->
<!--どっち？-->
<!--<%-->
<!--Phrase p = (Phrase)rquest.getAttribute("phrase");-->
<!--%>-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>英和辞典</title>
</head>
<body>
<h1>英和辞典</h1>
<form action="search" method="get">
<input type="text" name="resultCount">
<input type="text" name="word" >
<input type="submit" value="検索">
</form>
<% for(Dict dict : dictList){ %>
<p><%= dict.getWord() %>:<%= dict.getExplanation() %>
<% } %>
</body>
</html>