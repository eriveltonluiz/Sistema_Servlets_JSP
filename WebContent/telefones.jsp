<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>

<link rel="stylesheet" href="resources/CSS/cadastro.css">

<style type="text/css">
body {
	text-align: center;
}
</style>


</head>
<body>

	<h1>Cadastro de telefones</h1>

	<div class="form-style-2">
		<div class="form-style-2-heading">

			<h3>${msg}</h3>
			<form action="salvarTelefone" method="post" id="formTelefone"
				onsubmit="return validarCampos()? true : false;">
				<table align="center">

					<tr>
						<td>Código:</td>
						<td><input type="text" id="id" name="id" readonly="readonly"
							value="${userEscolhido.id}"></td>

						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" readonly="readonly"
							value="${userEscolhido.nome}"></td>
					</tr>

					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"></td>

						<td></td>
						<td><select id="tipo" name="tipo">
								<option>Casa</option>
								<option>Celular</option>
								<option>Contato</option>

						</select></td>
					</tr>

					<tr>

						<td></td>
						<td><input type="submit" value="Salvar" /></td>
					</tr>
				</table>

			</form>
		</div>
	</div>


	<table class="paleBlueRows" align="center">
		<tr>
			<th>ID</th>
			<th>Número</th>
			<th>Tipo</th>
			<th>Excluir</th>
		</tr>
		<c:forEach items="${telefones}" var="fone">

			<tr>

				<td style="width: 150px"><c:out value="${fone.idTel}"></c:out></td>
				<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
				<td style="width: 150px"><c:out value="${fone.tipo}"></c:out></td>
				<td style="width: 150px"><c:out value="${fone.usuario}"></c:out></td>

				<td><a href="salvarTelefone?acao=deletar&foneId=${fone.idTel}"> <img alt="" src="resources/img/ecluir.png"></a></td>
				
			</tr>

		</c:forEach>

	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert("informe o número");
				return false;
			} else if (document.getElementById("tipo").value == '') {
				alert("informe o tipo");
				return false;
			}

			return true;
		}
	</script>
</body>
</html>