<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
  <a href="${pageContext.request.contextPath}/logout" style="color: red;">|Log Out|</a>
  <a href="javascript:history.back()">|<- Go Back|</a>
  <c:if test="${sessionScope.userType == 'profesor'}">
    <a href="${pageContext.request.contextPath}/students">|Adauga note|</a>
  </c:if>
  <a href="${pageContext.request.contextPath}/dashboard">|Notele tale|</a>
  <!-- Puteți adăuga aici alte linkuri sau butoane, dacă este necesar -->
</nav>