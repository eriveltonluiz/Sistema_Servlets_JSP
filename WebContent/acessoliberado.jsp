<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:setProperty property="*" name="calcula" />
	<h2>Seja bem vindo ao sistema jsp</h2>
	
	<a href="Usuario?acao=listar"> <img alt="" src="resources/img/usuario.jpg"></a>
	<a href="salvarProduto?acao=listar"> <img alt="" src="resources/img/produto.jpg"> </a>
</body>
</html>