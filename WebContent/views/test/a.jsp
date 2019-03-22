<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
TestSerblet에서 보내준 map : ${map }<br>
TestSerblet에서 보내준 list : ${list }<br>
<%=request.getParameter("name") %>
<%
Map<String,String> map = (Map<String,String>)request.getAttribute("map");
out.println(map.get("특기"));
%>

</body>

</html>