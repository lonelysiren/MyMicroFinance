<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
			<link rel="stylesheet" href="./lib/plugins/layui2.0/css/layui.css" media="all" />
			<link rel="stylesheet" href="./lib/plugins/city/css/city.css" media="all" />
			<link rel="stylesheet" href="./lib/css/main.css" />
	</head>
	<body>
		<div class="admin-main">
<form class="layui-form layui-form-pane" action="">
<fieldset class="layui-elem-field">
  <legend>客戶信息详情</legend>
  <div class="layui-field-box">
  <div class="layui-inline">
  <label class="layui-form-label" >客户经理:</label>
  <div class="layui-input-inline">
  <select id="sales_account_manager" name="sales_account_manager" >
	</select> 
  </div>
  </div>
  </div>
</fieldset>
<fieldset id="info" class="layui-elem-field">
  <legend>个人信息</legend>
  <div class="layui-field-box">
<div class="layui-layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <div data-id="customer_id" ></div>
   		<label class="layui-form-label">客户姓名</label>
   		<div class="layui-input-block">
   		<input type="text" name="customer_name" autocomplete="off" lay-verify="required" class="layui-input" placeholder="请输入客户姓名(必填)" value="${info.customer_name }">
      </div>
</div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" pane="" >
   		<label class="layui-form-label">性别</label>
   		<div class="layui-input-block">
   		<input type="radio" name="sex"  title="男" checked>
		<input type="radio" name="sex" value="1" title="女" >
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
   		<label class="layui-form-label">年龄</label>
   		<div class="layui-input-block">
   		<input type="text" id='age' name="age" lay-verify="required|number" autocomplete="off" class="layui-input" placeholder="请输入年龄(必填)">
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item">
   		<label class="layui-form-label">婚姻状况</label>
   		<div class="layui-input-block">
   		<select id="marriage_status" lay-verify="required"  name="marriage_status" >
   		 <option value="">请选择婚姻状况(必选)</option>
		</select>     
      </div>
      </div>
</div>
</div>
<div class="layui-layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
   		<label class="layui-form-label">证件类型</label>
   		<div class="layui-input-block">
   		<select name="idcard_type" lay-verify="required" id="idcard_type">
  	<option value="">请选择证件类型(必填)</option>
</select>    
      </div>
      </div>
</div>
<div class="layui-col-md6">
 <div class="layui-form-item" >
   		<label class="layui-form-label">证件号码</label>
   		<div class="layui-input-block">
   		<input type="text" lay-verify="required|identity" id ="idcard_number" name="idcard_number" autocomplete="off" class="layui-input"  disabled>
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item">
   		<label class="layui-form-label">子女年龄</label>
   		<div class="layui-input-block">
   		<input type="text" name="child_age"   autocomplete="off" class="layui-input" placeholder="请输入子女年龄">
      </div>
      </div>
</div>
</div>
<div class="layui-layui-row">
<div class="layui-col-md4" style="padding-right: 5px">
 <div class="layui-form-item"  >
    <label class="layui-form-label">户籍地址</label>
    <div class="layui-input-block"  >
      <input type="text" id="census_register" name="census_register" lay-verify="required"  autocomplete="off" class="layui-input" placeholder="请选择户籍所在地(必选)">
  </div>
</div>
</div>
<div class="layui-col-md5" style="padding-left: 0px">
      <input type="text" id="census_register_detail"name="census_register_detail" lay-verify="required" autocomplete="off" class="layui-input" placeholder="填写详细地址(必填)">
</div>
<div class="layui-col-md3">
 <div class="layui-form-item">
   		<label class="layui-form-label">水电户号</label>
   		<div class="layui-input-block">
   		<input type="text" name="house_number" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入水电户号(必填)">
      </div>
      </div>
</div>
</div>
<div class="layui-layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
   		<label class="layui-form-label">住宅状况</label>
   		<div class="layui-input-block">
   		<select name="house_status" id="house_status" lay-filter="house_status" lay-verify="required">
 <option value="">请选择住宅状况(必选)</option>
