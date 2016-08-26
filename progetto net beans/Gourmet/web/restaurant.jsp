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
		<script src="js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="js/load.js"></script>
                <% 
                    RistoranteEBJ mioristorante;
                    RistoranteDAO ristoDAO=new RistoranteDAO();
                    mioristorante=ristoDAO.RistoranteDAO(Integer.parseInt(request.getParameter("id")),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                 %>
	</head>

	<body>
            <<%
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
            <div class="photo-gallery">
                <img src="ristorante1.jpg" alt="Mountain View" style="width:304px;height:228px;">
            </div>
            
            <div class="general-info">
                <%=mioristorante.getName()%>
                        <br>
                <%=mioristorante.getDescription() %>
                        <br>
                <%=mioristorante.getAddress() %>
                        <br>
                <a href="<%=mioristorante.getWebsite() %>"><%=mioristorante.getWebsite()%></a>
                        <br>
                Valutazione: <%=mioristorante.getGlobalvalue() %>
                        <br>
                Prezzo: <%=mioristorante.getPrice() %>
                        <br>
                Tipi di cucina: <% ArrayList<String> mioarray=mioristorante.getCuisine();
                    for(int i=0;i<mioarray.size();i++){
                %><%=mioarray.get(i) %> <%
                    }%>
                
            </div>
		
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

                        
                    %>
                </table>
            </div>
	</body>
</html>