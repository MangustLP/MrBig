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
                <script type="text/javascript" src="js/research.js"></script>
                
	</head>

	<body>
		
                    <%
                        if(session.isNew())
                        {                           
                            session.setAttribute("type","normal");
                        }
                        String type= (String)session.getAttribute("type");
                        if(type.equals("registered")) 
                        { 
                         %> <%@include file="registered.html"%> <%
                        }
                        else
                        {
                            if(type.equals("restaurateur"))
                            { 
                                %> <%@include file="restaurateur.html"%> <%
                            }
                            else
                            {
                                if(type.equals("admin"))
                                { 
                                     %> <%@include file="admin.html"%> <%
                                }
                                else
                                { 
                                     %> <%@include file="normal.html"%> <%
                                }
                            }
                        }
                    %>	
		
		<div class="title-container">
			<img src="img/Gourmet.png" alt="Gourmet.png">
		</div>
			<form action="/search" class="main-searchbox" method="get">
				<input class="main-searchbox" id="search-location" placeholder="Where you wanna eat?" autocomplete="on" name="search-location" tabindex="1" type="text">
				<input class="main-searchbox" id="search-name" placeholder="What are you looking for?" autocomplete="on" name="search-name" tabindex="2" type="text">
                                <Button class="btn btn-primary" id="search-button" type="button" value="Search">search</button>
			</form>
	</body>
</html>