</select>     
      </div>
      </div>
</div>

<div class="layui-col-md6">
 <div class="layui-form-item" >
   		<label class="layui-form-label">月支付额</label>
   		<div class="layui-input-block">
   		<input type="text" name="house_rent" autocomplete="off" class="layui-input" placeholder="请输入月支付额">
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item">
   		<label class="layui-form-label">户主名字</label>
   		<div class="layui-input-block">
   		<input type="text" name="house_holder" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入户主名字(必填)">
      </div>
      </div>
</div>
</div>
<div class="layui-layui-row">
<div class="layui-col-md9">
 <div class="layui-form-item" >
   		<label class="layui-form-label">现住地址</label>
   		<div class="layui-input-block">
   		<input type="text" lay-verify="required" id="current_residence" name="current_residence" autocomplete="off" class="layui-input" placeholder="请输入现住地址(必填)">
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" pane="">
   		<label class="layui-form-label">是否本地</label>
   		<div class="layui-input-block ">
   		<input type="radio" name="native_type"  title="是" checked>
   		<input type="radio"  name="native_type" value="1" title="否" >
      </div>
      </div>
</div>

</div>
<div class="layui-layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
   		<label class="layui-form-label">现居地固话</label>
   		<div class="layui-input-block">
   		<input type="text" name="current_residence_phone" autocomplete="off" class="layui-input" placeholder="请输入现居地固话">
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
   		<label class="layui-form-label">手机号码</label>
   		<div class="layui-input-block">
   		<input type="text" lay-verify="required|phone" name="mobile_phone" autocomplete="off" class="layui-input" placeholder="请输入手机号码(必填)">
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
   		<label class="layui-form-label">已使用年限</label>
   		<div class="layui-input-block">
   		<input type="number" name="mobile_phone_age" autocomplete="off" class="layui-input" placeholder="请输入手机号码使用年限">
      </div>
      </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" pane="">
   		<label class="layui-form-label">是否实名制</label>
   		<div class="layui-input-block">
   		<input type="radio" name="mobile_phone_real_name"  title="是" checked>
   		<input type="radio" name="mobile_phone_real_name" value="1" title="否" >
      </div>
      </div>
</div>
</div>
<div class="layui-layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
   		<label class="layui-form-label">教育程度</label>
   		<div class="layui-input-block">
   		<select name="education"  id="education" >
   		 <option value="">请选择教育程度(必选)</option>
		</select>     
      </div>
      </div>
</div>
<div class="layui-col-md6">
 <div class="layui-form-item" >
   		<label class="layui-form-label">毕业学校</label>
   		<div class="layui-input-block">
   		<input type="text" name="graduate_school" autocomplete="off" class="layui-input" placeholder="请输入毕业学校">
      </div>
      </div>
</div>
</div>
<div class="layui-layui-row">
<div class="layui-col-md6">
 <div class="layui-form-item" >
   		<label class="layui-form-label">收款账号</label>
   		<div class="layui-input-block">
   		<input type="text" lay-verify="required" name="account_number" autocomplete="off" class="layui-input" placeholder="请输入收款账号(必填)">
      </div>
      </div>
</div>
<div class="layui-col-md6">
 <div class="layui-form-item" >
   		<label class="layui-form-label" style="width:130px">开户银行和地址</label>
   		<div class="layui-input-block" style="    margin-left: 130px;">
   		<input type="text" lay-verify="required" name="deposit_bank" autocomplete="off" class="layui-input" placeholder="请输入开户银行和地址(必填)">
      </div>
      </div>
</div>
</div>
<div class="layui-layui-row">
  <div class="layui-col-md12">
  <div class="layui-form-item" >
  <label class="layui-form-label" style="width:130px">客户信息备注：</label>
  <div class="layui-input-block" style="    margin-left: 130px;">
  <textarea name="remark"  placeholder="请输入" class="layui-textarea"></textarea>
  </div>
   </div>
  </div>
  </div>
	</div>  
