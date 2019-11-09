<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Crear cuenta</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css/signUp.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->
<!-- web font -->
<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
<!-- //web font -->
</head>
<body>
	<!-- main -->
	<div class="main-w3layouts wrapper">
		<h1>Crear cuenta</h1>
		<div class="main-agileinfo">
			<div class="agileits-top">
				<form action="signUp.html" method="POST">
					<input class="text email" type="text" name="name" placeholder="Nombre" required value="b">
					<input class="text email" type="text" name="surname" placeholder="Apellido" required value="b">
					<input class="text" type="text" name="username" placeholder="Usuario" required value="b">
					<input class="text email" type="email" name="email" placeholder="Email" required value="b@gmail.com">
					<input class="text" type="password" name="password" placeholder="Contraseña" required value="b">
					<input class="text w3lpass" type="password" name="password" placeholder="Confirma tu contraseña" required value="b">
					<div class="wthree-text">
						<label class="anim">
							<input type="checkbox" class="checkbox" required="" checked="true">
							<span>Acepto los términos y condiciones</span>
						</label>
						<div class="clear"> </div>
					</div>
					<input type="submit" value="REGISTRARME">
				</form>
				<p>¿Ya tienes cuenta? <a href="login.jsp">¡Inicia Sesión!</a></p>
			</div>
		</div>
		<!-- copyright -->
		<div class="colorlibcopy-agile">
			<p>© 2019 Grupo XX. All rights reserved | Design by <a href="https://colorlib.com/" target="_blank">Grupo XX</a></p>
		</div>
		<!-- //copyright -->
		<ul class="colorlib-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<!-- //main -->
</body>
</html>