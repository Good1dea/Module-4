<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Statistics</title>
</head>
<body>
<c:if test="${empty detail}">
	<h1>Total Factory Statistics</h1>

	<p>Total number of details produced: ${total_details}</p>
	<p>Total number of broken chips: ${total_broken_chips}</p>
	<p>Total amount of extracted fuel: ${total_extracted_fuel}</p>
	<p>Total amount of used fuel: ${total_used_fuel}</p>
	</c:if>

	<c:if test="${not empty detail}">
	<h1>Detail Statistics</h1>
        <table border="2">
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Production time(seconds)</th>
                <th>Extracted Fuel</th>
                <th>Used Fuel</th>
                <th>Broken Chips</th>
            </tr>
            <tr>
                <td>${detail.getId()}</td>
                <td>${detail.getDate()}</td>
                <td>${detail.getProductionTime()}</td>
                <td>${detail.getExtractedFuel()}</td>
                <td>${detail.getUsedFuel()}</td>
                <td>${detail.getBrokenChips()}</td>
            </tr>
        </table>
    </c:if>

    <h3>Select a detail for more information:</h3>
    <form method="GET" action="stats">
        <select name="id">
          <c:forEach var="id" items="${all_id}">
            <option value="${id}">${id}</option>
          </c:forEach>
        </select>
        <br>
        <input type="submit" value="Select">
    </form>
    <br>
    </br>
    <form method="GET" action="index.html">
        <button type="submit" name="action" value="index.html" class="my-button">Back to main menu</button>
    </form>
</body>
</html>