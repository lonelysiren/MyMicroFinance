function select(id) {
	var realtion = /^relationship_[0-9]/;
	if (id == 'sales_account_manager') {
		$.ajax({
			timeout : 3000,
			async : false,
			type : "POST",
			url : "select",
			dataType : "JSON",
			scriptCharset : 'utf-8',
			data : {
				data : id,
			},
			success : function(result) {
				for (var i = 0; i < result.length; i++) {
					if (result[i].nickname.trim() == $("#name",
							window.parent.document).text().trim()) {
						$("#" + id).append(
								"<option selected value=" + result[i].id + ">"
										+ result[i].nickname + "</option>");
						continue
					}
					$("#" + id).append(
							"<option value=" + result[i].id + ">"
									+ result[i].nickname + "</option>");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	} else {
		var name = realtion.test(id) ? 'relationship' : id, option_data;
		switch (name) {
		case 'company_type':
			option_data = options.company_type;
			break;
		case 'education':
			option_data = options.education;
			break;
		case 'house_status':
			option_data = options.house_status;
			break;
		case 'idcard_type':
			option_data = options.idcard_type;
			break;
		case 'marriage_status':
			option_data = options.marriage_status;
			break;
		case 'relationship':
			option_data = options.relationship;
			var str
			for (i = 0; i < option_data.length; i++) {
					str  = str +	"<option value=" + option_data[i].id + ">"
								+ option_data[i].nickname + "</option>"
			}
			return str
			break;
		default:
			break;
		}
		for (i = 0; i < option_data.length; i++) {
			$("#" + id).append(
					"<option value=" + option_data[i].id + ">"
							+ option_data[i].nickname + "</option>");
		}
	}

}