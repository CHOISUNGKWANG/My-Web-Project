<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.sendRedirect("main.do");
	session.setAttribute("mid", "tjdrhkd1234a");
	session.setAttribute("mnick", "최성광");
	//session.invalidate();
%>