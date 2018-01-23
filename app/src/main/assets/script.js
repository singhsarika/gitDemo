$(document).ready(function(){
	$('body').on('click','.removeRow',function(){
		$(this).parent().parent().remove();
	});
	$('body').on('click',".addRow",function(){
		$(".overlay").fadeIn();
		$("#popuForm").fadeIn();
	});
	$("#addNewRow").on("click",function(e){
		e.preventDefault();
		var userName = userForm.userName.value;
		var tech = userForm.technology.value;
		var Email = userForm.email.value;
		var Address = userForm.addrs.value;
		if(userName !="" && tech!="" && Email!="" && Address!=""){
			$("tbody").append("<tr><td>"+userName+"</td><td>"+tech+"</td><td>"+Email+"</td><td>"+Address+"</td><td><span class='addRow'>add</span><span class='removeRow'>delete</span></td><td>");
		}
		$("#popuForm").fadeOut();
		$(".overlay").fadeOut();
		
		emptyFields();
	});
	$(".overlay").on("click",function(e){
		$("#popuForm").fadeOut();
		$(".overlay").fadeOut();
		emptyFields();
	});
	$(".overlay1").on("click",function(e){
		
		$(".overlay1").fadeOut();
		$(".popuForm1").fadeOut();
	});
		
	$(".imgGalWraper").on("click",function(){
		
		$(".popuForm1").find("img").attr('src',$(this).find("img").attr("src"));
		$(".overlay1").fadeIn();
		$(".popuForm1").fadeIn();
	});
	
});
function emptyFields(){
		userForm.userName.value="";
		userForm.technology.value="";
		userForm.email.value="";
		userForm.addrs.value="";
	}
