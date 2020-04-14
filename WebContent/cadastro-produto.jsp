<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/CSS/cadastro.css">

</head>
<body>

	<div class="form-style-2">
		<div class="form-style-2-heading">

			<h3>${msg}</h3>

			<form action="salvarProduto" method="post">
				<table align="center">
					<tr>
						<td>Id:</td>
						<td><input type="text" id="id" name="id"
							value="${produto.id}"></td>
					</tr>

					<tr>
						<td>Nome Produto:</td>
						<td><input type="text" id="nomeProd" name="nomeProd"
							value="${produto.nomeProd}"></td>
					</tr>

					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="qtd" name="qtd"
							value="${produto.qtd}"></td>
					</tr>

					<tr>
						<td>Preço:</td>
						<td><input type="text" id="preco" name="preco"
							value="${produto.preco}"></td>
					</tr>

					<tr>
						<td><input type="submit" value="Salvar"></td>
					</tr>

				</table>


				<table class="paleBlueRows" align="center">
					<c:forEach items="${produtos}" var="produto">
						<tr>
							<td style="width: 150px"><c:out value="${produto.id}"></c:out></td>
							<td style="width: 150px"><c:out value="${produto.nomeProd}"></c:out></td>
							<td style="width: 150px"><c:out value="${produto.qtd}"></c:out></td>
							<td style="width: 150px"><c:out value="${produto.preco}"></c:out></td>

							<td><a
								href="salvarProduto?acao=editar&produto=${produto.id}"> <img
									alt="" src="resources/img/editar.jpg" width="20px"
									height="20px">
							</a></td>

							<td><a
								href="salvarProduto?acao=deletar&produto=${produto.id}"> <img
									alt="" src="resources/img/ecluir.png
							" width="20px"
									height="20px">
							</a></td>
						</tr>
					</c:forEach>

				</table>

			</form>
		</div>
	</div>
</body>
</html>