<%@page import="java.sql.DriverManager"%>
<%@page import="db.RistoranteDAO"%>
<%@page import="db.RistoranteEBJ"%>
<%@page import="java.util.ArrayList"%>
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
		<link rel="stylesheet" type="text/css" href="css/notification-admin.css">
		<script src="js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="js/load.js"></script>
               
	</head>

	<body>
			<%
                        session.setAttribute("type","admin");
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
                                String redirectURL = "/error.jsp";
                                response.sendRedirect(redirectURL);
                            }
                            else
                            {
                                if(type.equals("admin"))
                                { 
                                     %> <%@include file="menu-admin.jsp"%> <%
                                }
                                else
                                {                                      
                                    String redirectURL = "/error.jsp";
                                    response.sendRedirect(redirectURL);
                                }
                            }
                        }

                        RistoranteEBJ mioristorante;
                        RistoranteDAO ristoDAO=new RistoranteDAO();
                        String claim=request.getParameter("id-restourant");
                                     
                        ArrayList<String> List=ristoDAO.getClaimedRestaurants(DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        %>
                        <table id="notification_admin_table">                            
                        <%
                            
                        for(int i=0;i<List.size();)
                        {
                                     
                            String ID_Res=List.get(i);
                            i++;
                            String Rest_Name=List.get(i);
                            i++;                            
                            String Username=List.get(i);
                            i++;%>                            
                            <tr>
                                <td>
                                    <%if(claim!=null)
                                    {
                                     %> <%=claim %> <br><%
                                    }   %>
                                    <form action="admin-notifications.jsp" method="post" class="notification-form" id="form<%=ID_Res%>">
                                        L'utente: <%= Username%> vuole reclamare il ristorante: <%=Rest_Name%>
                                        <input type="submit" id="button"<%=ID_Res%> class="btn btn-primary" value="ok">
                                        <input type="hidden" name="id-restourant" value="<%=ID_Res%>">         
                                        
                                    </form>
                                </td>
                            </tr>
                      <%}
                    %>
                        </table>
                    
                    
	</body>
</html>