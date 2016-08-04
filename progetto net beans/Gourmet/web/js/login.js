$(document).ready(function(){

	$("#login-button").click(function(){
		window.location.href="login.jsp";
	});

	$("#register-button").click(function(){
		window.location.href="register.jsp";
	});
	
	$("#search-button").click(function(){
		window.location.href="research.jsp?what="+document.getElementById("search-name").value+"&where="+document.getElementById("search-location").value;
	});
});