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
                    RistoranteDAO dao=new RistoranteDAO();
                    mioristorante=dao.RistoranteDAO(Integer.parseInt(request.getParameter("id")),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
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
                <a href="<%=mioristorante.getAddress() %>"><%=mioristorante.getAddress()%></a>
                        <br>
                <%=mioristorante.getGlobalvalue() %>
                        <br>
                <%=mioristorante.getPrice() %>
                        <br>
                <% ArrayList<String> array = mioristorante.getCuisine();
                    Iterator<String> i = array.iterator();
                    while(i.hasNext()){%>
                    <%String temp = i.next();%>
                    <%=temp%>
                        <br>
                <% } %>
                        
            </div>
		
            <div class="recensioni">
                <% 
                    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"); 
                    Statement statement = connection.createStatement();
                    String restaurant = request.getParameter("restaurant");
                    ResultSet rs=null;
                    //rs= statement.executeQuery("select * from REVIEWS ,RESTAURANTS ,USERS    where REVIEWS.  = '" + restaurant + "'"); query che non ho voglia di fare ora
                    
                %>
                <table class="reviews-table">
                    <%
                        //while(rs.next())
                        {%> 
                            <tr class="reviews_table_row">
                                <td class="reviews_table_column">                                
                                    <h1 class="global_review">
                                        Global Valutation: <%//rs.getString("REVIEW_GLOBAL_VALUE_CHECK "); %> /5     
                                    </h1>    
                                    <h4 class="other_review">
                                         Food Valutation: <%//rs.getString("REVIEW_FOOD_CHECK"); %>/5  Service: <%//rs.getString("REVIEW_SERVICE_CHECK"); %> Atmosphere: <%//rs.getString("REVIEW_ATMOSPHERE_CHECK"); %>/5 Cheapness <%//rs.getString("REVIEW_VALUE_FOR_MONEY_CHECK "); %>/5
                                    </h4>
                                        
                                </td>
                            </tr> <%
                        }
                    %>
                </table>
            </div>
	</body>
</html>