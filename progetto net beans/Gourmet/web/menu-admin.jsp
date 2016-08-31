<link rel="stylesheet" type="text/css" href="css/header.css">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<header>
     <a href="index.jsp"><img src="img/Gourmet-home.png" id="logo-home" alt="Gourmet Home"/></a>
    <button type="button" id="account-button" class="btn btn-primary">Admin <% if(!session.isNew())
       { 
            String nomecompleto=(String) session.getAttribute("name")+" "+(String) session.getAttribute("surname");
         %><%=nomecompleto%><%
       } %></button>
    <button type="button" id="admin-notif" class="btn btn-primary">Notification</button>
    <form  action="LogoutServlet" method="POST" class="logout-form" name="Logout" id="logoutform">
        <button type="submit" id="logout-button" class="btn btn-primary" value="Logout">Logout</button>        
    </form>    
</header>