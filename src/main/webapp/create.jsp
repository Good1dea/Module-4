<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html>
<head>
<title>Create detail</title>
</head>
<body>
    <h1>Click to start detail production</h1>
    <c:if test="${empty detail}">
    	<form method="post" action="start">
    		<input type="submit" value="Create Detail">
    	</form>
    </c:if>
    <c:if test="${not empty detail}">
         <p>Detail was created with id: ${detail.getId()}</p>
         <p>production time: ${detail.getProductionTime()} seconds</p>
         <p>broken chips: ${detail.getBrokenChips()}</p>
         <p>used fuel: ${detail.getUsedFuel()}</p>
         <p>extracted fuel: ${detail.getExtractedFuel()}</p>
         <br>
         <form method="post" action="start">
             <input type="submit" value="Create New Detail">
         </form>
    </c:if>
    <br>
    </br>
    <form method="GET" action="index.html">
            <button type="submit" name="action" value="index.html" class="my-button">Back to main menu</button>
    </form>
</body>
</html>