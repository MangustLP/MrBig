$(document).ready(function(){

	$("#login-button").click(function(){
		PopupLogin();
	});

});

function PopupLogin()
{
  var w = 400;
  var h = 350;
  var l = Math.floor((screen.width-w)/2);
  var t = Math.floor((screen.height-h)/2);
   
  window.open("login.html", "", "width=" + w + ",height=" + h + ",top=" + t + ",left=" + l);
};