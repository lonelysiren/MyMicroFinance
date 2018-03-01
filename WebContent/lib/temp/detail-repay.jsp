<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<blockquote class="layui-elem-field">
			<div class="layui-field-box layui-form">
			<table id="repay_info" lay-filter="repay_info"
					class="layui-table admin-table">
				</table>
				</div>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>还款明细</legend>
				<div class="layui-field-box layui-form">
				<table id="repay_detail" lay-filter="repay_detail"
					class="layui-table admin-table">
				</table>
			</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="paged" class="page" >
				</div>
			</div>
		</div>
		 <script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-sm" lay-event="edit">结清</a>
  <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">移至催收表</a>
  <a class="layui-btn layui-btn-sm" lay-event="edit">打印</a>
  <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">重置</a>
</script>
<script type="text/html" id=repay>
<a style="cursor:pointer;color:#0000CD" lay-event="repay">{{=d.date }}</a>
</script>
<script type="text/html" id="stauts">
  {{#  if(d.stauts == 0){ }}
禁用
  {{#  } else { }}
启用
  {{#  } }}
</script>
		<script type="text/javascript"src="./lib/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript"src="./lib/js/jquery.cookie.js"></script>
		<script type="text/javascript">
			  var table = layui.table;
			  table.render({
				  elem:'#repay_info',
				  cols:[[
					  {field:'customer_name',title:"客户姓名",width:90},
					  {field:'idcard_number',title:'身份证'},
					  {field:'amount',title:'放款金额',width:90},
					  {field:'circle',title:'周期',width:90},
					  {field:'realfee',title:'已还金额',width:90},
					  {field:'otherfee',title:'其他费用',width:90},
					  {title:'操作',toolbar: '#barDemo'}
				 		 ]],
				  data:[${repay}],
				  done: function(res, curr, count){
					    console.log(res);
					    console.log(curr); 
					    console.log(count);
					  }
			  })
			  table.render({
				  elem:'#repay_detail',
				  cols:[[ //标题栏
					    {field: 'num', title: '期数',width:90},
					    {field: 'date', title: '应还日期',toolbar:'#repay',width:120},
					    {field: 'stauts', title: '状态',width:90},
					    {field: 'bj',  title: '当期本金',width:100},
					    {field: 'fee',  title: '当期服务费',width:90},
					    {field: 'lx', title: '当期利息',width:90},
					    {field: 'allfee',title:'当期应还总额',width:120},
					    {field: 'repay_date',title:'实际还款日期',width:120},
					    {field: 'yfzn',title:'应付滞纳',width:90},
					    {field: 'sfzn',title:'实付滞纳',width:90},
					    {field: 'wfzn',title:'未付滞纳',width:90},
					    {field: 'ysbj',title:'已收本金',width:90},
					  ]],
					  size:"sm", 
					  done: function(res, curr, count){
						    console.log(res);
						    console.log(curr); 
						    console.log(count);
						  },
					  data:${data}
					  
			  })
			  table.on('tool(repay_detail)',function(obj){
				  var data = obj.data; //获得当前行数据
				  var layEvent = obj.event; //获得 lay-event 对应的值
				  var tr = obj.tr; //获得当前行 tr 的DOM对象
				  if(layEvent =='repay') {
					  var load = layer.load(2, {time: 2*1000});
				  $.get('./lib/temp/duizhang.jsp', {data:${data}}, function(form) {
			   			var addBoxIndex = layer.open({
			   				type: 1,
			   				title: '对账',
			   				content: form,
			   				shade: false,
			   				offset: ['0%', '0%'],
			   				area: ['100%', '100%'],
			   				zIndex: 500,
			   				maxmin: true,
			   				 btnAlign: 'c',
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
			   					layer.close(load);
			   					layer.full(index);
			   					var btn = layero.find('.layui-layer-btn');
			   						return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
			   				},
			   				end: function() {
			   					addBoxIndex = -1;
			   				}
			   			});
			   		});
				  }
			  })
		</script>
	</body>
</html>