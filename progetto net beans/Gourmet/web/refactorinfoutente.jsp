<%@page import="db.User"%>
<%@page import="db.UserDao"%>
<%@page import="java.sql.PreparedStatement"%>
<html>
    <head>
        <title>Gourmet</title>
        <meta charset="UTF-8">
        <meta name="author" content="Mr. Big">
        <meta name="description" content="Find the restaurant of your desires">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/research.css">
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
    </head>
    <body>
        <%
            if(session.isNew())
            {                           
                session.setAttribute("type","normal");
            }
            //session.setAttribute("type","admin");
            String type= (String)session.getAttribute("type");
            if(type.equals("registered")) 
            { 
                %> <%@include file="menu-registered.jsp"%> <%
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
                        %> <%@include file="menu-admin.jsp"%> <%
                    }
                    else
                    { 
                        String redirectURL = "/error.jsp";
                        response.sendRedirect(redirectURL);
                    }
                }
            }
            String id=session.getAttribute("ID")+"";
            UserDao utenteDao=new UserDao();
            User Utente=utenteDao.UserDao(id, DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword"));
        %>
        <div class="signup-box">
            <p>Create a new account</p>
            <form class="createaccount-form" name="createaccount" action="refactoruserservlet" method="post">
                <input type="text" value="<%=Utente.getName()%>" name="Name" id="FirstName" spellcheck="false" placeholder="Nome">
                <input type="text" value="<%=Utente.getSurname() %>" name="Surname" id="LastName" spellcheck="false" placeholder="Cognome">
                <input type="text" value="<%=Utente.getUsername() %>" maxlength="30" autocomplete="off" name="nickname" id="UserName" spellcheck="false" placeholder="Username">
                <input type="mail" value="" maxlength="30" autocomplete="off" name="Email_" id="Email" spellcheck="false" placeholder="Email@address">
                <input type="password" name="Password_" id="Passwd" autocomplete="off" placeholder="Password">
                <input type="password" name="PasswdAgain" id="PasswdAgain" autocomplete="off" placeholder="Retype Password">
                <a href="index.jsp" id="cancel-button">Cancel</a>
                <input type="submit" value="Refactor" id="submit-button">
            </form>
        </div>
    </body>
</html>
                