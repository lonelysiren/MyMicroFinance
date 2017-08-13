/**
 * 
 */
layui.config({
base: './lib/js/'
});
layui.use(['form', 'jquery','layer'], function(){
  var form = layui.form(),
  layer = parent.layer === undefined ? layui.layer : parent.layer
;
	
$("select").each(function(index,dom){
	select($(dom).attr("id"));
});
  //
  $("#relation_add").click(function(){
	  
  });
  form.on('select(house_status)', function(data){
	  if(data.value == '1'){
		  $("#current_residence").val($("#census_register").val()+"-"+$("#census_register_detail").val());
	  }
	});  
  form.on('select(contact)', function(data){
	  $("input[lay-filter= '"+data.elem.id+"']").attr({"lay-verify":"required","disabled": false});
  });
  form.render();
  //向导式表单
  var call = {
		  //验证
		  verify: {
	        required: [
	            /[\S]+/
	            ,'必填项不能为空'
	          ]
	          ,phone: [
	            /^1\d{10}$/
	            ,'请输入正确的手机号'
	          ]
	          ,email: [
	            /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
	            ,'邮箱格式不正确'
	          ]
	          ,url: [
	            /(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/
	            ,'链接格式不正确'
	          ]
	          ,number: [
	            /^\d+$/
	            ,'只能填写数字'
	          ]
	          ,date: [
	            /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/
	            ,'日期格式不正确'
	          ]
	          ,identity: [
	            /(^\d{15}$)|(^\d{17}(x|X|\d)$)/
	            ,'请输入正确的身份证号'
	          ]
	        },
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
		    },
		    //tab切换
		    tabChange : function(filter, layid){
		        var TITLE = '.layui-tab-title'
		            ,tabElem = $('.layui-tab[lay-filter='+ filter +']')
		            ,titElem = tabElem.children(TITLE)
		            ,liElem = titElem.find('>li[lay-id="'+ layid +'"]');
		            call.tabClick(null, null, liElem);
		          },
		      //绑定点击    
			  addClick : function(){
					$(document).off('click','.layui-tab-title li:lt(2)')
					$(document).on('click','.layui-tab-title li:lt(2)',call.tabClick)
					idcard = $('#idcard').val();
					call.tabChange('customer','1');
					if (!(parseInt(idcard.substr(16, 1)) % 2 == 1)) { 
						$('#famale').attr('checked','true');
						form.render('radio');
						}
					var myDate = new Date(); 
					var month = myDate.getMonth() + 1; 
					var day = myDate.getDate(); 
					var age = myDate.getFullYear() - idcard.substring(6, 10) - 1; 
					if (idcard.substring(10, 12) < month || idcard.substring(10, 12) == month && idcard.substring(12, 14) <= day) { 
					age++; 
					} 
					$('#age').val(age);
					$('#idcard_number').val(idcard);
			  },
			  //15位身份证转换18
			  changeFivteenToEighteen : function(card){  
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
			},
			//提交验证
			submit : function(options){
				var button = options.dom, verify = call.verify, stop = null
			    ,DANGER = 'layui-form-danger', field = {} ,elem = button.parents('.layui-tab-item')
			    ,verifyElem = elem.find('*[lay-verify]') //获取需要校验的元素
			    ,fieldElem = elem.find('input,select,textarea') //获取所有表单域
			    ,filter = button.attr('lay-filter')//获取过滤器
				,device = layui.device();
			    //开始校验
			    layui.each(verifyElem, function(_, item){
			      var othis = $(this), ver = othis.attr('lay-verify').split('|');
			      var tips = '', value = othis.val();
			      othis.removeClass(DANGER);
			      layui.each(ver, function(_, thisVer){
			        var isFn = typeof verify[thisVer] === 'function';
			        if(verify[thisVer] && (isFn ? tips = verify[thisVer](value, item) : !verify[thisVer][0].test(value)) ){
			          layer.msg(tips || verify[thisVer][1], {
			            icon: 5
			            ,shift: 6
			          });
			          //非移动设备自动定位焦点
			          if(!device.android && !device.ios){
			            item.focus();
			          }
			          othis.addClass(DANGER);
			          return stop = true;
			        }
			      });
			      if(stop) return stop;
			    });
			    if(stop) return false;
			   if(options.before) options.before()
			    layui.each(fieldElem, function(_, item){
			      if(!item.name) return;
			      if(/^checkbox|radio$/.test(item.type) && !item.checked) return;
			      field[item.name] = item.value;
			    });
			    field['sales_account_manager'] = $("#sales_account_manager").val();

					  var load = layer.load(1);
					  $.ajax({
						  type:'post',
						  url:options.url,
						  data:{'action' : options.action,'data' :JSON.stringify(field),'customer_id':'10'},//$("#customer_id").val()},
						  success:function(result){
							  layer.close(load);
							  options.yes(result);	 //请求成功的回调函数						  
						  }, error: function (result, status) {
				            	layer.close(load); 
				            	layer.msg('请求失败');
				          }
					  })
			}
  };
  //测试用代码
  $(document).on('click','.layui-tab-title li ',call.tabClick)
   //提交用户信息
  $('#cusotmer_info_commit').click(function(){
	  call.submit({
		  dom:$(this),
		  url:'/customer_edit',
		  action:'customer_info',
		  yes:function(result){
			  if(result != '0'){
				$('#sales_account_manager').append("<input type='hidden' name='customer_id' id='customer_id' value="+ result +">")
				$(document).on('click','.layui-tab-title li:eq(2)',call.tabClick)
				call.tabChange('customer','2');
			  }else {
				  layer.msg('客户添加失败');
			  }
		  }
	  })
  });
  //提交用户
  //提交联系人
  $('#customer_contact_commit').click(function(){
	  call.submit({
		  dom:$(this),
		  url:'/customer_edit',
		  action:'customer_relation_info',
		  yes:function(result){
				 var dom=  $('#contact').find('.col-md-6'),i=0
				 result = JSON.parse(result) ;
				 $.each(result,function(key,value){
					 if(key == 'id_6'){
						 i = 5;
					 }
					 dom.eq(i).append('<input type="hidden" name='+key +' value='+result[key]+'>');
					 i++;
					 })
					 $(document).on('click','.layui-tab-title li:eq(3)',call.tabClick)
						call.tabChange('customer','3');
		  }
	  })
  });
  //提交联系人
  //提交用户公司
  $('#cusotmer_company_commit').click(function(){
	  call.submit({
		    dom:$(this),
		  url:'/customer_edit',
		  action:'customer_company_info',
		  yes:function(result){
			  if(result != '0'){
					$('#cusotmer_company_commit').append("<input type='hidden' name='company_id' value="+ result +">")
					$(document).on('click','.layui-tab-title li:eq(4)',call.tabClick)
					call.tabChange('customer','4');
				  }else {
					  layer.msg('客户添加失败');
				  }
		  }
	  })
  })
  //提交公司用户
  //查重验证
  $("#check_id").click(function(){
	call.submit({
		dom:$(this),
		url:'/customer_edit',
		action:'check_id',
		before:function(){
			$("#idcard").val(call.changeFivteenToEighteen($("#idcard").val()));
		},
		yes:function(result){
			switch(result)
			{
			case '0':
				layer.open({
					title:'查询结果',
					content:'不存在该客户,点击下一步继续',
					btn:['下一步'],
					yes: function(index, layero){
						 call.addClick();
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
						  	 call.addClick();
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
				  break;
			}
		}
	})  
  })
    //身份证验证
 
});

