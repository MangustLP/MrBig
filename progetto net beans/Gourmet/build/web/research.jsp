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
    			<label for="rank" class="order-list">N° Classifica</label>
				<input type="radio" class="order-list" id="price" name="order-list" value="Price">
				<label for="price" class="order-list">Prezzo</label>
    			<input type="radio" class="order-list" id="alphabet" name="order-list" value="Alphabetic">
    			<label for="alphabet" class="order-list">Alfabetico</label>
		</form>
		<div class="filter-box">
			<label id="price-type-list">Prezzo:</label>
			<table>
				<tr>
					<td><input type="radio" class="price-list" id="low-price" name="price-list" value="low-price">
					<label for="low-price" class="price-list">Basso</label></td>
					<td><input type="radio" class="price-list" id="medium-price" name="price-list" value="medium-price">
					<label for="medium-price" class="price-list">Medio</label></td>
					<td><input type="radio" class="price-list" id="high-price" name="price-list" value="high-price" checked>
					<label for="high-price" class="price-list">Alto</label></td>
					<td><input type="radio" class="price-list" id="every-price" name="price-list" value="every-price" checked>
					<label for="every-price" class="price-list">Qualsiasi</label></td>
				</tr>
			</table>
			<label class="food-list">Tipologia di cucina:</label>
			<select class="food-list">
				<option>Cucina Italiana</option>
				<option>Fast Food</option>
				<option>Slow Food</option>
				<option>Cucina Tipica</option>
				<option>Pizzeria</option>
				<option>Bistrò</option>
			</select>
		</div>
	</body>
</html>
