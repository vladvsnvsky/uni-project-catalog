<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<html>
<head>
  <title>Editare Notă</title>
</head>
<body>
<h2>Editare Notă</h2>

<c:choose>
  <c:when test="${nota != null}">
    <form action="editGrade" method="post">
      <input type="hidden" name="notaId" value="${nota.id}" />

      <label for="valoareNota">Valoare Notă:</label>
      <input type="number" id="valoareNota" name="valoareNota" value="${nota.valoare}" min="1" max="10" required /><br><br>

      <input type="submit" value="Actualizează Nota" />
    </form>
  </c:when>
  <c:otherwise>
    <p>Nota nu a fost găsită.</p>
  </c:otherwise>
</c:choose>

</body>
</html>
