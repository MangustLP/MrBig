<!DOCTYPE html>
<html>
	<head>
		<title>Gourmet - Register a new account</title>
		<meta charset="UTF-8">
		<meta name="author" content="Mr. Big">
		<meta name="description" content="Register a new account">
		<link rel="stylesheet" href="css/register.css">
                <script src="js/login.js" type="text/javascript"></script>
	</head>

	<body background="img/wine.jpg">
		<div class="signup-box">
			<p>Create a new account</p>
			<form class="createaccount-form" name="createaccount" action="RegistrationServlet" method="post">
			  	<input type="text" value="" name="Name" id="FirstName" spellcheck="false" placeholder="Nome">
			  	<input type="text" value="" name="Surname" id="LastName" spellcheck="false" placeholder="Cognome">
			  	<input type="text" value="" maxlength="30" autocomplete="off" name="nickname" id="UserName" spellcheck="false" placeholder="Username">
			  	<input type="email" value="" maxlength="30" autocomplete="off" name="Email_" id="Email" spellcheck="false" placeholder="email@address">
			  	<input type="password" name="Password_" id="Passwd" autocomplete="off" placeholder="Password">
			  	<input type="password" name="PasswdAgain" id="PasswdAgain" autocomplete="off" placeholder="Retype Password">         
                                <input type="checkbox" name="privacy" id="privacy"><label id="l-privacy">I accept the <a href="javascript:open('privacy.html');">Terms of Tratements</a> of my personal data</label>
                                <input type="button" value="Cancel" id="cancel-button" onClick="history.go(-1);">
			  	<input type="submit" value="Register me now!" id="submit-button" onClick="index.jsp"></form>
		</div>
	</body>
</html>