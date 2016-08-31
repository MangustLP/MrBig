<%@page import="java.sql.DriverManager"%>
<%@page import="db.RistoranteDAO"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="css/header.css">
<% String username=(String)session.getAttribute("username");
   Integer id=(Integer)session.getAttribute("ID");
   ArrayList<String> arraynomi=new ArrayList<String>();
   ArrayList<Integer> arrayid=new ArrayList<Integer>();
   RistoranteDAO ristodao=new RistoranteDAO();
   arraynomi=ristodao.getNames(""+id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
   arrayid=ristodao.getIds(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
   
%>
<header>    
    <a href="index.jsp"><img src="img/Gourmet-home.png" id="logo-home" alt="Gourmet Home"/></a>
    <button type="button" id="account-button" class="btn btn-primary"> <% if(!session.isNew())
       { 
            String nomecompleto=(String) session.getAttribute("name")+" "+(String) session.getAttribute("surname");
         %><%=nomecompleto%><%
       } %></button>  
    <form  action="LogoutServlet" method="POST" class="logout-form" name="logout" id="logoutform">
        <input type="submit" value="Logout" id="logout-button" class="btn btn-primary">        
    </form>  
</header>