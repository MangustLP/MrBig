<%@page import="java.util.Arrays"%>
<%@page import="java.sql.SQLException"%>
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
                 <%!
                                RistoranteDAO ristoDAO=new RistoranteDAO();
                                ArrayList<RistoranteEBJ> rsdata;
                                String Where;
                                String What;
                                String url="";
                                String username="";
                                String password="";
                                      
                                     
                                Boolean Every2= false;
                               
                                Boolean Italian=false;
                                Boolean Slow=false;
                                Boolean Fast=false;
                                Boolean Tipical=false;
                                Boolean Pizzeria=false;
                                Boolean Bistro=false;
                                
                     
                %>
        </head>

	<body>
                    <%
                        String[] cucinafiltratiarray;
                        ArrayList<String> cucinafiltrati=null;
                        cucinafiltratiarray=request.getParameterValues("tipologia-cucina");
                        if(cucinafiltratiarray!=null)
                            cucinafiltrati=new ArrayList<String>(Arrays.asList(cucinafiltratiarray));
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
            <form action="ResearchQueryServlet"  method="post">
		<div class="title-container">
                    <div class="main-searchbox" >
                        <input class="main-searchbox" id="search-location"  autocomplete="on" placeholder="Where you wanna eat?" name="search-location" tabindex="1" type="text" value=<%=request.getParameter("search-location")%>>
                        <input class="main-searchbox" id="search-name"  autocomplete="on" placeholder="What are you looking for?" name="search-name" tabindex="2" type="text" value= <%= request.getParameter("search-name")%> >
			<input class="main-searchbox" id="search-button" type="submit" value="Search">
                    </div>
		</div>
		<div class="body-container">
			<div class="form-group" id="order-box">
  				<label id="order-for" for="order-list">Order:</label>
  				<select class="form-control" id="order-list" name="order">
                                    <option value="Rank">Ranking</option>
                                    <option value="Price">Price</option>
                                    <option value="Alphabetic">Alphabet</option>
  				</select>
                                <button type="submit" id="apply-button1" class="btn btn-primary">Apply</button>
			</div>
			<aside class="filter-box">
					<label id="price-list">Price:</label>
					<br/>
					<label class="radio-inline"><input type="radio" id="low-price" name="radio" value="low-price">low</label>
					<label class="radio-inline"><input type="radio" id="medium-price" name="radio" value="medium-price">medium</label>
					<label class="radio-inline"><input type="radio" id="high-price" name="radio" value="high-price">high</label>
					<label class="radio-inline"><input type="radio" id="every-price" name="radio" value="every-price" checked>every</label>
					<br/>
				<label class="food-list">Cuisine Type:</label>
                                <% 
                                    String query="SELECT NAME FROM CUISINES";
                                    ResultSet rs = null;
                                    Statement ps=null;
                                    try{
                                        ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                                        rs = ps.executeQuery(query);
                                    }catch (SQLException ex) {
                                        //Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                                    } %>
				<% while(rs.next()){ %>
                                <div class="checkbox">
				  <label>
                                      <input type="checkbox" name="tipologia-cucina" class="food-list" id="<%=rs.getString(1) %>" value="<%=rs.getString(1) %>" <% if(cucinafiltrati!=null && cucinafiltrati.contains(rs.getString(1))) out.print("checked"); %> > <%=rs.getString(1) %>
				  </label>
				</div>
                                <% } 
                                rs.close();
                                ps.close(); %>
				<!--<div class="checkbox">
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
				</div> -->
				<br/>
				<button type="reset" id="reset-button" class="btn btn-danger">Reset</button>
                                <button type="submit" id="apply-button" class="btn btn-primary">Apply</button>
			</aside>
			<div class="corporicerca">
                            <%                                
                                Where=request.getParameter("search-location");
                                What=request.getParameter("search-name");      
                                
                                
                                try
                                {
                                    String Bselected[]=request.getParameterValues("tipologia-cucina");
                                   
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
    if (request.getAttribute("resultset")!= null){
        rsdata = (ArrayList<RistoranteEBJ>)request.getAttribute("resultset");
        Iterator<RistoranteEBJ> it = rsdata.iterator();
        while(it.hasNext()){
            RistoranteEBJ temp = it.next();
            ArrayList<String> cucinaristorante=ristoDAO.getCuisines(temp.getId(), DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
            if(cucinafiltrati!=null){
                if(cucinaristorante.containsAll(cucinafiltrati)){
%>
                                <tr class="research_table_row">
                                    <td>
                                        <%
                                            
                                            String namep = "";
                                            query="SELECT PHOTOS.NAME FROM PHOTOS INNER JOIN RESTAURANTS ON RESTAURANTS.PRIMARYPHOTO = PHOTOS.ID WHERE ID_RESTAURANT = "+temp.getId(); 
                                            rs = null;
                                            ps=null;
                                            try{
                                                   ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                                                   rs = ps.executeQuery(query);
                                            }catch (SQLException ex) {
                                            //Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            if ( rs.isBeforeFirst()){
                                                rs.next();
                                                namep = rs.getString(1); 
                                                rs.close();
                                                ps.close();
                                                System.out.println("sadddddddddddddddddddddddddddddd      " +namep);
                                        %><img class="research_image" src="upload_image/<%=namep %>.jpg"><%
                                            }
                                            else{
                                                %><img class="research_image" src="img/ristorante1.jpg"><%
}
                                        %>
                                        <div class="research_info"> 
                                            <div class="research_name"> <a href="restaurant.jsp?id=<%=temp.getId()%>"><%out.println(temp.getName());%></a></div>
                                            <div class="value">
<%
                int stars = temp.getGlobalvalue();
                for(int i=0; i < stars; i++) { %>
                                                <img src="img/one-star.png" alt="star" style="width:15px;">
<%              } %>
<%              int emptyStars = 5 - temp.getGlobalvalue(); 
                for(int i=0; i < emptyStars; i++) { %>
                                                <img src="img/zero-star.png" alt="star" style="width:15px;">
<%              } %>                        </div> 
                                            <div>
                                                Prezzo:<%=temp.getPrice() %>
                                            </div> 
                                            <div>
                                                Numero recensioni:<%=temp.getNrecensioni() %>
                                            </div>
                                            <address class="coordinates"> <img src="img/address.png" alt="address" id="address-image"/> <%out.println(temp.getAddress());%></address>  
                                            <div class="cuisines"><img src="img/food.png" alt="food" id="food-image"/> <% for(int i=0;i<cucinaristorante.size();i++){out.print(cucinaristorante.get(i)+" ");} %>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="spaceUnder">
                                    <td></td>
                                </tr>
<%
                }
            }
            else{ %>
                                <tr class="research_table_row">
                                    <td>
                                        <%
                                            
                                            String namep = "";
                                            query="SELECT PHOTOS.NAME FROM PHOTOS INNER JOIN RESTAURANTS ON RESTAURANTS.PRIMARYPHOTO = PHOTOS.ID WHERE ID_RESTAURANT = "+temp.getId(); 
                                            rs = null;
                                            ps=null;
                                            try{
                                                   ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                                                   rs = ps.executeQuery(query);
                                            }catch (SQLException ex) {
                                            //Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            if ( rs.isBeforeFirst()){
                                                rs.next();
                                                namep = rs.getString(1); 
                                                rs.close();
                                                ps.close();
                                                System.out.println("sadddddddddddddddddddddddddddddd      " +namep);
                                        %><img class="research_image" src="upload_image/<%=namep %>.jpg"><%
                                            }
                                            else{
                                                %><img class="research_image" src="img/ristorante1.jpg"><%
}
                                        %>
                                        <div class="research_info"> 
                                            <div class="research_name"> <a href="restaurant.jsp?id=<%=temp.getId()%>"><%out.println(temp.getName());%></a></div>
                                            <div class="value">
<%
                int stars = temp.getGlobalvalue();
                for(int i=0; i < stars; i++) { %>
                                                <img src="img/one-star.png" alt="star" style="width:15px;">
<%              } %>
<%              int emptyStars = 5 - temp.getGlobalvalue(); 
                for(int i=0; i < emptyStars; i++) { %>
                                                <img src="img/zero-star.png" alt="star" style="width:15px;">
<%              } %>                        </div> 
                                            <div>
                                                Prezzo:<%=temp.getPrice() %>
                                            </div> 
                                            <div>
                                                Numero recensioni:<%=temp.getNrecensioni() %>
                                            </div>
                                            <address class="coordinates"> <img src="img/address.png" alt="address" id="address-image"/> <%out.println(temp.getAddress());%></address>  
                                            <div class="cuisines"><img src="img/food.png" alt="food" id="food-image"/> <% for(int i=0;i<cucinaristorante.size();i++){out.print(cucinaristorante.get(i)+" ");} %>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="spaceUnder">
                                        <td></td>
                                </tr>
<%          }
        }
    }
    else{
        out.println("nessun ristorante trovato!");
    }%>
                            </table>             
			</div>
		</div>
            </form>
	</body>
</html>
