/**
 * 
 */
  var content = '<div name="contact"><div class="layui-row" ><div class="layui-col-md3"><div data-id="contact_id"></div><div class="layui-form-item"><label class="layui-form-label">联系人</label><div class="layui-input-block"><select id="relationship"  name="relationship[]" lay-filter="contact"><option value="">请选择关系(必选)</option></select></div></div></div><div class="layui-col-md3"><div class="layui-form-item"><label class="layui-form-label">姓名</label><div class="layui-input-block"><input type="text"  name="contact_name[]" autocomplete="off" class="layui-input" placeholder="请输入联系人姓名"></div></div></div><div class="layui-col-md3"><div class="layui-form-item"><label class="layui-form-label">联系电话</label><div class="layui-input-block"><input type="text"  name="contact_mobile_phone[]" autocomplete="off" class="layui-input" placeholder="请输入联系人电话"></div></div></div></div><div class="layui-row"><div class="layui-col-md3"></div><div class="layui-col-md6"><div class="layui-form-item"><label class="layui-form-label">工作单位</label><div class="layui-input-block"><input type="text"  name="contact_company[]" autocomplete="off" class="layui-input" placeholder="请输入联系人工作单位"></div></div></div></div></div>';
  for ( i = 0; i < 5; i++) {
		$("#other_contact").before(content)
	}
  $("select").each(function(index,dom){
		var id = $(dom).attr("id")
		if(id =="user_id" || id== "pay_stauts" || !id) return
		var str = select(id);
		if(str){
			$(dom).append(str);
		}
	});
  $("#credit_add").click(function(){
	  var html = '<div name="debt_creditcard" class="layui-row" ><div class="layui-col-md3"><div class="layui-form-item" ><label class="layui-form-label">信用卡</label><div class="layui-input-block"><input type="text" name="creditcard_name" autocomplete="off" class="layui-input" placeholder="请输入发卡行" ></div></div></div><div class="layui-col-md3"><div class="layui-form-item" ><label class="layui-form-label">授信额度</label><div class="layui-input-block"><input type="text" name="creditcard_limit[]" autocomplete="off" class="layui-input" placeholder="请输入金额" ></div></div></div><div class="layui-col-md3"><div class="layui-form-item" ><label class="layui-form-label">已使用额度</label><div class="layui-input-block"><input type="text" name="creditcard_used[]" autocomplete="off" class="layui-input" placeholder="请输入已经用金额" ></div></div></div><div class="layui-col-md3"><div class="layui-form-item" ><button type="button"  class="layui-btn layui-btn-danger" onclick="remove_input(this)" >删除</button></div></div></div>';
	 $('#lingyong').before(html)
  });
  $("#lingyong_add").click(function(){
	  var html = '<div class="layui-row" name="debt_lingyong"><div class="layui-col-md3"><div class="layui-form-item" ><label class="layui-form-label">零用贷</label><div class="layui-input-block"><input type="text" name="lingyong_name" autocomplete="off" class="layui-input" placeholder="请输入零用贷名称" ></div></div></div><div class="layui-col-md3"><div class="layui-form-item" ><label class="layui-form-label">金额</label><div class="layui-input-block"><input type="text" name="lingyong_amount[]" autocomplete="off" class="layui-input" placeholder="请输入零用贷额度" ></div></div></div><div class="layui-col-md3"><div class="layui-form-item" ><button type="button" onClick="remove_input(this)"  class="layui-btn layui-btn-danger" >删除</button></div></div></div>';
	  $('#other').before(html)
  });
  $("#other_add").click(function(){
	  var html = '<div class="layui-row" name="debt_other"><div class="layui-col-md3" ><div class="layui-form-item" ><label class="layui-form-label">其他贷款</label><div class="layui-input-block">   		<input type="text" name="other_name" autocomplete="off" class="layui-input" placeholder="请输入其他贷款名称" lay-verify="required"></div></div></div><div class="layui-col-md3"><div class="layui-form-item" >   		<label class="layui-form-label">金额</label>   		<div class="layui-input-block">   		<input type="text" name="other_amount[]" autocomplete="off" class="layui-input" placeholder="请输入其他金额" lay-verify="required"></div></div></div><div class="layui-col-md3"><div class="layui-form-item" >   		<button type="button" onClick="remove_input(this)"  class="layui-btn layui-btn-danger" >删除</button></div></div></div>';
	  $('#final').before(html)
  });
  function remove_input(obj){
		 $(obj).parent().parent().parent().remove();
		  return false;
	 }
  function check(odata,ndata){
	  var temp = {},id = {}
	   layui.each(odata,function(name,value){
		   if(name.indexOf("_id" ) > 0){
				id[name] = value
				return
			}
		   name = name + "[]";
		   if(value != ndata[name]){
				temp[name] = ndata[name]
			}
	   })
	   if($.isEmptyObject(temp)) return 
	   temp = $.extend({},temp,id)
	   return temp
	  };
	  
function getData(obj,key,length,mode){
	var obj = $(obj).find("[name='"+ key +"']"),
	data = {},
	datas = [];
	layui.each(obj,function(index,value){
		fieldElem = $(value).find('input,select,textarea')
		layui.each(fieldElem,function(_,item){
			if(!item.name) return;
		     if(/^checkbox|radio$/.test(item.type) && !item.checked) return;
		     if(index >= length && item.value =="") return;
		     data[item.name] = item.value
		})
		if($.isEmptyObject(data)) return
		 datas[index] = data
		 data = {}
	})
	return (mode==1) ? datas[0] : datas
//	layui.each(fieldElem,function(_,item){
//		console.log(item.name,item.value);
//		if(!item.name) return;
//	     if(/^checkbox|radio$/.test(item.type) && !item.checked) return;
//		 if(data.hasOwnProperty(item.name)){
//			 datas[index] = data
//				index++
//				data = {}
//		 	} 
//		data[item.name] = item.value
//	})
//	     if(!($.isEmptyObject(data)))  datas[index] = data
	
}	  

function loadData(jsonStr,index){
			var obj = jsonStr;
			var index = index || 0
			var key,value,tagName,type,arr;
			  if(obj.constructor === Object){
					for(x in obj){
						key = x;
						value = obj[x];
						if(key.indexOf("_id") > 0 ){
							var html = "<input type='hidden' name='"+key+"' value='"+ value +"'>"
							$("[data-id="+key+"]").eq(index).append(html);
						}
						var ths = $("[name='"+key+"'],[name='"+key+"[]']").eq(index);
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