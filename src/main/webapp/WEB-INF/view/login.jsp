<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
  <label for="username">Nume de utilizator:</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Parola:</label>
  <input type="password" id="password" name="password" required><br><br>

  <input type="submit" value="Login">
</form>
</body>
</html>
