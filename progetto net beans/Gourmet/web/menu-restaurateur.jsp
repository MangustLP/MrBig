<%@page import="java.sql.DriverManager"%>
<%@page import="db.RistoranteDAO"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="css/header.css">
<% String username=(String)session.getAttribute("username");
   Integer id=(Integer)session.getAttribute("ID");
   ArrayList<String> arraynomi=new ArrayList<>();
   ArrayList<Integer> arrayid=new ArrayList<>();
   RistoranteDAO ristodao=new RistoranteDAO();
   arraynomi=ristodao.getNames(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
   arrayid=ristodao.getIds(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
   System.out.println("I ristoranti posseduti da "+username+" sono:");
   for (int i=0;i<arraynomi.size();i++)
    System.out.println(arraynomi.get(i)+ " "+ arrayid.get(i));
   System.out.println("Fine");
   
%>
<header>    
     <a href="index.jsp"><img src="img/Gourmet-home.png" id="logo-home" alt="Gourmet Home"/></a>
     
    <button type="button" id="restourant-button" class="btn btn-primary">Your Restourant</button>
    
    <button type="button" id="account-button" class="btn btn-primary"> <% if(!session.isNew())
       { 
         %><%=(String)session.getAttribute("username")%><%
       } %></button>
       <button type="=button" id="restourant-notif" class="btn btn-primary">notification</button>
    <form  action="LogoutServlet" method="POST" class="logout-form" name="logout" id="logoutform">
            <input type="submit" value="logout" id="logout-button" class="btn btn-primary">        
    </form> 
    
</header>