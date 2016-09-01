$(document).ready(function(){

	$("#login-button").click(function(){
		window.location.href="login.jsp";
	});

	$("#register-button").click(function(){
		window.location.href="register.jsp";
	});
        $("#account-button").click(function(){
		window.location.href="account-registered.jsp";
	});
        
        $("#admin-notif").click(function(){
		window.location.href="admin-notifications.jsp";
	});   
        $("#refactor-button").click(function(){
		window.location.href="refactorinfoutente.jsp";
	});      
        $("#admin-account").click(function(){
		window.location.href="account-registered.jsp";
	});
        
        $("#reset-button").click(function(){
		$('input:checkbox').removeAttr('checked');
                $("#every_price").prop("checked", true);
                //$("#apply-button").click();
	});
        
        
        function open(url) { 
        newin = window.open(url,'titolo','scrollbars=no,resizable=yes, width=200,height=200,status=no,location=no,toolbar=no');
} 
});