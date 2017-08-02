function select( id) {
	console.log(id)
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
				$("#" + id).append("<option value="+result[i].id+">" + result[i].nickname + "</option>");
			}
		},
		error:     function(XMLHttpRequest, textStatus, errorThrown){
			console.log(textStatus)
	       }
	});
}