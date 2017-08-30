function edit_page($,product,layer,obj){
	$.get('./lib/temp/edit-product.html', null, function(form) {
		addBoxIndex = layer.open({
			type: 1,
			title: '编辑产品',
			content: form,
			btn: ['保存', '取消'],
			shade: false,
			offset: ['15%', '0%'],
			area: ['100%', '500px'],
			zIndex: 19950924,
			maxmin: true,
			yes: function(index) {
				//触发表单的提交事件
				$('form.layui-form').find('button[lay-filter=edit]').click();
			},
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
					var form = layui.form,i=0;
					$("form input").each(function(index,element){
						if(element.name =="product_isAdvance" ){
							  $(element).attr("checked",product[element.name]);
								console.log(product[element.name])
							  return
						}
						$(element).val(product[element.name]);
					})
					  $("select[name='product_payment_method']").val(product['product_payment_method']);
				
					$("#product_remark").val(product['product_remark']);
					form.render();
				form.on('submit(edit)', function(data) {
					var data = data.field,temp={},fields={}
					layui.each(product,function(key,value){
						console.log(key,"====","原值:",product[key],"新值:",value)
						if(value != data[key]){
							temp[key] =  data[key]
							fields[key]=data[key]
						}
					})
					 if($.isEmptyObject(temp)) {layer.close(index); return false;}
						
					 temp['product_id'] = data['product_id']
					$.ajax({
						type:'post',
						url:'/product_edit',
						data:{'product':JSON.stringify(temp)},
						success: function (result) {
							if(result == "success"){
								parent.layer.msg("修改成功");
								 obj.update(fields);
							}else{
								parent.layer.msg("修改失败");
							}
							layer.close(index);
						}
					});							
					//这里可以写ajax方法提交表单
					return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
				});
			},
			end: function() {
				addBoxIndex = -1;
			}
		});
	});
}
function delete_page(product_id){
	
}