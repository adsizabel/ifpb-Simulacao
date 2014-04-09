<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% int count = 0; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" charset="utf-8"
	src="scripts/jquery-1.11.0.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<div>
		<span id="timer">00:00:00</span>
	</div>

	<form>
		<input type="hidden" name="tempo" value="" id="input-tempo" />
		<c:forEach items="${simulado.questoes}" var="questao">
			<p>
				${questao.enunciado}<br>
				<c:forEach items="${questao.alternativas}" var="alternativa">
						<input type="radio" name="${questao.codQuestao}"
							value="${alternativa.id}"
							id="${questao.codQuestao}-${alternativa.id}" disabled />
						<label for="${questao.codQuestao}-${alternativa.id}">${alternativa.descricao}</label>
					</div>
				</c:forEach>
			</p>
		</c:forEach>
		<input type="submit" value="Enviar Questionario" />
	</form>

	<script type="text/javascript">
		$(document).ready(function() {
			var currentSeconds = 0;

			function chrono() {
				currentSeconds++;
				var segundos = Math.floor(currentSeconds % 60); 
				var minutos = Math.floor((currentSeconds / 60) % 60);
				var horas = Math.floor(((currentSeconds / 60) / 60) % 60);
				
				if (segundos < 10) {
					segundos = "0" + segundos;
				}
				if (minutos < 10) {
					minutos = "0" + minutos;
				}
				if (horas < 10) {
					horas = "0" + horas;
				}
				
				$("#timer").html(horas + ":" + minutos + ":" + segundos);
				$("#input-tempo").attr("value", currentSeconds);
			}

			timerID = setInterval(chrono, 1000);

		});
	</script>

</body>
</html>