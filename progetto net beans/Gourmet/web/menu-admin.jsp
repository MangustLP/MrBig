<link rel="stylesheet" type="text/css" href="css/header.css">

<header>
     <a href="index.jsp"><img src="img/Gourmet-home.png" id="logo-home" alt="Gourmet Home"/></a>
    <button type="button" id="admin-account-button" class="btn btn-primary">Admin <% if(!session.isNew())
       { 
         %><%=(String)session.getAttribute("username")%><%
       } %></button>
    <button type="=button" id="restourant-notif" class="btn btn-primary">Notification</button>
    <form  action="LogoutServlet" method="POST" class="logout-form" name="logout" id="logoutform">
        <button type="submit" id="logout-button" class="btn btn-primary" value="logout">Logout</button>        
    </form>    
</header>