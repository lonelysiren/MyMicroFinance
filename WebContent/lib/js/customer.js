/**
 * 
 */
layui.config({
base: './lib/js/'
});
layui.use(['form', 'jquery','layer'], function(){
  var form = layui.form(),
  layer = parent.layer === undefined ? layui.layer : parent.layer
		 console.log( $(this).data("name"));
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
		    },tabChange : function(filter, layid){
		        var TITLE = '.layui-tab-title'
		            ,tabElem = $('.layui-tab[lay-filter='+ filter +']')
		            ,titElem = tabElem.children(TITLE)
		            ,liElem = titElem.find('>li[lay-id="'+ layid +'"]');
		            call.tabClick(null, null, liElem);
		          }
  ,addClick : function(idcard){
		$(document).off('click','.layui-tab-title li:lt(2)')
		$(document).on('click','.layui-tab-title li:lt(2)',call.tabClick)
		call.tabChange('customer','1');
		$('#idcard_number').val(idcard);
  },changeFivteenToEighteen : function(card)  
  {  
	    if(card.length == '15')  
	    {  
	        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   
	        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');   
	        var cardTemp = 0, i;     
	        card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);  
	        for(i = 0; i < 17; i ++)   
	        {   
	            cardTemp += card.substr(i, 1) * arrInt[i];   
	        }   
	        card += arrCh[cardTemp % 11];   
	        return card;  
	    }  
	    return card;  
	}
  };
  //身份证验证
  $("button[lay-filter='check_id']").click(function() {
		var idcard = call.changeFivteenToEighteen($("#idcard").val()),
		
		sales_account_manager = $("#sales_account_manager").val();
		if(!sales_account_manager){
			layer.msg('请选择客户经理',{time: 1000, icon:6});
			return;
		}
		if(!idcard || !/(^\d{15}$)|(^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$)/i.test(idcard)){
			layer.msg('请输入正确的身份证',{time: 1000, icon:6});
			return;
	    }
		var load = layer.load(1);
	    	$.ajax({
	    		type:'post',
				url:'/customer_edit',
				data:{'action':'check_id','idcard_number':idcard,'sales_account_manager':sales_account_manager},
				success: function (result) {
					layer.close(load); 
						switch(result)
						{
						case '0':
							layer.open({
								title:'查询结果',
								content:'不存在该客户,点击下一步继续',
								btn:['下一步'],
								yes: function(index, layero){
									
									 call.addClick(idcard);
									 layer.close(index); 
								  }
							})
						  break;
						case '1':
							layer.open({
								title:'查询结果',
								content:'该客户已经存在是否继续,点击下一步继续',
								btn:['复制账号','下一步'],
								yes: function(index, layero){
									 layer.close(index); 
								  } ,btn2: function(index, layero){
									  	 call.addClick(idcard);
										 layer.close(index); 
								  }
							})
						  break;
						case '2' :
							layer.open({
								title:'查询结果',
								content:'公司其他员工已有此客户',
								btn:['确定'],
								yes: function(index, layero){
									 layer.close(index); 
								  }
							})
						  break;
						default:
						}
	                },
	            error: function (result, status) {
	            	layer.close(load); 
	            }
	    	});
	    
	});
  //监听提交
});

