<%@page import="db.RecensioniEBJ"%>
<%@page import="db.RecensioniDAO"%>
<html>
	<head>
		<title>Gourmet</title>
		<meta charset="UTF-8">
		<meta name="author" content="Mr. Big">
		<meta name="description" content="Find the restaurant of your desires">
  		<link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		<link rel="stylesheet" type="text/css" href="css/restaurateur.css">
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
                    
                    <div>
                        <h1>Restaurants of <%= Name%> <%= Surname%></h1>                    
                        <h3>Username: <%=session.getAttribute("username")%></h3>  
                        <br>
                        <br>                        
                        <button type="button" id="refactor-button" class="btn btn-primary"> Change Account Info</button>
                        <% String username=(String)session.getAttribute("username");                        
                        Integer id=(Integer)session.getAttribute("ID");
                        ArrayList<String> arraynomi=new ArrayList<String>();
                        ArrayList<Integer> arrayid=new ArrayList<Integer>();
                        RistoranteDAO ristodao=new RistoranteDAO();
                        arraynomi=ristodao.getNames(id+"", DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        arrayid= ristodao.getIds(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        %>
                        <div class="panel-group">
                            <div class="panel panel-default" id="restaurant-panel">
                                <div class="panel-heading">Your restaurants</div>
                                    <div class="panel-body">
                                        <ul>
                                        <%
                                        for (int i=0;i<arraynomi.size();i++)
                                        {
                                        %><li><a href="restaurant.jsp?id=<%=arrayid.get(i) %>"><%=arraynomi.get(i)%></a></li><br><%
                                        }%> 
                                        </ul>
                                    </div>                        
                            </div>
                            <div class="panel panel-default" id="reviews">
                                <div class="panel-heading" id="a-review">Your Reviews:</div>
                                <div class="panel-body">
                                <%
                                ArrayList<RecensioniEBJ> arrayrecensioni;
                                RecensioniDAO receDAO=new RecensioniDAO();
                                arrayrecensioni=receDAO.getUsersReviews(session.getAttribute("ID")+"",DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                                for(int i=0;i<arrayrecensioni.size();i++)
                                {
                                    %>
                                <h4>Restaurant: <%= arrayrecensioni.get(i).getName()%></h4>
                                <h5>Global Evaluation: <%= arrayrecensioni.get(i).getValue()%></h5>
                                <h5 class="d-review"> Review: <cite>"<%= arrayrecensioni.get(i).getDescription()%>"</cite></h5> <%
                                }
                                %>
                                </div>
                            </div>
                        </div>
	</body>
</html>