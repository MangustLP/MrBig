<!DOCTYPE html>
<html>
	<head>
		<title>Gourmet - Login</title>
		<meta charset="UTF-8">
		<meta name="author" content="Mr. Big">
		<meta name="description" content="Login Page">
		<link rel="stylesheet" href="css/login.css">
		<script type="text/javascript" src="js/login.js"></script>
	</head>

	<body background="img/wine.jpg">
		<div class="login-box">
			<p>Login</p>
			<img src="img/account.png">
			<form action="LoginServlet" method="POST" class="login-form" name="login" >
			  	<input type="text" value="" maxlength="30" autocomplete="off" name="UserName" id="UserName" spellcheck="false" placeholder="Username">
			  	<input type="password" name="Passwd" id="Passwd" autocomplete="off" placeholder="Password">
			  	<a href="index.jsp"  class="login-button" id="cancel-button">Cancel</a>
			  	<input type="submit" value="Login" class="login-button" id="submit-button">
			  	<a href="" id="resetPass">Forgot Password?</a>
			</form>
		</div>
	</body>
</html>