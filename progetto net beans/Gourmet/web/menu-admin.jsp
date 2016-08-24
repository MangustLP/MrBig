<link rel="stylesheet" type="text/css" href="css/header.css">

<header>
    <@ (String)session.getAttribute("type"); @>
    <button type="button" id="account-button" class="btn btn-primary">Your Normal Account</button>
    <button type="button" id="admin-account-button" class="btn btn-primary">Your Admin Account</button>
    <form  action="LogoutServlet" method="POST" class="logout-form" name="logout">
        <input type="submit" value="logout" id="logout-button" class="btn btn-primary">        
    </form>    
</header>