</fieldset>
<fieldset class="layui-elem-field">
  <legend>公司信息</legend>
  <div class="layui-field-box">
  <div class="layui-row">
    <div class="layui-col-md3"><div class="layui-form-item" >
    <div data-id="customer_company_id" ></div>
   		<label class="layui-form-label">公司名称：</label>
   		<div class="layui-input-block">
   		<input type="text" name="company_name" autocomplete="off" class="layui-input" placeholder="请输入公司名称(必填)" lay-verify="required">
      </div>
      </div></div>
    <div class="layui-col-md3"><div class="layui-form-item" >
   		<label class="layui-form-label">部门名称：</label>
   		<div class="layui-input-block">
   		<input type="text" name="department" autocomplete="off" class="layui-input" placeholder="请输入部门名称 " >
      </div>
      </div></div>
    <div class="layui-col-md3"><div class="layui-form-item" >
   		<label class="layui-form-label">职位：</label>
   		<div class="layui-input-block">
   		<input type="text" name="position" autocomplete="off" class="layui-input" placeholder="请输入职位" >
      </div>
      </div></div>
    </div>
       <div class="layui-row">
    <div class="layui-col-md6">
    <div class="layui-form-item" >
   		<label class="layui-form-label">公司地址：</label>
   		<div class="layui-input-block">
   		<input type="text" name="compnay_address" autocomplete="off" class="layui-input" placeholder="请输入公司地址" >
      </div>
      </div></div>
    <div class="layui-col-md3">
    <div class="layui-form-item" >
   		<label class="layui-form-label">邮编：</label>
   		<div class="layui-input-block">
   		<input type="text" name="postal_code" autocomplete="off" class="layui-input" placeholder="请输入邮编" >
      </div>
      </div></div>
    <div class="layui-col-md3"></div>
    </div>
    <div class="layui-row">
    <div class="layui-col-md3">
    <div class="layui-form-item" >
   		<label class="layui-form-label">公司电话：</label>
   		<div class="layui-input-block">
   		<input type="text" name="phone_number" autocomplete="off" class="layui-input" placeholder="请输入公司电话" >
      </div>
      </div></div>
    <div class="layui-col-md3"><div class="layui-form-item" >
   		<label class="layui-form-label">分机：</label>
   		<div class="layui-input-block">
   		<input type="text" name="phone_number_extension" autocomplete="off" class="layui-input" placeholder="请输入分机号码姓名" >
      </div>
      </div></div>
    <div class="layui-col-md3"><div class="layui-form-item" >
   		<label class="layui-form-label">人事电话：</label>
   		<div class="layui-input-block">
   		<input type="text" name="phone_number_hr" autocomplete="off" class="layui-input" placeholder="请输入人事电话" >
      </div>
      </div></div>
    </div>
    <div class="layui-row">
    <div class="layui-col-md5"><div class="layui-form-item" >
   		<label class="layui-form-label">工作时间：</label>
   		<div class="layui-input-inline">
   		<input type="text" name="work_year" autocomplete="off" class="layui-input" placeholder="请输入工作时间" >
      </div>
        <div class="layui-form-mid layui-word-aux">年</div>
      </div></div>
    <div class="layui-col-md5"><div class="layui-form-item" >
   		<label class="layui-form-label">月工资：</label>
   		<div class="layui-input-inline">
   		<input type="text" name="salary" autocomplete="off" class="layui-input" placeholder="请输入月工资" >
      </div>
         <div class="layui-form-mid layui-word-aux">元</div>
      </div></div>
    <div class="layui-col-md2"></div>
    </div>
    <div class="layui-row">
    <div class="layui-col-md5"><div class="layui-form-item" >
   		<label class="layui-form-label">公积金名：</label>
   		<div class="layui-input-block">
   		<input type="text" name="accumulation_fund_account" autocomplete="off" class="layui-input" placeholder="请输入公积金网员工名" >
      </div></div></div>
    <div class="layui-col-md5"><div class="layui-form-item" >
   		<label class="layui-form-label">密码：</label>
   		<div class="layui-input-inline">
   		<input type="text" name="accumulation_fund_password" autocomplete="off" class="layui-input" placeholder="请输入密码" >
      </div></div>
    <div class="layui-col-md2"></div>
    </div>
    </div>
    <div class="layui-row">
    <div class="layui-col-md5">
    <div class="layui-form-item" >
   		<label class="layui-form-label">公司性质：</label>
   		<div class="layui-input-block">
   		<select id="company_type"name="company_type" lay-verify="required">
  <option value="">请选择公司性质</option>
