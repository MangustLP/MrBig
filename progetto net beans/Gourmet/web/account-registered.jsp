<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
	</head>

	<body>
            <%
                
                if(session.isNew())
                {                    
                    String redirectURL = "/error.jsp";
                    response.sendRedirect(redirectURL);
                }
                String type= (String)session.getAttribute("type");
                if(type.equals("registered")) 
                { 
                    %> <%@include file="menu-registered.jsp"%> <%
                }
                else
                {
                    if(type.equals("restaurateur"))
                    {                                 
                        String redirectURL = "/error.jsp";
                        response.sendRedirect(redirectURL);
                    }
                    else
                    {
                        if(type.equals("admin"))
                        { 
                            String redirectURL = "/error.jsp";
                            response.sendRedirect(redirectURL);
                        }
                        else
                        {                                      
                            String redirectURL = "/error.jsp";
                            response.sendRedirect(redirectURL);
                        }
                    }
                }
            %>
                    
            <div >
                <%
                    String username=(String)session.getAttribute("username");
                    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"); 
                    Statement statement = connection.createStatement();
                    String Nome;
                    String Cognome;
                    int id=0;
                    String Password;
                    ResultSet rs=statement.executeQuery("SELECT * FROM USERS WHERE NICKNAME ='"+username+"'");
                    while(rs.next())
                    {
                        id=rs.getInt("ID");
                        Nome=rs.getString("NAME");
                        Cognome=rs.getString("SURNAME");   
                        Password=rs.getString("PASSWORD");
                    }
                    Connection connection2 = DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"); 
                    Statement statement2 = connection2.createStatement();                          
                    ResultSet rs2=statement.executeQuery("SELECT * FROM REVIEWS WHERE ID_CREATOR ="+id);
                    while(rs2.next())
                    {
                        %> <%= rs2.getString("DESCRIPTION") %> <%
                    }
                %>
            </div>
            
		
	</body>
</html>