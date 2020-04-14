<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel = "stylesheet" href="resources/CSS/estilo.css">
</head>
<body>
<div class = "login-page">
   <div class = "form">
    <h2> login do usuário </h2>
	<form action="LoginServlet" method="post" class="login-form">
		<label>Login:</label> 
		<input type="text" id="login" name="login" class="input-field"/>
		 <br />
		  <label>Senha:</label> 
		  <input type="password" id="senha" name="senha" class="input-field"/>
		   <br /> 
			<button type="submit" value="Logar">Logar</button>
	</form>
	</div></div>
</body>
</html>