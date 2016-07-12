<!DOCTYPE html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en" ><![endif]-->
<!--[if gt IE 8]><!--><html class="no-js" ><!--<![endif]-->
<html>
	<head>
		<title>Gourmet</title>
		<meta charset="UTF-8">
		<meta name="author" content="Mr. Big">
		<meta name="description" content="Find the restaurant of your desires">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
  		<meta name="viewport" content="width=device-width, initial-scale=1.0">
  		<link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<script src="js/jquery-2.1.1.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="js/load.js"></script>
	</head>

	<body background="img/pizza-wallpaper.jpg">
		<header>
                    <%
                        if(session.isNew())
                        {
                            //
                            session.setAttribute("type","normale");
                        }
                        String type= (String)session.getAttribute("type");
                        switch (type)
                        {
                            case "registrato":// caricamento men� registato
                                break;
                            case "ristoratore":// caricamento men� ristoratore
                                break;
                            case "amministratore":// caricamento men� admin
                            default: // caricamento men� utente normale
                                break;
                        }                        
                    %>
			<button type="button" id="login-button" class="btn btn-primary">Login</button>
			<button type="button" id="register-button" class="btn btn-primary">Register</button>
		</header>
		<div class="title-container">
			<img src="img/Gourmet.png">
		</div>
		<div class="main-searchbox">
			<form action="/search" class="main-searchbox" method="get">
				<input class="main-searchbox" id="search-location" placeholder="Where you wanna eat?" autocomplete="on" name="search-location" tabindex="1" type="text">
				<input class="main-searchbox" id="search-name" placeholder="What are you looking for?" autocomplete="on" name="search-name" tabindex="2" type="text">
				<input class="main-searchbox" id="search-button" type="submit" value="Search">
			</form>
		</div>
	</body>
</html>
