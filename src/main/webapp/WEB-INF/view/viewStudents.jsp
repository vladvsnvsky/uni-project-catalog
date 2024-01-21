<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<html>
<head>
    <title>Vizualizare Studenți</title>
</head>
<body>
<h2>Lista Studenților</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nume</th>
        <th>Prenume</th>
        <th>Operațiuni</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.id}</td>
            <td>${student.nume}</td>
            <td>${student.prenume}</td>

            <td>
                <form action="addGrade" method="get">
                    <input type="hidden" name="studentId" value="${student.id}" />
                    <input type="submit" value="Adaugă notă" />
                </form>
                <form action="studentPage" method="get">
                    <input type="hidden" name="studentId" value="${student.id}" />
                    <input type="submit" value="Vezi medii" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
