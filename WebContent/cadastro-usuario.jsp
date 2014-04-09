<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuário</title>
</head>
<body>
	<h1>Cadastra Usuário</h1>
	<form action="cadastrar" method="post">
		nome: <input type="text" name="nome"> <br /> username <input
			type="text" name="login"> <br /> senha: <input
			type="password" name="senha"> <br /> tipo: <input type="text"
			name="tipo"><br /> <input type="submit"
			value="Cadastrar Usuário">
	</form>
</body>
</html>