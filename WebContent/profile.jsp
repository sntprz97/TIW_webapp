<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Mi Perfil</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<link type="text/css" rel="stylesheet" href="css/profile.css" />

</head>
<body>
	<!-- HEADER -->
	<header>
		<!-- top Header -->
		<div id="top-header">
			<div class="container">
				<div class="pull-left">
					<span>Welcome to TIW-shop!</span>
				</div>
				<div class="pull-right">
					<ul class="header-top-links">
						<li><a href="#">Store</a></li>
						<li><a href="#">Newsletter</a></li>
						<li><a href="#">FAQ</a></li>
						<li class="dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">ENG <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">English (ENG)</a></li>
								<li><a href="#">Russian (Ru)</a></li>
								<li><a href="#">French (FR)</a></li>
								<li><a href="#">Spanish (Es)</a></li>
							</ul>
						</li>
						<li class="dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">USD <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">USD ($)</a></li>
								<li><a href="#">EUR (â‚¬)</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- /top Header -->

		<!-- header -->
		<div id="header">
			<div class="container">
				<div class="pull-left">
					<!-- Logo -->
					<div class="header-logo">
						<a class="logo" href="index.jsp">
							<img src="./img/logo.png" alt="">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Search -->
					<div class="header-search">
						<form>
							<input class="input search-input" type="text" placeholder="Enter your keyword">
							<select class="input search-categories">
								<option value="0">All Categories</option>
								<option value="1">Category 01</option>
								<option value="1">Category 02</option>
							</select>
							<button class="search-btn"><i class="fa fa-search"></i></button>
						</form>
					</div>
					<!-- /Search -->
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<strong class="text-uppercase">My Account <i class="fa fa-caret-down"></i></strong>
							</div>
							<a href="login.jsp" class="text-uppercase">Login</a> / <a href="signUp.jsp" class="text-uppercase">Join</a>
							<ul class="custom-menu">
								<li><a href="#"><i class="fa fa-user-o"></i> My Account</a></li>
								<li><a href="#"><i class="fa fa-heart-o"></i> My Wishlist</a></li>
								<li><a href="#"><i class="fa fa-exchange"></i> Compare</a></li>
								<li><a href="checkout.html"><i class="fa fa-check"></i> Checkout</a></li>
								<li><a href="login.jsp"><i class="fa fa-unlock-alt"></i> Login</a></li>
								<li><a href="signUp.jsp"><i class="fa fa-user-plus"></i> Create An Account</a></li>
							</ul>
						</li>
						<!-- /Account -->

						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-shopping-cart"></i>
									<span class="qty">0</span>
								</div>
								<strong class="text-uppercase">My Cart:</strong>
								<br>
								<span>0$</span>
							</a>
							<div class="custom-menu">
								<div id="shopping-cart">
									<div class="shopping-cart-list">
										<div class="product product-widget">
											<div class="product-thumb">
												<img src="./img/thumb-product01.jpg" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">$32.50 <span class="qty">x3</span></h3>
												<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
											</div>
											<button class="cancel-btn"><i class="fa fa-trash"></i></button>
										</div>
										<div class="product product-widget">
											<div class="product-thumb">
												<img src="./img/thumb-product01.jpg" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">$32.50 <span class="qty">x3</span></h3>
												<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
											</div>
											<button class="cancel-btn"><i class="fa fa-trash"></i></button>
										</div>
									</div>
									<div class="shopping-cart-btns">
										<button class="main-btn">View Cart</button>
										<a  href="checkout.html"><button class="primary-btn">Checkout <i class="fa fa-arrow-circle-right"></i></button></a>
									</div>
								</div>
							</div>
						</li>
						<!-- /Cart -->

						<!-- Mobile nav toggle-->
						<li class="nav-toggle">
							<button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
						</li>
						<!-- / Mobile nav toggle -->
					</ul>
				</div>
			</div>
			<!-- header -->
		</div>
		<!-- container -->
	</header>
	<!-- /HEADER -->
	<img class="dashboard" src="./img/dashboard.png" alt="">
	<% HttpSession sesion = request.getSession();
	if(sesion.getAttribute("Usuario")!= null){ %>
	<div id="fh5co-tours" class="fh5co-section-gray">
			<div class="container"> 
				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="heading-title">Perfil</h2>
					</div>
					<div class="col-md-6 animate-box">
						<form action="cambiarPerfil.html" method="post" enctype="multipart/form-data">
                        <table class="table">
                            <tbody> 
                                <tr>                                
                                    <td>Nombre:</td>
                                    <td><input name="nombre" type="text" value="${perfilA}" required></td>
                                </tr> 
                                <tr>                                
                                    <td>Apellido:</td>
                                    <td><input name="apellido" type="text" value="${perfilB}" required></td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td><span class="email"><c:out value="${perfilC}"/></span></td>
                                </tr>
                                <tr>
                                    <td>Nombre de usuario:</td>
                                    <td><input name="username" type="text" value="${perfilD}" required></td>
                                </tr>
                                <tr>
                                    <td>Dirección:</td>
                                    <td><input name="direccion" type="text" value="${perfilE}" required></td>
                                </tr>
                                <tr>
      								<td><input type="submit" value="Guardar cambios" required><br></td>
								</tr>                            
                            </tbody>
                        </table>
                        </form>                                             
                    </div>
					<div class="col-md-6 animate-box">
						<table class="table">
                            <tbody>        
                                <tr>
                                    <th scope="row" ><a href="cerrarSesion.html">Cerrar Sesión</a></th>   
                                </tr>
                                <tr>
                                    <th scope="row" ><button onclick="displayPopup()" >Borrar Cuenta</button></th>   
                                </tr>                            
                            </tbody>
                        </table>
					</div>
				</div>
			</div>
		</div>
	<%}else{ %>
		<h1> Para visitar su perfil, antes debe <a href="signUp.jsp" id="Registro">registrarse</a> o <a href="login.jsp" id="Registro">iniciar sesión</a></h1>
  	<%} %>
  	<div class="cd-popup" role="alert" id="showpopup">
		<div class="cd-popup-container">
			<p>¿Está segur@ de que quiere darse de baja de la aplicación?</p>
				<ul class="cd-buttons">
					<li><form id="borrar-cuenta" method="get" action="borrarCuenta.html"><a onclick="document.getElementById('borrar-cuenta').submit()">Sí</a></form></li>
					<li><a onclick="hidePopup()">No</a></li>
				</ul>
			<a href="profile.jsp" class="cd-popup-close img-replace" onclick="hidePopup()">Cerrar</a>
		</div> <!-- cd-popup-container -->
	</div> <!-- cd-popup -->
	
	<!-- jQuery -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script src="js/script.js"></script>
</body>
</html>