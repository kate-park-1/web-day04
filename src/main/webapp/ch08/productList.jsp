<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
    <body>
        <h2>상품 목록</h2>
        <hr>
        <table border="1">
            <tr>
                <td>제품ID</td>
                <td>제품명</td>
                <td>제품가격</td>
            </tr>
            <c:forEach var="p" items="${products}">
            <tr>
                <td><a href="/pcontrol?action=info&id=${p.id}">${p.id}</a></td>
                <td><a href="/pcontrol?action=info&id=${p.id}">${p.name}</a></td>
                <td>${p.price}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>