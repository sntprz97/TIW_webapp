<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Vendedor</title>

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
	<link type="text/css" rel="stylesheet" href="css/seller.css" />
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
								<li><a href="#">EUR (â¬)</a></li>
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
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<strong class="text-uppercase">My products <i class="fa fa-caret-down"></i></strong>
							</div>
							<a href="#" class="text-uppercase"><%= session.getAttribute("Usuario") %></a>
							<ul class="custom-menu">
								<li><a href="#"><i class="fa fa-user-o"></i> My Account</a></li>
								<li><a href="#"><i class="fa fa-heart-o"></i> My Wishlist</a></li>
								<li><a href="#"><i class="fa fa-exchange"></i> Compare</a></li>
								<li><a href="checkout.html"><i class="fa fa-check"></i> Checkout</a></li>
								<li><a href="login.jsp"><i class="fa fa-unlock-alt"></i> Login</a></li>
								<li><a href="signUp.jsp"><i class="fa fa-user-plus"></i> Create An Account</a></li>
								<li><a href="#"><i class="fa fa-times-circle"></i> Unsubscribe</a></li>
							</ul>
						</li>
						<!-- /Account -->
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
	<div id="fh5co-tours" class="fh5co-section-gray">
			<div class="container"> 
				<div class="row">
					<button class="primary-btn action-btn active" id="add-button" onclick="changeTab('add')"><i class="fa fa-plus-square"></i>  Add product</button>
					<button class="primary-btn action-btn " id="edit-button" onclick="changeTab('edit')"><i class="fa fa-edit"></i>  Edit product</button>
					<button class="primary-btn action-btn " id="remove-button" onclick="changeTab('remove')"><i class="fa fa-trash"></i>  Remove product</button>
				</div>
				<div class="action-container">
					<form class="action" action="addProduct" method="post" id="add" style="display: block;">
						<div class="product product-details clearfix">
							<div class="col-md-6">
								<div id="product-main-view">
									<div class="product-view">
										<img src="./img/default-product.png" id="product-image" onclick="document.getElementById('image-selector').click()" style="cursor: pointer;">
										<input type="file" id="image-selector" style="display: none">
										<input type="text" name="imagen" id="submit-image" style="display: none;">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="product-body">
									<input class="product-name h2" type="text" placeholder="Product name" name="nombreProducto">
									<input class="product-price h3" type="number" min="1" step="0.01" placeholder="Product price" name="precio">
									<p><strong>Brand:</strong><input class="brand-input" type="text" placeholder="Type the product brand" name="marca"></p>
									<textarea class="description" placeholder="Type the description below..." name="desc"></textarea>
									<div class="product-options">
										<ul class="size-option" id="sizes" style="display: flex; align-items: center;">
											<label>
												<input type="text" id="size-value" class="size-input" placeholder="Add size">
												<input type="text" id="size-values" style="display: none;"  name="talla">
												<i class="fa fa-plus" onclick="addSize()" style="position: relative; left: -30px; cursor: pointer;"></i>
											</label>
											<li style="margin: 0 8px 0 0 !important;"><span class="text-uppercase">Sizes:</span></li>
										</ul>
									</div>
		
									<div class="product-btns" style="position: relative; display: flex;">
										<div class="qty-input">
											<span class="text-uppercase">STOCK: </span>
											<input class="input" type="number" style="box-shadow: unset !important; background: white;" name="cantidad">
										</div>
										<span class="add-btn" onclick="addProduct()">ADD</span>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div class="action" id="edit">
						EDIT
					</div>
					<div class="action" id="remove">
						REMOVE
					</div>
				</form>
			</div>
		</div>
		<!-- jQuery Plugins -->
		<script src="js/main.js"></script>
		<script src="js/seller.js"></script>
</body>
</html>