</select>    
      </div></div>
    </div>
    <div class="layui-col-md5"></div>
    <div class="layui-col-md2"></div>
    </div>
  </div>
</fieldset>
<fieldset class="layui-elem-field">
  <legend>联系人信息</legend>
  <div class="layui-field-box">
   <div class="layui-row" id="other_contact">
    <div class="layui-col-md3">
     <div class="layui-form-item" >
     <div data-id="contac_other_id" ></div>
   		<label class="layui-form-label" style="width:100%">其他联系人</label>
   		</div>
    </div> 
   <div class="layui-col-md6">
   <div class="layui-form-item" >
  <textarea name="contact_content"   placeholder="请输入" class="layui-textarea"></textarea>
  </div>
   </div>
   </div>
  </div>
</fieldset>
<fieldset class="layui-elem-field">
  <legend>负债信息</legend>
  <div class="layui-field-box">
   <div class="layui-row" name="debt_creditcard" id="creditcard">
   <div class="layui-col-md3"> <div class="layui-form-item" >
  <div data-id="creditcard_id"></div>
   		<label class="layui-form-label">信用卡</label>
   		<div class="layui-input-block">
   		<input type="text" name="creditcard_name[]" autocomplete="off" class="layui-input" placeholder="请输入发卡行" >
      </div>
      </div></div>
   <div class="layui-col-md3"> <div class="layui-form-item" >
   		<label class="layui-form-label">授信额度</label>
   		<div class="layui-input-block">
   		<input type="text" name="creditcard_limit[]" autocomplete="off" class="layui-input" placeholder="请输入金额" >
      </div>
      </div></div>
   <div class="layui-col-md3"> <div class="layui-form-item" >
   		<label class="layui-form-label">已使用额度</label>
   		<div class="layui-input-block">
   		<input type="text" name="creditcard_used[]" autocomplete="off" class="layui-input" placeholder="请输入已经用金额" >
      </div>
      </div></div>
   	<div class="layui-col-md3"> 
   	<div class="layui-form-item" >
   		<button type="button" id = "credit_add" class="layui-btn">增加</button>
      </div>
      </div>
   </div>
   <div class="layui-row" name="debt_lingyong" id="lingyong" >
   <div class="layui-col-md3"> <div class="layui-form-item" >
    <div data-id="lingyong_id"></div>
   		<label class="layui-form-label">零用贷</label>
   		<div class="layui-input-block">
   		<input type="text" name="lingyong_name[]" autocomplete="off" class="layui-input" placeholder="请输入零用贷名称" >
      </div>
      </div></div>
   <div class="layui-col-md3"> <div class="layui-form-item" >
   		<label class="layui-form-label">金额</label>
   		<div class="layui-input-block">
   		<input type="text" name="lingyong_amount[]" autocomplete="off" class="layui-input" placeholder="请输入零用贷额度" >
      </div>
      </div></div>
   <div class="layui-col-md3"> <div class="layui-form-item" >
   		<button type="button" id = "lingyong_add" class="layui-btn">增加</button>
      </div></div>
   </div>
   <div class="layui-row" name="debt_other" id="other">
   <div class="layui-col-md3"> <div class="layui-form-item" >
   <div data-id="other_id"></div>
   		<label class="layui-form-label">其他贷款</label>
   		<div class="layui-input-block">
   		<input type="text" name="other_name[]" autocomplete="off" class="layui-input" placeholder="请输入其他贷款名称" >
      </div>
      </div></div>
   <div class="layui-col-md3"> <div class="layui-form-item" >
   		<label class="layui-form-label">金额</label>
   		<div class="layui-input-block">
   		<input type="text" name="other_amount[]" autocomplete="off" class="layui-input" placeholder="请输入其他金额" >
      </div>
      </div></div>
   <div class="layui-col-md3"> <div class="layui-form-item" >
   		<button type="button" id="other_add" class="layui-btn">增加</button>
      </div></div>
      <div id = "final">
      <button lay-filter="edit" lay-submit style="display: none;"></button>
      </div>
   </div>
  </div>
