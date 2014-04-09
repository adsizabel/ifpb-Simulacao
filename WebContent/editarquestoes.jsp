<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
	
	<div class="row" id="lista">
				<div class="span12">
					<h1>Questões Cadastradas</h1>
						<table class="table">
							<thead>
									<tr>																				
										<th>Descrição</th>
										<th>Ano</th>
										<th>Data do Cadastro</th>
									</tr>
							</thead>
							<tbody>
							<c:forEach items="${questoes}" var="questao">
								<tr>																	
									<td>${questao.enunciado}</td>
									<td>${questao.anoConcurso}</td>	
									<td><a href="?id="$(questao.id) >${questao.anoConcurso}</td>							
								</tr>
							</c:forEach>
							</tbody>
				
						</table>
						    
				</div>
			</div>
