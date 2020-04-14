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

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
</head>
<body>
	<a href="acessoliberado.jsp">Iniciar</a>
	<a href="index.jsp">Sair</a>
	<h1>Cadastro de usuário</h1>

	<div class="form-style-2">
		<div class="form-style-2-heading">

			<h3>${msg}</h3>
			<form action="Usuario" method="post" id="formUser"
				onsubmit="return validarCampos()? true : false;" enctype="multipart/form-data">
				<table align="center">

					<tr>
						<td>Código:</td>
						<td><input type="text" id="id" name="id" value="${user.id}"></td>

						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep();" value="${user.cep}"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}"></td>

						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}"></td>
					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>

						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}"></td>

					</tr>

					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}"></td>

						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado}"></td>
					</tr>

					<tr>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}"></td>
					</tr>

					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto" value="Foto"></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" /> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'Usuario?acao=cancelar'" /></td>
					</tr>
				</table>

			</form>
		</div>
	</div>


	<table class="paleBlueRows" align="center">
		<tr>
			<th>ID</th>
			<th>Login</th>
			<th>Foto</th>
			<th>Nome</th>
			<th>Telefone</th>
			<th>Excluir</th>
			<th>Editar</th>
			<th>Fones</th>
		</tr>
		<c:forEach items="${usuarios}" var="user">

			<tr>

				<td style="width: 150px"><c:out value="${user.id}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
				<td><img alt="" src='<c:out value="${user.tempFotoUser}"/>' width="40px" height="40px"></td>
				<td style="width: 150px"><c:out value="${user.nome}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.telefone}"></c:out></td>
				

				<td><a href="Usuario?acao=delete&user=${user.id}"> <img
						title="Excluir" src="resources/img/ecluir.png" width="20px"
						height="20px"></a></td>

				<td><a href="Usuario?acao=editar&user=${user.id}"> <img
						alt="" src="resources/img/editar.jpg" width="20px" height="20px"></a></td>

				<td><a href="salvarTelefone?acao=addfone&user=${user.id}">
						<img alt="" src="resources/img/telefone.png" width="20px"
						height="20px">
				</a></td>
			</tr>

		</c:forEach>

	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert("informe o login");
				return false;
			}

			else if (document.getElementById("senha").value == '') {
				alert('Informe a senha');
				return false;
			}

			else if (document.getElementById("nome").value == '') {
				alert('Informe o nome');
				return false;
			}

			else if (document.getElementById("telefone").value == '') {
				alert('Informe o telefone');
				return false;
			}
			return true;
		}

		function consultaCep() {
			var cep = $("#cep").val();
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} else {
							alert("CEP não encontrado.");
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');
						}
					});
		}
	</script>
</body>
</html>