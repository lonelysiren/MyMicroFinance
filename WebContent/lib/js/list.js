function loadData(jsonStr,index){
			var obj = jsonStr;
			var index = index || 0
			var key,value,tagName,type,arr;
			  if(obj.constructor === Object){
					for(x in obj){
						key = x;
						value = obj[x];
						if(key.indexOf("contract_id") > 0 ){
							var html = "<input type='hidden' name='"+key+"' value='"+ value +"'>"
							$("[data-id="+key+"]").eq(index).append(html);
						}
						if(key =="next_payday") value = value.replace(" 00:00:00","")
						var ths = $("[name='"+key+"'],[name='"+key+"[]']").eq(index);
							if(ths.length == 0) {
								alert(key);
							}
							tagName = $(ths)[0].tagName;
							type = $(ths).attr('type');
							if(tagName=='INPUT'){
								if(type=='radio'){
									  $(":radio[name='"+key+"'][value='" + value + "']").prop("checked", "checked");
								}else{
									$(ths).val(value);
								}
							}else if(tagName=='SELECT' || tagName=='TEXTAREA'){
								$(ths).val(value);
							}
					}
			    } else {
			      for(key = 0; key < obj.length; key++){
			        loadData(obj[key],key);
			      }
			    }
		}

function mdate(mydate){//将当前时间转换成yyyymmdd格式
    var str = "" + mydate.getFullYear();
    var mm = mydate.getMonth()+1
    if(mydate.getMonth()>9){
     str += mm;
    }
    else{
     str += "0" + mm;
    }
    if(mydate.getDate()>9){
     str += mydate.getDate();
    }
    else{
     str += "0" + mydate.getDate();
    }
    return str;
  }