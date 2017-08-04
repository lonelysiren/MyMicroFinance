/**
 * 
 */
layui.config({
base: './lib/js/'
});
layui.use(['form', 'jquery','layer'], function(){
  var form = layui.form(),
  layer = parent.layer === undefined ? layui.layer : parent.layer
		  
$("select").each(function(index,dom){
	select($(dom).attr("id"));

});
  form.render();
  //向导式表单
  var call = {
		//Tab点击
		    tabClick: function(e, index, liElem){
		      var othis = liElem || $(this)
		      ,index = index || othis.parent().children('li').index(othis)
		      ,parents = othis.parents('.layui-tab').eq(0)
		      ,item = parents.children('.layui-tab-content').children('.layui-tab-item')
		      ,filter = parents.attr('lay-filter')
		      ,THIS = 'layui-this'
		      , SHOW = 'layui-show';
		      
		      othis.addClass(THIS).siblings().removeClass(THIS);
		      item.eq(index).addClass(SHOW).siblings().removeClass(SHOW);
		    }
  ,addClick : function(){
		$(document).off('click','.layui-tab-title li:lt(2)')
		$(document).on('click','.layui-tab-title li:lt(2)',call.tabClick)
  }
  };
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
				data:{'action':'check_id','idcard_number':idcard,'sales_account_manager':sales_account_manager},
				success: function (result) {
					
	                },
	            error: function (result, status) { }
	    	});
	    
	});
  //监听提交
});

