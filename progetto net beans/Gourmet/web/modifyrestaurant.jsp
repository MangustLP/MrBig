<%@page import="db.RistoranteEBJ"%>
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
                String restaurant=request.getParameter("id");
                RistoranteEBJ mioristorante;
                RistoranteDAO ristoDAO=new RistoranteDAO();
                mioristorante=ristoDAO.RistoranteDAO(Integer.parseInt(request.getParameter("id")),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                String IDu=session.getAttribute("ID")+"";
                String IDOw=mioristorante.getIdOwner()+"";
                if(!(IDu.equals(IDOw)))
                {
                    String redirectURL = "/error.jsp";
                    response.sendRedirect(redirectURL);
                }                        
                if(request.getParameter("cancel-image-name")!=null)
                {
                    String nameI=request.getParameter("cancel-image-name");
                    System.out.println(nameI);
                    ristoDAO.FlagImages(nameI, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                    ristoDAO=new RistoranteDAO(); 
                    mioristorante=ristoDAO.RistoranteDAO(Integer.parseInt(request.getParameter("id")),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
               
                }
                if(request.getParameter("prymary-photo-name")!=null)
                {
                    ristoDAO.PrymaryImage(request.getParameter("prymary-photo-name"), request.getParameter("id")+"", DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                }
                if(request.getParameter("changing")!=null)
                {
                    String name=request.getParameter("Name");
                    String Description=request.getParameter("Description");
                    String web=request.getParameter("WebSite");
                    ristoDAO.UpdateRestaurant(request.getParameter("id")+"", name, Description, web, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                    ristoDAO=new RistoranteDAO();
                    mioristorante=ristoDAO.RistoranteDAO(Integer.parseInt(request.getParameter("id")),DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                }
            %>   
            <form id="RestaurantInfo" action="modifyrestaurant.jsp">
                <table>
                    <tr>
                        <td>Name:</td>
                        <td>
                            <input type="text" value="<%=mioristorante.getName()%>" name="Name" id="RestaurantName" spellcheck="false" >
                        </td>
                    </tr>
                    <tr id="descriptionrow">                        
                        <td>Description: </td>
                        <td>
                            <textarea name="Description" id="RestaurantDescription" name="description" row="10" cols="50"><%=mioristorante.getDescription() %></textarea>
                        </td>
                    </tr>
                    <tr>                        
                        <td>Web Site:</td>
                        <td>
                            <input type="text" value="<%=mioristorante.getWebsite() %>" name="WebSite" id="RestaurantWebSite" spellcheck="false" >           
                        </td>                        
                    </tr> 
                    
                </table>
                <br>
                <input type="hidden" name="changing" value="1">
                <input type="hidden" name="id" value="<%=request.getParameter("id") %>">
                <input type="submit" value="change restaurant info">
            </form>                           
                <table id="RestaurantImages">                    
                    <%
                    ArrayList<String> arrayphotos=new ArrayList<String>();
                    arrayphotos =mioristorante.getNotFlaggedPhotos();
                    Thread.sleep(2000);
                    for (int i=0;i<arrayphotos.size();i++)
                    {
                        %>
                        <tr>
                            <td class="image-column">
                                <img src="upload_image/<%=arrayphotos.get(i) %>.jpg" alt="restaurant" height="125" width="125"/>
                            </td>
                            <td id="cancel-column">
                                <form action="modifyrestaurant.jsp" method="Post">
                                    <input type="hidden" name="cancel-image-name" value="<%=arrayphotos.get(i) %>">
                                    <input type="hidden" name="id" value="<%=request.getParameter("id") %>">
                                    <input type="submit" value="Ask to be Removed" class="btn btn-primary">
                                </form>
                            </td>
                            <td class="primary-column">
                                <form action="modifyrestaurant.jsp" method="Post">
                                    <input type="hidden" name="prymary-photo-name" value="<%=arrayphotos.get(i) %>">
                                    <input type="submit" value="Use as prymary photo" class="btn btn-primary">
                                    <input type="hidden" name="id" value="<%=request.getParameter("id") %>">
                                </form>
                            </td>
                        </tr>
                        <%
                    }
                    %>                    
                </table>
	</body>
</html>