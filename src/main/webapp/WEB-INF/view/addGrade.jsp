<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<html>
<head>
    <title>Adăugare Notă</title>
</head>
<body>
<h2>Adăugați o Notă Nouă</h2>
<form method="post">
    <input type="hidden" name="studentId" value="${studentId}" />
    <label for="studentId">ID Student:</label>
    <input type="number" id="studentId" placeholder="${studentId}" name="studentId" required><br><br>

    <label for="disciplina">Alege Disciplina:</label>
    <select id="disciplina" name="disciplina">
        <c:forEach items="${discipline}" var="disciplina">
            <option value="${disciplina.id}">${disciplina.nume}</option>
        </c:forEach>
    </select><br><br>

    <label for="valoareNota">Valoare Notă:</label>
    <input type="number" id="valoareNota" name="valoareNota" min="1" max="10" required><br><br>

    <input type="submit" value="Adaugă Nota">
</form>
</body>
</html>
