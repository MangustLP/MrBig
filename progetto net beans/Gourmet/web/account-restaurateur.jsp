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
                    
                    <div id="restauretor_info">
                        <h1>I ristoranti di <%= Name%> <%= Surname%></h1>                    
                        <h3>Username: <%=session.getAttribute("username")%></h3>  
                        <br>
                        <br>                        
                        <button type="button" id="refactor-button" class="btn btn-primary"> Change Account Info</button>
                        <% String username=(String)session.getAttribute("username");                        
                        Integer id=(Integer)session.getAttribute("ID");
                        ArrayList<String> arraynomi=new ArrayList<String>();
                        ArrayList<Integer> arrayid=new ArrayList<Integer>();
                        RistoranteDAO ristodao=new RistoranteDAO();
                        arraynomi=ristodao.getNames(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        arrayid= ristodao.getIds(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        %>
                        <div class="panel panel-default">
                            <div class="panel-heading">I tuoi ristoranti</div>
                                <div class="panel-body">
                                    <ul>
                                    <%
                                    for (int i=0;i<arraynomi.size();i++)
                                    {
                                    %><li><a href="modifyrestaurant.jsp?id=<%=arrayid.get(i) %>"><%=arraynomi.get(i)%></a></li><br><%
                                    }%> 
                                    </ul>
                                </div>
                        </div>                        
                        
                        
		                       
                    </div>
                    <div id="reviews">
                    Your Reviews               
                    <%
                    ArrayList<RecensioniEBJ> arrayrecensioni;
                    RecensioniDAO receDAO=new RecensioniDAO();
                    arrayrecensioni=receDAO.getUsersReviews(session.getAttribute("ID")+"",DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                    for(int i=0;i<arrayrecensioni.size();i++)
                    {
                        %><h4>Restaurant: <%= arrayrecensioni.get(i).getName()%> Global Valutation:<%= arrayrecensioni.get(i).getValue()%> </h4>
                        <h5> Review:<%= arrayrecensioni.get(i).getDescription()%></h5> <%
                    }
                    %> 
                    </div>                
	</body>
</html>