/**
 * 
 */
layui.config({
base: './lib/js/'
});
function remove_input(obj){
	 $(obj).parent().parent().parent().remove();
	  return false;
 }
layui.use(['form', 'jquery','layer'], function(){
  var form = layui.form(),
  layer = parent.layer === undefined ? layui.layer : parent.layer
;
  var text = 'lay-verify="required"';
  
 
  var content = '<div class="row"><div class="col-md-3"><div class="layui-form-item"><label class="layui-form-label">联系人</label><div class="layui-input-block"><select id="relationship"  name="relationship[]" lay-filter="contact"><option value="">请选择关系(必选)</option></select></div></div></div><div class="col-md-3"><div class="layui-form-item"><label class="layui-form-label">姓名</label><div class="layui-input-block"><input type="text"  name="contact_name[]" autocomplete="off" class="layui-input" placeholder="请输入联系人姓名"></div></div></div><div class="col-md-3"><div class="layui-form-item"><label class="layui-form-label">联系电话</label><div class="layui-input-block"><input type="text"  name="contact_mobile_phone[]" autocomplete="off" class="layui-input" placeholder="请输入联系人电话"></div></div></div></div><div class="row"><div class="col-md-3"></div><div class="col-md-6"><div class="layui-form-item"><label class="layui-form-label">工作单位</label><div class="layui-input-block"><input type="text"  name="contact_company[]" autocomplete="off" class="layui-input" placeholder="请输入联系人工作单位"></div><input type="hidden" name=contact_id[] value=0></div></div></div>';
  for ( i = 0; i < 5; i++) {
		$("#other_contact").before(content)
	}
$("select").each(function(index,dom){
	var str = select($(dom).attr("id"));
	if(str){
		$(dom).append(str);
	}
});
  //
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
		      //身份证校验点击    
			  calc : function(){
				  $(document).off('click','.layui-tab-title li:eq(0)')
				  $(document).on('click','.layui-tab-title li:eq(0)',call.tabClick)
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
					$('#idcard_type').children("[value='1']").attr("selected",true);
					form.render('select');
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
			 var obj ={},objs = [] //
			   layui.each(fieldElem, function(_, item){
			      if(!item.name) return;
			      if(/^checkbox|radio$/.test(item.type) && !item.checked) return;
			      if(item.name.indexOf('[]') != -1){
			    	  if(obj.hasOwnProperty(item.name)){
			    		  objs.push(obj)
			    		  obj = {}
			    		  obj[item.name] = item.value
			    		  return
			    	  } 
			    		  obj[item.name] = item.value
			    		  return
			      }
			      field[item.name] = item.value;
			    });
			   	  if(!($.isEmptyObject(obj))) objs.push(obj)
			      if(objs.length != 0) field['Object'] = objs
					    var tempData,
					   url=options.url,
					   data = field
					   ,action=options.action,
					   oldData=initformdata[action],
					   index =options.tab_index;
			    if(action=='check_id' || action=='customer_info') {
			    	field['sales_account_manager'] = $("#sales_account_manager").val();
			    	}

			    if(null != oldData){
			    	tempData = call.isEdit(oldData,field)//第二次点击提交 进行重新编辑判断
				    if(tempData){//有重新编辑---改变请求地址
				    	url = url.replace('add','edit');
				    	data = tempData;
				    }else{//没有重新编辑 --- 下一步
				    	console.log("下一步")
				    	call.tabChange('customer',index);
				    	return
				    }
			    }
				initformdata[action] = field;
			    console.log(tempData);
			    return
			     //新增
			    var load = layer.load(1);
					  var xhr =$.ajax({
						  type:'post',
						  url:url,
						  timeout: 5000, 
						  data:{'action' : action,'data' :JSON.stringify(data),'customer_id':'10'},//$("#customer_id").val()},
						  beforeSend:function(xhr){
						  },
						  success:function(result){
							  if(result == 'next' || result =='success'){
							  }else{
								  options.yes(result,field);	 //请求成功的回调函数
								  if(index){
									  $(document).off('click','.layui-tab-title li:eq('+index+')')
									  $(document).on('click','.layui-tab-title li:eq('+index+')',call.tabClick)
								  }
							  }
								call.tabChange('customer',index);
								initformdata[action] = field;
						  }, error: function (result, status) {
							 alert("错误码:"+status)
							 location.reload();
						  },complete: function(XMLHttpRequest,status){
				        	  layer.close(load); 
				        	  if(status == 'timeout') {
				                    xhr.abort();    // 超时后中断请求
				                    $.alert("网络超时，请刷新", function () {
				                        location.reload();
				                    })}
				          }
					  })
			},
			isEdit : function(oldData,newData){
				    	var result= {},editData= {},editArray = [],length = 0;
				    	layui.each(newData,function(key,item){//遍历提交对象
				    		 if(item.constructor === Array){//遍历提交对象中数组对象
				    			 for(var i=0;i<item.length;i++){
				    				  var temp = call.isEdit(oldData[key][i],item[i])
				    				  console[isEdit['length']];
					    		      if(temp) editArray.push(temp);
				    				}
				    			 if(editArray.length){ 
				    				 editData[key] = editArray;
				    				 length++;
				    			 }
				    		      return;
				    		 }
				    		var isId = key.substr(key.length-2)
				    		var isIdArr =  key.substr(key.length-4,2)
				    		if( isIdArr == 'id'){
					        	editData[key] = newData[key];
					        	length++;
					        	return 
					        }
				    		if( isId == 'id'){
					        	editData[key] = newData[key];
					        	return 
					        }
			    	        if (newData[key] != oldData[key])  
			    	        {  
			    	        	editData[key] = newData[key];
			    	        	length++;
			    	        }  
				    	})
				    	    if(length <1) return;
				    	    if(editData) return editData;
			}
  };
  //测试用代码
  $(document).on('click','.layui-tab-title li ',call.tabClick)
  var initformdata = {'customer_info': null,'customer_info_contact':null,'customer_info_company':null}
  var a = {"contact_content":"1","Object":[{"customer[]_id":"1","relationship[]":"1","contact_name[]":"123","contact_mobile_phone[]":"123","contact_company[]":"213"},{"relationship[]":"123","contact_name[]":"123","contact_mobile_phone[]":"123","contact_company[]":"123"},{"relationship[]":"","contact_name[]":"","contact_mobile_phone[]":"","contact_company[]":""},{"relationship[]":"","contact_name[]":"","contact_mobile_phone[]":"","contact_company[]":""},{"relationship[]":"","contact_name[]":"","contact_mobile_phone[]":"","contact_company[]":""}]};
   //提交用户信息
  var b = {"contact_content":"1","Object":[{"customer[]_id":"1","relationship[]":"1","contact_name[]":"123","contact_mobile_phone[]":"123","contact_company[]":"213"},{"relationship[]":"123","contact_name[]":"123","contact_mobile_phone[]":"123","contact_company[]":"123"},{"relationship[]":"","contact_name[]":"","contact_mobile_phone[]":"","contact_company[]":""},{"relationship[]":"","contact_name[]":"","contact_mobile_phone[]":"","contact_company[]":""},{"relationship[]":"","contact_name[]":"","contact_mobile_phone[]":"","contact_company[]":""}]};
 // console.log(call.isEdit(a,b))
  $('#cusotmer_info_commit').click(function(){
	  call.submit({
		  dom:$(this),
		  url:'/customer_add',
		  action:'customer_info',
		  initdata: initformdata['customer_info'],
		  tab_index : '2',
		  yes:function(result,field){
					$('#sales_account_manager').append("<input type='hidden' name='customer_id' id='customer[]_id' value="+ result +">")
			  }
	  })
  });
  //提交用户
  //提交联系人
  $('#customer_contact_commit').click(function(){
	  call.submit({
		  dom:$(this),
		  url:'/customer_add',
		  action:'customer_info_contact',
		  initdata: initformdata['customer_info_contact'],
		  tab_index : '3',
		  yes:function(result){
				 var dom=  $('#customer_contact').find('input[name^="contact_id"]'),
				 result = JSON.parse(result) ;
				 if(result['contact_other_id']){
					 $('#contact_other_id').val(result['contact_other_id'])
				 }
				 layui.each(result['contact_id'],function(key,id){
					 if(id)  dom.eq(key).val(id)
				 })
		  }
	  })
  });
  //提交联系人
  //提交用户公司
  $('#customer_company_commit').click(function(){
	  call.submit({
		    dom:$(this),
		  url:'/customer_add',
		  action:'customer_info_company',
		  initdata: initformdata['customer_info_company'],
		  tab_index : '4',
		  yes:function(result,field){
				  $('#customer_company_commit').append("<input type='hidden' name='customer_company_id' value="+ result +">")
		  }
	  })
  })
  //提交用户公司
  //提交用户负债
  $('#contact_debt_commit').click(function(){
	  call.submit({
		    dom:$(this),
		  url:'/customer_add',
		  action:'customer_info_debt',
		  initdata: initformdata['customer_info_debt'],
		  tab_index : '4',
		  yes:function(result,field){
				  $('#customer_company_commit').append("<input type='hidden' name='customer_company_id' value="+ result +">")
		  }
	  })
  })
   //提交用户负债
  //查重验证
  $("#check_id").click(function(){
	call.submit({
		dom:$(this),
		url:'/customer_add',
		action:'check_id',
		before:function(){
			$("#idcard").val(call.changeFivteenToEighteen($("#idcard").val()));
		},
		yes:function(result){
			var title = '查询结果',content,btn = [],yes=function(index, layero){
					  $(document).off('click','.layui-tab-title li:eq(0)')
					  $(document).on('click','.layui-tab-title li:eq(0)',call.tabClick)
					  layer.close(index);
					  call.calc()
					  call.tabChange('customer','1');
			  }
			switch(result)
			{
			case '0':
				content = '不存在该客户,点击下一步继续';
				btn = ['下一步'];
			  break;
			case '1':
				content = '该客户已经存在！是否继续？点击下一步继续';
				btn = ['下一步','复制账号'];
				var btn2 =function(index, layero){
					 layer.close(index); 
					 console.log('复制成功')
				  }
			  break;
			case '2' :
				btn = ['确定']
				content = '公司其他员工已有此客户'
				yes = function(index, layero){
				 layer.close(index);
				}
			  break;
			default:
				  break;
			}
			layer.open({
				title:title,
				content:content,
				btn:btn,
				yes: yes,
				btn2: btn2
			})
		}
	})  
  })

    //身份证验证
  $("#credit_add").click(function(){
	  var html = '<div id="credit" class="row" ><div class="col-md-3"><div class="layui-form-item" ><label class="layui-form-label">信用卡</label><div class="layui-input-block"><input type="text" name="creditcard_name[]" autocomplete="off" class="layui-input" placeholder="请输入发卡行" ></div></div></div><div class="col-md-3"><div class="layui-form-item" ><label class="layui-form-label">授信额度</label><div class="layui-input-block"><input type="text" name="creditcard_limit[]" autocomplete="off" class="layui-input" placeholder="请输入金额" ></div></div></div><div class="col-md-3"><div class="layui-form-item" ><label class="layui-form-label">已使用额度</label><div class="layui-input-block"><input type="text" name="creditcard_used[]" autocomplete="off" class="layui-input" placeholder="请输入已经用金额" ></div></div></div><div class="col-md-3"><div class="layui-form-item" ><button type="button"  onclick="remove_input(this)" class="layui-btn">删除</button></div></div></div>';
	 $(this).parent().parent().parent().after(html)
  });
  $("#lingyong_add").click(function(){
	  var html = '<div class="row" id="lingyong"><div class="col-md-3"><div class="layui-form-item" ><label class="layui-form-label">零用贷</label><div class="layui-input-block"><input type="text" name="lingyong_name[]" autocomplete="off" class="layui-input" placeholder="请输入零用贷名称" ></div></div></div><div class="col-md-3"><div class="layui-form-item" ><label class="layui-form-label">金额</label><div class="layui-input-block"><input type="text" name="lingyong_amount[]" autocomplete="off" class="layui-input" placeholder="请输入零用贷额度" ></div></div></div><div class="col-md-3"><div class="layui-form-item" ><button type="button" onClick="remove_input(this)" class="layui-btn">删除</button></div></div></div>';
	  $(this).parent().parent().parent().after(html)
  });
  $("#other_add").click(function(){
	  var html = '<div class="col-md-3"><div class="layui-form-item" >   		<label class="layui-form-label">其他贷款</label>   		<div class="layui-input-block">   		<input type="text" name="other_name[]" autocomplete="off" class="layui-input" placeholder="请输入其他贷款名称" lay-verify="required"></div></div></div><div class="col-md-3"><div class="layui-form-item" >   		<label class="layui-form-label">金额</label>   		<div class="layui-input-block">   		<input type="text" name="other_amount[]" autocomplete="off" class="layui-input" placeholder="请输入其他金额" lay-verify="required"></div></div></div><div class="col-md-3"><div class="layui-form-item" >   		<button type="button" onClick="remove_input(this)" class="layui-btn">增加</button></div></div></div>';
	  $(this).parent().parent().parent().after(html)
  });

});

