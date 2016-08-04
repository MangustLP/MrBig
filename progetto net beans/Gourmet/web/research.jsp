<!DOCTYPE html>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

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
                 <%!
                                String Where;
                                String What;
                                String url="";
                                String username="";
                                String password="";
                                String query="";
                                Connection conn;
                                Statement stmt;
                                ResultSet rs;
                %>
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
		<div class="body-container">
			<div class="form-group" id="order-box">
  				<label id="order-for" for="order-list">Ordina per:</label>
  				<select class="form-control" id="order-list">
    			<option id="rank" value="Rank">Classifica</option>
    			<option id="price" value="Price">Prezzo</option>
    			<option id="alphabet" value="Alphabetic">Alfabetico</option>
  				</select>
			</div>
			<aside class="filter-box">
					<label id="price-list">Prezzo:</label>
					<br/>
					<label class="radio-inline"><input type="radio" id="low-price" name="radio" value="low-price">Basso</label>
					<label class="radio-inline"><input type="radio" id="medium-price" name="radio" value="medium-price">Medio</label>
					<label class="radio-inline"><input type="radio" id="high-price" name="radio" value="high-price">Alto</label>
					<label class="radio-inline"><input type="radio" id="every-price" name="radio" value="every-price" checked>Qualsiasi</label>
					<br/>
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
				    <input type="checkbox" name="tipologia-cucina" class="food-list" id="bistro" value="bistro">Bistr&ograve
				  </label>
				</div>
				<br/>
				<button type="reset" id="reset-button" class="btn btn-danger">Reset</button>
			</aside>
			<div class="corporicerca">
                            <%
                               /* Connection conn=DriverManager.getConnection(url, username, password);
                                
                                Where=request.getParameter("Where");
                                What=request.getParameter("What");
                                
                                //creazione query
                                
                                stmt=conn.createStatement();
                                rs=stmt.executeQuery(query);  */
                            %>
                            
                            <table class="research_table">
                                <%
                                 //while(rs.next())
                                 {
                                 %> <tr class="research_table_row">
                                        <img class="research_image" src="ristorante1.jpg"> 
                                        <h1 class="research_name"> <%//rs.getString("NAME");%>RIstorante prova</h1>
                                        <div class="coordinates"> <%//rs.getString("ID_COORDINATE");%>via qualcosa qualdove n2</div>
                                        <div class="value"> <%//rs.getString("GLOBAL_VALUE INTEGER");%>5/7</div>                                        
                                    </tr> <%
                                 }
                                %>
                            </table>
                            
			</div>
		</div>
	</body>
</html>
