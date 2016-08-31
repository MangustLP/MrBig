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
                        String user_claim=request.getParameter("username");
                        String decision=request.getParameter("decision");
                       
                        if(claim!=null && decision!=null)
                        {
                            if(decision.equals("accept"))
                                ristoDAO.Claim(claim,user_claim, 1, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                            else
                                ristoDAO.Claim(claim,user_claim, 0, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        }
                        ArrayList<String> List=ristoDAO.getClaimedRestaurants(DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        %>
                        <table id="notification_admin_table">                            
                        <%
                        %>
                            <h2 id="claim_title">
                                Claims list
                            </h2>
                        <%    
                        for(int i=0;i<List.size();)
                        {
                                     
                            String ID_Res=List.get(i);
                            i++;
                            String Rest_Name=List.get(i);
                            i++;                           
                            String Username=List.get(i);
                            i++;
                        %>                            
                            <tr class="claims_row">                                
                                <form action="admin-notifications.jsp" method="post" class="notification-form" id="form<%=ID_Res%>">
                                <td>
                                    <h4>
                                         User <%= Username%> want to claim : <%=Rest_Name%>
                                    </h4>
                                </td>
                                <td>
                                        <input type="submit" id="button"<%=ID_Res%> name="decision" class="claim-button" value="accept">
                                        <input type="hidden" name="id-restourant" value="<%=ID_Res%>">     
                                        <input type="hidden" name="username" value="<%=Username%>">
                                        <input type="submit" id="button"<%=ID_Res%> name="decision" class="claim-button" value="decline">
                                </td>
                                </form>
                            </tr>
                            <tr class="claims_blank">
                                
                            </tr>
                      <%}
                    %>
                    <%
                        String idr = request.getParameter("idR");
                        String namepo = request.getParameter("namep");
                        if ((idr!=null)&&(namepo!=null)){
                            ristoDAO.removePhoto(namepo, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        }
                        else{
                            ristoDAO.setflagPhoto(namepo,DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        }
                        ArrayList<String> Listp = ristoDAO.getPhotosContested( DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
                        for(int i=0;i<Listp.size();)
                        {
                                     
                            
                            String namep=Listp.get(i);
                            i++;
                            String path = Listp.get(i);
                            i++;
                            Integer idR = Integer.parseInt(Listp.get(i));
                            i++;
                            String nameR = Listp.get(i);
                            i++;
                            String surnameR = Listp.get(i);
                            i++;
                            String emailR = Listp.get(i);
                            i++;
                            String nameS = Listp.get(i);
                            i++;
                            String surnameS = Listp.get(i);
                            i++;
                            String emailS = Listp.get(i);
                            i++;
                            
                        %>                            
                            <tr class="claims_row">                                
                                <form action="admin-notifications.jsp" method="post" class="notification-form" id="formp">
                                <td class="request-td">
                                    <h4>User:  <%= nameS%> <%=surnameS%>   |   e-mail: <%=emailS%></h4>
                                    <br>
                                    <h4>Type of request: <cite>request to remove an image from restaurant : <%=ristoDAO.getNames(idR,DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"))%></cite></h4>
                                    <br>
                                    <h4>Image uploaded from : <%= nameR%> <%=surnameR%>   |   e-mail <%=emailR%></h4>
                                    <br>
                                    <br>
                                </td>
                                <td>
                                    <div>
                                             <img class="research_image" src="/upload_image/<%=namep%>.jpg">  
                                    </div>
                                </td>
                                <td>
                                        <input type="submit" id="buttonp2" name="decisionp" class="claim-button" value="accept">
                                        <input type="hidden" name="id-restourant" value="<%=idR%>">     
                                        <input type="hidden" name="namep" value="<%=namep%>">
                                        <input type="hidden" name="idr" value="<%=idR%>">
                                        <input type="submit" id="buttonp" name="decisionp" class="claim-button" value="decline">
                                </td>
                                </form>
                            </tr>
                            <tr class="claims_blank">
                                
                            </tr>
                      <%}
                      %>
                        </table>
                    
                    
	</body>
</html>