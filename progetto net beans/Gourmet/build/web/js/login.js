$(document).ready(function(){

	$("#login-button").click(function(){
		PopupLogin("login.html", 350);
	});

	$("#register-button").click(function(){
		PopupLogin("register.html", 400);
	});

});

function PopupLogin(page, height)
{
  var w = 400;
  var h = height;
  var l = Math.floor((screen.width-w)/2);
  var t = Math.floor((screen.height-h)/2);
   
  window.open(page, "", "width=" + w + ",height=" + h + ",top=" + t + ",left=" + l);
};