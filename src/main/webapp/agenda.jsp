<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contactos");
	for(int i = 0; i < lista.size(); i++){
		out.println(lista.get(i).getIdcon());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getTelefone());
		out.println(lista.get(i).getEmail());
	}
%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<title>Agenda de Contactos</title>
<link href="imagens/favicon.png" rel="icon">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contactos</h1>
	<a href="novo.html" class="Botao1">Novo Contacto</a>
	<table id="tabela">
		<thead>
			<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Telefone</th>
			<th>E-mail</th>
			
			</tr>
		</thead>
		
		<tbody>
			<%for (int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><%=lista.get(i).getTelefone()%></td>
					
				</tr>
			
			<%} %>
		</tbody>
	
	</table>
</body>
</html>