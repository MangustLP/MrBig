<%@page import="db.RecensioniDAO"%>
<%@page import="db.RecensioniEBJ"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.RistoranteDAO"%>
<%@page import="db.RistoranteEBJ"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Gourmet</title>
		<meta charset="UTF-8">
		<meta name="author" content="Mr. Big">
		<meta name="description" content="Find the restaurant of your desires">
  		<link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/admin.css">
                <link href="css/restaurant.css" rel="stylesheet" type="text/css"/>
		<script src="js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
                <% 
                    RistoranteEBJ mioristorante;
                    RistoranteDAO ristoDAO=new RistoranteDAO();
                    String claim=request.getParameter("claimed");
                    if(claim!=null)
                    {
                        %><%=request.getParameter("id") %> <%=(String)session.getAttribute("username") %><%
                        ristoDAO.setflag(request.getParameter("id"),(Integer)session.getAttribute("ID"),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                    }
                    mioristorante=ristoDAO.RistoranteDAO(Integer.parseInt(request.getParameter("id")),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                 %>
                 
	</head>

	<body>
            <%
                if(session.isNew())
                        {                           
                            session.setAttribute("type","normal");
                        }
                        //session.setAttribute("type","admin");
                        String type= (String)session.getAttribute("type");
                        if(type.equals("registered")) 
                        { 
                         %> <%@include file="menu-registered.jsp"%> <%
                        }
                        else
                        {
                            if(type.equals("restaurateur"))
                            { 
                                %> <%@include file="menu-restaurateur.jsp"%> <%
                            }
                            else
                            {
                                if(type.equals("admin"))
                                { 
                                     %> <%@include file="menu-admin.jsp"%> <%
                                }
                                else
                                { 
                                     %> <%@include file="menu-normal.jsp"%> <%
                                }
                            }
                        }
            %>
            <div class="title-container">
		<div class="main-searchbox" >
                    <form action="ResearchQueryServlet" method="POST">
                        <input class="main-searchbox" id="search-location"  autocomplete="on" placeholder="Where you wanna eat?" name="search-location" tabindex="1" type="text" >
                        <input class="main-searchbox" id="search-name"  autocomplete="on" placeholder="What are you looking for?" name="search-name" tabindex="2" type="text"  >
                        <input class="main-searchbox" id="search-button" type="submit" value="Search">
                    </form>
		</div>
            </div>
            <div class="photo-gallery">
                <img src="img/restaurant1.png" alt="restaurant"/>
            </div>
            
            <div class="general-info">
                <h1><%=mioristorante.getName()%></h1>
                <label>Valutazione: </label>
                <%
                int stars = mioristorante.getGlobalvalue();
                for(int i=0; i < stars; i++) { %>
                    <img src="img/one-star.png" alt="star" class="star-img"/>
                <% } %>
                <%
                int emptyStars = 5 - mioristorante.getGlobalvalue();
                for(int i=0; i < emptyStars; i++) { %>
                    <img src="img/zero-star.png" alt="star" class="star-img"/>
                <% } %>
                <label id="nrecensioni"><%=mioristorante.getNrecensioni()%> recensioni</label>
                <br>
                <label id="rdescription"><%=mioristorante.getDescription()%></label>
                <address> <img src="img/address.png" alt="address" id="address-image"/> <%=mioristorante.getAddress() %></address>
                
                
                <a href="<%=mioristorante.getWebsite() %>" id="rwebsite"><img src="img/website.png" alt="website" id="website-image"/> Sito Web</a>
                <br>
                <label id="rprice"><img src="img/price.png" alt="price" id="price-image"/> Prezzo: <%=mioristorante.getPrice() %> </label>
                        <br>                        
                        <label id="rfood"> <img src="img/food.png" alt="food" id="food-image"/><% ArrayList<String> mioarray=mioristorante.getCuisine();
                    for(int i=0;i<mioarray.size();i++){
                %><%=mioarray.get(i) %> <%
                    }%> </label>
                
            </div>
		
                    <div class="panel panel-default" id="review-panel">
                        <div class="panel-heading">Leave a Review <label id="review-info">(From 1 to 5 stars)</label></div>
                    <div class="panel-body">
  
            <div class="recensioni">
                <%
                    ArrayList<RecensioniEBJ> arrayrecensioni;
                    RecensioniDAO receDAO=new RecensioniDAO();
                    arrayrecensioni=receDAO.RecensioniDAO(Integer.parseInt(request.getParameter("id")),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                %>
                <table class="reviews-table">
                    <%
                        for(int i=0;i<arrayrecensioni.size();i++){
                    %> 
                            <tr class="reviews_table_row">
                                <td class="reviews_table_column">                                
                                    <h1 class="global_review">
                                        Global Valutation: <%=arrayrecensioni.get(i).getValue()%> /5     
                                    </h1>    
                                    <h4 class="other_review">
                                        Food Valutation: <%=arrayrecensioni.get(i).getFood() %>/5  Service: <%=arrayrecensioni.get(i).getService() %> Atmosphere: <%=arrayrecensioni.get(i).getAtmosphere() %>/5 Cheapness <%=arrayrecensioni.get(i).getPricevalue() %>/5
                                    </h4>
                                        
                                </td>
                            </tr> <%
                        }
                        int owner=mioristorante.getIdOwner();
                        if(owner ==0 && session.getAttribute("ID")!=null)
                        { %>
                        <form action="restaurant.jsp" method="get">
                                <Input type="submit" id="button-claim" class="btn btn-primary" value="Claim">   
                                <input type="hidden" name="id" value="<%=request.getParameter("id")%>" />
                                <input type="hidden" name="claimed" value="1" /> 
                        </form> <% 
                        }                        
                    %>
                    <div>
                        <form action="RecensioneServlet" method="post" enctype="multipart/form-data">
                            <input type="file" name="file"  size="50" />
                            <br />
                            <div>
                                <label class="review-label">Global value of the restaurant:</label>
                            </div>
                                <label class="radio-inline"><input type="radio" id="onestar" name="radio" value="1">1</label>
                                <label class="radio-inline"><input type="radio" id="twostar" name="radio" value="2">2</label>
                                <label class="radio-inline"><input type="radio" id="threestar" name="radio" value="3">3</label>
                                <label class="radio-inline"><input type="radio" id="fourstar" name="radio" value="4">4</label>
                                <label class="radio-inline"><input type="radio" id="fivestar" name="radio" value="5">5</label>
                            <div>
                                <label class="review-label">Value of the food:</label>
                            </div>
                                <label class="radio-inline"><input type="radio" id="onestar" name="radiof" value="1">1</label>
                                <label class="radio-inline"><input type="radio" id="twostar" name="radiof" value="2">2</label>
                                <label class="radio-inline"><input type="radio" id="threestar" name="radiof" value="3">3</label>
                                <label class="radio-inline"><input type="radio" id="fourstar" name="radiof" value="4">4</label>
                                <label class="radio-inline"><input type="radio" id="fivestar" name="radiof" value="5">5</label>
                            <div>
                                <label class="review-label">Value of the service:</label>
                            </div>
                                <label class="radio-inline"><input type="radio" id="onestar" name="radios" value="1">1</label>
                                <label class="radio-inline"><input type="radio" id="twostar" name="radios" value="2">2</label>
                                <label class="radio-inline"><input type="radio" id="threestar" name="radios" value="3">3</label>
                                <label class="radio-inline"><input type="radio" id="fourstar" name="radios" value="4">4</label>
                                <label class="radio-inline"><input type="radio" id="fivestar" name="radios" value="5">5</label>    
                            <div>
                                <label class="review-label">Value for the price:</label>
                            </div>
                                <label class="radio-inline"><input type="radio" id="onestar" name="radiov" value="1">1</label>
                                <label class="radio-inline"><input type="radio" id="twostar" name="radiov" value="2">2</label>
                                <label class="radio-inline"><input type="radio" id="threestar" name="radiov" value="3">3</label>
                                <label class="radio-inline"><input type="radio" id="fourstar" name="radiov" value="4">4</label>
                                <label class="radio-inline"><input type="radio" id="fivestar" name="radiov" value="5">5</label>
                            <div>
                                <label class="review-label">Climate value:</label>
                            </div>
                                <label class="radio-inline"><input type="radio" id="onestar" name="radioa" value="1">1</label>
                                <label class="radio-inline"><input type="radio" id="twostar" name="radioa" value="2">2</label>
                                <label class="radio-inline"><input type="radio" id="threestar" name="radioa" value="3">3</label>
                                <label class="radio-inline"><input type="radio" id="fourstar" name="radioa" value="4">4</label>
                                <label class="radio-inline"><input type="radio" id="fivestar" name="radioa" value="5">5</label>
                                <br>
                                <label class="review-label" id="review-description">Do you want to leave more info about your experience? Write it here:</label>
                            </div>
                                <textarea name="description" row="10" cols="50"></textarea>
                                <button type="submit" class="btn btn-primary" id="send-button" <% if(session.getAttribute("ID")==null){ %> <%="disabled"%><% } %>>Send</button>
                                
                        </form> 
                    </div>
                </table>
            </div>
        </div>
        </div>
	</body>
</html>