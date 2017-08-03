/**
 * 
 */
layui.config({
base: './lib/js/'
});
layui.use(['form','element','layer'], function(){
  var form = layui.form(),
  layer = parent.layer === undefined ? layui.layer : parent.layer,
  element = layui.element();
$("select").each(function(index,dom){
	select($(dom).attr("id"));

});
  form.render();
  //tab切换验证
 var item = $('.layui-tab').eq(0).children('.layui-tab-content').children('.layui-tab-item').eq(0).children('.layui-form-item').eq(0);
 console.log(item);
  element.on('tab', function(data){
	 if(data.index !=0){
		 var item = data.elem.children('.layui-tab-content').children('.layui-tab-item').eq(data.index-1);
		 
		
	
		  //element.tabChange('customer',data.index-1);
	 }
  });
  //身份证验证
  $("button[lay-filter='check_id']").click(function() {
		var idcard = $("#idcard").val(),
		sales_account_manager = $("#sales_account_manager").val();
		if(!sales_account_manager){
			layer.msg('请选择客户经理',{time: 1000, icon:6});
			return;
		}
		if(!idcard || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(idcard)){
			layer.msg('请输入正确的身份证',{time: 1000, icon:6});
			return;
	    }
	    	$.ajax({
	    		type:'post',
				url:'/customer_edit',
				data:{'action':'check_id','idcard_number':idacrd,'sales_account_manager':sales_account_manager},
				success: function (result) {
	                },
	            error: function (result, status) { }
	    	});
	    
	});
  //监听提交
  form.on('submit(formDemo)', function(data){
	  element.tabChange('customer', '个人信息');
	 
    return false;
  });
});
