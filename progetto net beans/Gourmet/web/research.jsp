<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.RistoranteEBJ"%>
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
                                Boolean Every= false;     
                                Boolean Every2= false;
                                Boolean Low=false;
                                Boolean Medium=false;
                                Boolean High=false;
                                Boolean Italian=false;
                                Boolean Slow=false;
                                Boolean Fast=false;
                                Boolean Tipical=false;
                                Boolean Pizzeria=false;
                                Boolean Bistro=false;
                                Connection conn;
                                Statement stmt;
                                ResultSet rs;
                %>
	</head>

	<body>
            <form action="ResearchQueryServlet"  method="get">
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
			<div class="main-searchbox" >
				
                                    <input class="main-searchbox" id="search-location"  autocomplete="on" name="search-location" tabindex="1" type="text" value=<%=request.getParameter("search-location")%>>
					<input class="main-searchbox" id="search-name"  autocomplete="on" name="search-name" tabindex="2" type="text" value= <%= request.getParameter("search-name")%> >
					<input class="main-searchbox" id="search-button" type="submit" value="Search">
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
					<label class="radio-inline"><input type="radio" id="low-price" name="radio" value="low-price">low</label>
					<label class="radio-inline"><input type="radio" id="medium-price" name="radio" value="medium-price">medium</label>
					<label class="radio-inline"><input type="radio" id="high-price" name="radio" value="high-price">high</label>
					<label class="radio-inline"><input type="radio" id="every-price" name="radio" value="every-price" checked>every</label>
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
                                Where=request.getParameter("search-location");
                                What=request.getParameter("search-name");      
                                try
                                {
                                    String selected = request.getParameter("radio");
                                    if(selected.equals("low-price"))
                                    {
                                        Low=true;
                                    }
                                    if(selected.equals("medium-price"))
                                    {
                                        Medium=true;
                                    }
                                    if(selected.equals("high-price"))
                                    {
                                        High=true;
                                    }
                                    out.println("selezionato "+selected);                                     
                                }
                                catch(Exception  e)
                                {
                                    Every=true;
                                } 
                                
                                try
                                {
                                    String Bselected[]=request.getParameterValues("tipologia-cucina");
                                    out.println("selezionato ");
                                    for(int i=0;i<Bselected.length;i++)
                                    {
                                        if(Bselected[i].equals("cucina-italiana"))
                                        {
                                            Italian=true;
                                            out.println(Bselected[i]);
                                        }
                                        if(Bselected[i].equals("fast-food"))
                                        {
                                            Fast=true;
                                            out.println(Bselected[i]);
                                        }
                                        if(Bselected[i].equals("slow-food"))
                                        {
                                            Slow=true;
                                            out.println(Bselected[i]);
                                        }
                                        if(Bselected[i].equals("cucina-tipica"))
                                        {
                                            Tipical=true;
                                            out.println(Bselected[i]);
                                        }
                                        if(Bselected[i].equals("pizzeria"))
                                        {
                                            Pizzeria=true;
                                            out.println(Bselected[i]);
                                        }
                                        if(Bselected[i].equals("bistro"))
                                        {
                                            Bistro=true;
                                            out.println(Bselected[i]);
                                        }
                                    }
                                }
                                catch(Exception  e)
                                {
                                    Every2=true;
                                } 
                                
                                
                  
                            %>
                            
                            <table class="research_table">
                                <%
                                 ArrayList<RistoranteEBJ> rsdata = null;
                                 request.getAttribute("resultset");
                                 Iterator<RistoranteEBJ> it = rsdata.iterator();
                                 while(it.hasNext())
                                 {
                                 RistoranteEBJ temp = it.next();
                                %> <tr class="research_table_row">
                                        <img class="research_image" src="ristorante1.jpg"> 
                                        <h1 class="research_name"> <%temp.getName();%></h1>
                                        <div class="coordinates"> <%temp.getGlobalvalue();%></div>
                                        <div class="value"> <%temp.getCuisine();%></div>                                        
                                    </tr> <%
                                 }
                                %>
                            </table>
                            
			</div>
		</div>
            </form>
	</body>
</html>
