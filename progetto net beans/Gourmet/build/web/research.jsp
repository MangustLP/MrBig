<!DOCTYPE html>
<html>
	<head>
		<title>Gourmet</title>
		<meta charset="UTF-8">
		<meta name="author" content="Mr. Big">
		<meta name="description" content="Find the restaurant of your desires">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
  		<meta name="viewport" content="width=device-width, initial-scale=1.0">
  		<link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/research.css">
		<script src="js/jquery-2.1.1.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="js/load.js"></script>
	</head>

	<body>
		<header>
			<button type="button" id="login-button" class="btn btn-primary">Login</button>
			<button type="button" id="register-button" class="btn btn-primary">Register</button>
		</header>
		<div class="title-container">
			<div class="main-searchbox">
				<form action="/search" class="main-searchbox" method="get">
					<input class="main-searchbox" id="search-location" placeholder="Where you wanna eat?" autocomplete="on" name="search-location" tabindex="1" type="text">
					<input class="main-searchbox" id="search-name" placeholder="What are you looking for?" autocomplete="on" name="search-name" tabindex="2" type="text">
					<input class="main-searchbox" id="search-button" type="submit" value="Search">
				</form>
			</div>
		</div>
		<form class="order-box">
    			<label id="order-for">Ordina per:</label>
    			<input type="radio" class="order-list" id="rank" name="order-list" value="Rank" checked>
    			<label for="rank" class="order-list">Classifica</label>
				<input type="radio" class="order-list" id="price" name="order-list" value="Price">
				<label for="price" class="order-list">Prezzo</label>
    			<input type="radio" class="order-list" id="alphabet" name="order-list" value="Alphabetic">
    			<label for="alphabet" class="order-list">Alfabetico</label>
		</form>
		<div class="filter-box">
			<label id="price-type-list">Prezzo:</label>
			<table>
				<tr>
					<td><input type="radio" class="radio" id="low-price" name="radio" value="low-price">
					<label for="low-price" class="radio">Basso</label></td>
					<td><input type="radio" class="radio" id="medium-price" name="radio" value="medium-price">
					<label for="medium-price" class="radio">Medio</label></td>
					<td><input type="radio" class="radio" id="high-price" name="radio" value="high-price" checked>
					<label for="high-price" class="radio">Alto</label></td>
					<td><input type="radio" class="radio" id="every-price" name="radio" value="every-price" checked>
					<label for="every-price" class="radio">Qualsiasi</label></td>
				</tr>
			</table>
			<label class="food-list">Tipologia di cucina:</label>
			<div class="checkbox">
			  	<label>
			  		<input type="checkbox" name="tipologia-cucina" class="food-list" id="cucina-italiana" value="cucina-italiana">Cucina Italiana
			  	</label>
			</div>
			<div class="checkbox">
			  <label>
			    <input type="checkbox" name="tipologia-cucina" class="food-list" id="fast-food" value="fast-food">Fast Food
			  </label>
			</div>
			<div class="checkbox">
			  <label>
			    <input type="checkbox" name="tipologia-cucina" class="food-list" id="slow-food" value="slow-food">Slow Food
			  </label>
			</div>
			<div class="checkbox">
			  <label>
			    <input type="checkbox" name="tipologia-cucina" class="food-list" id="cucina-tipica" value="cucina-tipica">Cucina tipica
			  </label>
			</div>
			<div class="checkbox">
			  <label>
			    <input type="checkbox" name="tipologia-cucina" class="food-list" id="pizzeria" value="pizzeria">Pizzeria
			  </label>
			</div>
			<div class="checkbox">
			  <label>
			    <input type="checkbox" name="tipologia-cucina" class="food-list" id="bistro" value="bistro">Bistrò
			  </label>
			</div>
			<br/>
			<button type="reset" id="reset-button" class="btn btn-danger">Reset</button>
		</div>
	</body>
</html>