</fieldset>
</form>
			</div>
			<script type="text/javascript" src="./lib/js/jquery-3.2.1.min.js"></script>
			<script type="text/javascript" src="./lib/datas/option.js"></script>
			<script type="text/javascript"src="./lib/js/select.js"></script>
			<script type="text/javascript" src="./lib/js/customer_list.js"></script>
		<script src="./lib/plugins/city/Popt.js"></script>
		<script src="./lib/plugins/city/citySet.js"></script>
		<script src="./lib/plugins/city/cityJson.js"></script>
		<script type="text/javascript">
		var form = layui.form,layer=layui.layer
		var info = ${info}
		var company = ${company}
		var contact = ${contacts}
		var contact_other = ${contact_other}
		var debt_creditcard =${debt_creditcard}
		var debt_lingyong = ${debt_lingyong}
		var debt_other = ${debt_other}
		var details = {"info":info,"company":company,"contact":contact,"contact_other":contact_other,"debt_creditcard":debt_creditcard,"debt_lingyong":debt_lingyong,"debt_other":debt_other}
		for (var i = 1; i < debt_creditcard.length; i++) {
				 var html = $("#creditcard").clone()
				 $(html).children().last().remove()
				 $('#lingyong').before(html)
			}
		for (var i = 1; i < debt_lingyong.length; i++) {
			var html =  $("#lingyong").clone()
			 $(html).children().last().remove()
			  $('#other').before(html)
		}
		for (var i = 1; i < debt_other.length; i++) {
			var html =  $("#other").clone()
			$(html).children().last().remove()
			  $('#final').before(html)
		}
		layui.each(details,function(index,value){
			loadData(value);
		})
		form.render()
			form.on('select(house_status)', function(data) {
				if (data.value == '1') {
					$("#current_residence").val(
							$("#census_register").val() + "-"
									+ $("#census_register_detail").val());
				}
			});
		form.on('submit(edit)',function(data){
			var ndata = {}, field = data.field,obj = data.form
			layui.each(details,function(key,value){
				var temp = {},id = {}
				if(value.constructor === Array){
					var adata = getData(obj,key)
					layui.each(value,function(index,value){
						temp = check(value,adata[index])
						if($.isEmptyObject(temp)) delete adata[index]
					})
					if($.isEmptyObject(adata)) return
					var bdata = [];//去除undefined后的结果
					for(var i=0;i<adata.length;i++){
					    if(typeof(adata[i])!='undefined'){
					    	bdata.push(adata[i]);
					    }
					}
					ndata[key] = bdata
					return
				}
				layui.each(value,function(name,value){
					if(name.indexOf("_id" ) > 0){
						id[name] = value
						return
					}
					if(value != data.field[name]){
						temp[name] = data.field[name]
					}
				})
				if($.isEmptyObject(temp)) return
				temp = $.extend({},temp,id)
				ndata[key] = temp
			})
			console.log(ndata);
			/*  $.ajax({
						type:'post',
  						url:'/customer_edit',
						data:{'action':'customer','customer_id':info[customer_id],'data':JSON.stringify(nata)},
						success: function(result){
					   }
					   	})  */
			return false
		})
			$("[name='census_register']").click(function(e) {
				SelCity(this, e, layer);
			});
		</script>
	</body>

</html>