<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Bem vindo, ${usuarioLogado.nome}!</h1>
	
	<!-- Op��es de Simulado -->
	<form action="GeraSimulado" method="post">
	<select name="assunto">
		<option>Selecione um assunto</option>
		<option value="geral">Geral</option>
		<c:forEach items="${assuntos}" var="assunto">
			<option ${assunto}>${assunto}</option>
		</c:forEach>
	</select>
	<button name="submit" type="submit">Gerar</button>
	</form>
	
</body>
</html>