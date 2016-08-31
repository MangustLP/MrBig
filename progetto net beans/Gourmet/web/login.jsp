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
			  	<input type="text" value="" maxlength="30" autocomplete="off" name="nickname" id="UserName" spellcheck="false" placeholder="Username">
			  	<input type="password" name="pass" id="Passwd" autocomplete="off" placeholder="Password">
			  	<input type="button" value="Back" class="login-button" id="cancel-button" onClick="history.go(-1);">
			  	<input type="submit" value="Login" class="login-button" id="submit-button" onClick="history.go(-1);">
			  	<a href="" id="resetPass">Forgot Password?</a>
			</form>
		</div>
	</body>
</html>