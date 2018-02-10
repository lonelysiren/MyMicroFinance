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
			<link rel="stylesheet" href="./lib/css/main.css" />
	</head>
	<body>
		<div class="admin-main">
<form class="layui-form layui-form-pane" action="">
<div data-id="customer_contract_id"></div>
<div class="layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">产品</label>
 	<div class="layui-input-block">
   		<select id="products" name="product_id" lay-filter="products">
  <option value="">请选择产品</option>
</select>     
      </div>
 </div>
</div>
</div>
<div class="layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">放款金额</label>
 	<div class="layui-input-block">
   		<input type="text" name="amount" autocomplete="off" lay-verify="required" class="layui-input"  >
      </div>
 </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">周期</label>
 	<div class="layui-input-block">
   		<input type="text" name="circle" autocomplete="off" lay-verify="required" class="layui-input" >
      </div>
 </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">下个还款日</label>
 	<div class="layui-input-block">
   		<input type="text" id="next_payday" name="next_payday" autocomplete="off" lay-verify="required" class="layui-input"   >
      </div>
 </div>
</div>
</div>
<div class="layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">保证金</label>
 	<div class="layui-input-block">
   		<input type="text" name="margin_loans" autocomplete="off" lay-verify="required" class="layui-input"  >
      </div>
 </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">管理费</label>
 	<div class="layui-input-block">
   		<input type="text" name="management_pay" autocomplete="off" lay-verify="required" class="layui-input" >
      </div>
 </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">其他费用</label>
 	<div class="layui-input-block">
   		<input type="text" name="other_pay" autocomplete="off" lay-verify="required" class="layui-input"   >
      </div>
 </div>
</div>
</div>
<div class="layui-row">
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">审核日期</label>
 	<div class="layui-input-block">
   		<input type="text" id="check_date"name="check_date" autocomplete="off" lay-verify="required" class="layui-input"  >
      </div>
 </div>
</div>
<div class="layui-col-md3">
 <div class="layui-form-item" >
 <label class="layui-form-label">期还款额</label>
 	<div class="layui-input-block">
   		<input type="text" disabled id="circle_pay" autocomplete="off"  class="layui-input layui-bg-gray"  >
      </div>
 </div>
</div>
</div>
<div class="layui-row">
<div class="layui-col-md9">
 <div class="layui-form-item" >
  <label class="layui-form-label" >备注：</label>
  <div class="layui-input-block" >
  <textarea name="remark"  class="layui-textarea"></textarea>
  </div>
   </div>
</div>
</div>
<div class="layui-row ">
<div class="layui-col-md6 layui-col-md-offset3">
<button  lay-filter="edit" lay-submit  id="3" class="layui-btn">预审通过</button>
<button   lay-filter="edit" lay-submit id="4" class="layui-btn">终审通过</button>
<button  lay-filter="edit" lay-submit id="1" class="layui-btn layui-btn-danger">拒绝通过</button>
<button  lay-filter="edit" lay-submit id="2" class="layui-btn  layui-btn-normal">咨询</button>
<button type="button" id="close" class="layui-btn layui-btn-primary">关闭</button>
</div> 
</div>

</form>
			</div>
			<script type="text/javascript" src="./lib/js/jquery-3.2.1.min.js"></script>
			<script type="text/javascript" src="./lib/js/list.js"></script>
		<script type="text/javascript">
		var form = layui.form,layer=layui.layer,laydate = layui.laydate
		var products = ${products}
		var customer_id = ${customer_id}
		var detail_verify = ${detail_verify}
		for (i = 0; i < products.length; i++) {
			$("#products").append(
					"<option value=" + products[i].product_id + ">"
							+ products[i].product_name + "</option>");
		}
		loadData(detail_verify)
		laydate.render({
			 elem: '#next_payday'
		})
		laydate.render({
			 elem: '#check_date'
			 ,type: 'datetime'
		})
		form.render()
			form.on('select(products)', function(data) {
					$("#circle_pay").val(1000);
			});
		$("#close").on('click',function(e){
				layer.closeAll('page');
		})
		form.on('submit(edit)',function(data){
			var load = layer.load(2, {time: 2*1000});
			 var field = data.field
			 field['check_stauts'] = data.elem.id
			 field['customer_id'] = customer_id
			   $.ajax({
					type:'post',
					url:'/CustomerVerifyAction',
					data:{'detail':JSON.stringify(field)},
					success: function(result){
					if(result !="faild")
						layer.msg("修改成功");
						layer.closeAll('page');
				   }
				   	}) 
			  layer.close(load);
			return false
		})
		</script>
	</body>

</html>