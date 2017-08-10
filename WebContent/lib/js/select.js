function select(id) {
	
	$.ajax({
		timeout : 3000,
		async : false,
		type : "POST",
		url : "select",
		dataType : "JSON",
		scriptCharset: 'utf-8',
		data : {
			data : id,
		},
		success : function(result) {
			for (var i = 0; i < result.length; i++) {
				if(id == 'sales_account_manager' && result[i].nickname.trim() == $("#name",window.parent.document).text().trim() ){
					$("#" + id).append("<option selected value="+result[i].id+">" + result[i].nickname + "</option>");
					continue
				}
				$("#" + id).append("<option value="+result[i].id+">" + result[i].nickname + "</option>");
			}
		},
		error:     function(XMLHttpRequest, textStatus, errorThrown){
	       }
	});
}