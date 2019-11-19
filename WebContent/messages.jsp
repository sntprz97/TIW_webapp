<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Mis mensajes</title>
	
	<!--styles-->
	<style>
		body {
			background: linear-gradient(to top, #F8694A, #ff9233);
			margin: 0;
			min-height: 100vh;
			padding: 50px 5%;
		}
		.column {
			display: inline-flex;
			align-items: center;
			width: 49%;
			flex-direction: column;
		}
		.heading {
			margin-bottom: 40px;
			color: white;
		}
		.messageForm {
			width: 80%;
		    background-color: rgba(0,0,0,0.6);
		    opacity: 1;
		    display: flex;
		    align-items: center;
		    flex-direction: column;
		    padding: 2em 1em;
		    border-radius: 20px;
		    padding-bottom: 20px;
		}
		.form-input:last-child {
			margin-bottom: 50px;
		}
		.form-input {
			padding: 1rem;
			border: 1px solid white;
			margin-bottom: 30px;
			background-color: transparent;
   			width: 80%;
   			color: white;
		}
		::placeholder {
			color: white;
			opacity: 1;
		}
		.form-submit {
			background-color: #ff9233;
		    padding: 1rem 2rem;
		    display: flex;
		    justify-content: center;
		    border: none;
		    width: 80%;
		    color: white;
		    font-size: .9em;
		}
		.messages {
			padding: 1rem;
			border-radius: 30px;
			background-color: rgba(0,0,0,0.6);
			margin-bottom: 30px;
		}
		.message-content {
			color: white;
		}
		
	</style>

</head>
<body>
	<div class="column">
		<h1 class="heading">Enviar mensaje</h1>
		<form action="enviarMensaje.html" method="POST" class="messageForm">
			<% if (session.getAttribute("Status") == "buyer") { %>
				<input type="text" class="form-input" name="receiverId" placeholder="Email del receptor"></input>
			<%	} %>
			<input type="text" class= "form-input" name="message" placeholder="Escribe tu mensaje"></input>
			<input type="submit" class="form-submit" placeholder="Enviar"></input>
		</form>
		<% if (session.getAttribute("Status") == "seller") { %>
				<p style="color:white;">Este mensaje será difundido a todos los compradores de la plataforma</p>
		<%	} %>
	</div>
	<div class="column">
		<h1 class="heading">Mis mensajes</h1>
		<c:forEach items="${messages}" var="m">
			<div class="messages">
				<p class="message-content">Emisor: ${m.getSenderId()}<p>
				<p class="message-content">Mensaje: ${m.getText()}</p>		
			</div>	
		</c:forEach>
	</div>
</body>
</html>