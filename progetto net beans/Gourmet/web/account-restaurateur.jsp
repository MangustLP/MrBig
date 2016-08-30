<html>
	<head>
		<title>Gourmet</title>
		<meta charset="UTF-8">
		<meta name="author" content="Mr. Big">
		<meta name="description" content="Find the restaurant of your desires">
  		<link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		<link rel="stylesheet" type="text/css" href="css/Restaurateur.css">
		<script src="js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
	</head>

	<body>
			<%
                        //session.setAttribute("type","admin");
                        if(session.isNew())
                        {                    
                          String redirectURL = "/error.jsp";
                          response.sendRedirect(redirectURL);
                        }
                        String type= (String)session.getAttribute("type");
                        if(type.equals("registered")) 
                        { 
                                String redirectURL = "/error.jsp";
                                response.sendRedirect(redirectURL);
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
                        String Name="not set";
                        String Surname="not set";
                        if(session.getAttribute("name")!=null)
                        {
                            Name=(String)session.getAttribute("name");
                        }
                        if(session.getAttribute("surname")!=null)
                        {
                            Surname=(String)session.getAttribute("surname");
                        }
                        
                    %>
                    
                    <div id="restauretor_info">
                        <p>Name:<%= Name%> </p> 
                        <p>Surname:<%= Surname%></p>   
                        <p>Username:<%=session.getAttribute("username")%></p>  
                        <% String username=(String)session.getAttribute("username");
                        Integer id=(Integer)session.getAttribute("ID");
                        ArrayList<String> arraynomi=new ArrayList<>();
                        ArrayList<Integer> arrayid=new ArrayList<>();
                        RistoranteDAO ristodao=new RistoranteDAO();
                        arraynomi=ristodao.getNames(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        arrayid= ristodao.getIds(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        %>
                        
                        I ristoranti posseduti da <%=username%> sono:<br>
                        <ul>
                        <%
                        for (int i=0;i<arraynomi.size();i++)
                        {
                        %><li><%=arraynomi.get(i)%> <a href="restaurant.jsp?id=<%=arrayid.get(i) %>">link</a></li><br><%
                        }%> 
                        </ul>
                        
		                       
                    </div>
	</body>
</html>