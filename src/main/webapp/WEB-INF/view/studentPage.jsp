<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<html>
<head>
  <title>Pagina Studentului</title>
</head>
<body>
<h2>Informații Student</h2>
<p>Nume: ${student.nume}</p>
<p>Prenume: ${student.prenume}</p>

<h3>Medii pe Discipline</h3>
<table border="1">
  <tr>
    <th>Disciplină</th>
    <th>Medie</th>
  </tr>
  <c:forEach var="medie" items="${medii}">
    <tr>
      <td>${medie.disciplina}</td>
      <td>${medie.valoare}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
