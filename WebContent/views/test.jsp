<%@page import="com.osf.test.vo.PhotoBoardVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    pageContext.setAttribute("str","난 페이지");
    request.setAttribute("str","난리퀘스트"); 
    session.setAttribute("str","난 세션");
    application.setAttribute("str","난어플리케이션");
    Map<String,String> tMap = new HashMap<>();
    tMap.put("ttt_nnn","1");
    tMap.put("ttt_nname","길동이");
    pageContext.setAttribute("tMap",tMap);
    PhotoBoardVO pb = new PhotoBoardVO();
    pb.setPbTitle("제목임!");
    pageContext.setAttribute("pb",pb);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
나는 테스트!!
${pageScope.str}<br>
${requestScope.str}<br>
${sessionScope.str}<br>
${applicationScope.str}<br>
<%=tMap.get("ttt_nname") %>
${tMap.ttt_nname}
<%=pb.getPbTitle() %>
${pb.pbTitle}


</body>
</html>