<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<html>
<head>
  <title>Catalog</title>
</head>
<body>
<h2>Notele Studentului</h2>
<c:choose>
  <c:when test="${not empty note}">
    <table border="1">
      <tr>
        <th>ID Notă</th>
        <th>Nume student</th>
        <th>Valoare</th>
        <th>Data</th>
        <th>Disciplină</th>
        <th>Acțiuni</th> <!-- Coloană pentru acțiuni, cum ar fi ștergerea -->
      </tr>
      <c:forEach var="nota" items="${note}">
        <tr>
          <td>${nota.id}</td>
          <td>${nota.student.nume} ${nota.student.prenume}</td>
          <td>${nota.valoare}</td>
          <td><fmt:formatDate value="${nota.data}" pattern="dd-MM-yyyy"/></td>
          <td>${nota.disciplina.nume}</td>
          <td>
            <c:if test="${sessionScope.userType == 'profesor'}">
              <form action="deleteGrade" method="post">
                <input type="hidden" name="notaId" value="${nota.id}" />
                <input type="submit" value="Șterge" onclick="return confirm('Sunteți sigur că doriți să ștergeți această notă?');"/>
              </form>
              <form action="editGrade" method="get">
                <input type="hidden" name="notaId" value="${nota.id}" />
                <input type="submit" value="Edit"/>
              </form>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:when>
  <c:otherwise>
    <p>Nu există note înregistrate pentru acest student.</p>
  </c:otherwise>
</c:choose>
</body>
</html>
