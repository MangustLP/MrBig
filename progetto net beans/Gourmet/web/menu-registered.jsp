<link rel="stylesheet" type="text/css" href="css/header.css">

<header>
       <a href="index.jsp"><img src="img/Gourmet-home.png" id="logo-home" alt="Gourmet Home"/></a>
        <button type="button" id="account-button" class="btn btn-primary"><% if(!session.isNew())
       { 
         %><%=(String)session.getAttribute("username")%><%
       } %></button>        
        <form  action="LogoutServlet" method="POST" class="logout-form" name="logout">
            <input type="submit" value="logout" id="logout-button" class="btn btn-primary">        
        </form>   
</header>