function CheckPasswordMatch()
{
	var pass =  $("#pass").val();
	var repass = $("#confirm").val();
	
	if(pass.length <= repass.length )
	{
		if( pass != repass)
		{
			window.alert("passwords dosen't match please enter same password");
			return(false);
		}
		else
			return(true);
	}
	return(false);
}