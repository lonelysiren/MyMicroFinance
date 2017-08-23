	layui.config({
		base: './lib/js/'
	});
	layui.use(['paging', 'form','element'], function() {
		var $ = layui.jquery,
			element = layui.element(),
			paging = layui.paging(),
			layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
			layer = layui.layer, //获取当前窗口的layer对象
			form = layui.form();
		$('#add').click(function(){
			var $that = $(this);
			edit_page($that,layer,'0');
		});
        paging.init({
            openWait: true,
            url: url, //地址
			elem: '#content', //内容容器
			params: { //发送到服务端的参数
				company:$.cookie('company')
			},
			type: 'POST',
			tempElem: '#tpl', //模块容器
			pageConfig: { //分页参数配置
				elem: '#paged', //分页容器
				pageSize: 15 //分页大小
			},
			success: function() { //渲染成功的回调
			},
			fail: function(msg) { //获取数据失败的回调
			},
			complate: function() { //完成的回调
				//alert('处理完成');
				//重新渲染复选框
				form.render('checkbox');
				form.on('checkbox(allselector)', function(data) {
					var elem = data.elem;
					$('#content').children('tr').each(function() {
						var $that = $(this);
						//全选或反选
						$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
						form.render('checkbox');
					});
				});
				//绑定所有编辑按钮事件	
				var addBoxIndex = -1;
				$('#content').children('tr').each(function() {
					var $that = $(this);
					$that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
						delete_page($that, layer);
					});
					$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
						if(addBoxIndex !== -1)
							return;
						id=$that.children('td:nth-child(2)').text().trim();
						edit_page($that,layer,id);
						//本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
					});
				});
			},
		});
		//获取所有选择的列
	});
	//打开编辑页面
	function edit_page($that,layer,id){
		$that.find('tr').each(function(){
			
		})
		
		$.get(edit_url, null, function(form) {
			addBoxIndex = layer.open({
				type: 1,
				title: '编辑',
				content: form,
				btn: ['保存', '取消'],
				shade: false,
				offset: ['0px', '0px'],
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
					//弹出窗口成功后渲染表单
					var form = layui.form()
					if($that.is('tr')){
						
					}
					fn(layero, index)
					form.render();
					form.on('submit(edit)', function(data) {
						data.field.id=id;
							//调用父窗口的layer对象
							$.ajax({
								type:'post',
								url:action_url,
								data:data.field,
								success: function (result) {
									if(result == "ok"){
										parent.layer.msg("修改成功");
									}else{
										parent.layer.msg("修改失败");
									}
									layer.close(index);
									location.reload();
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
	//打开删除页面
	function delete_page($that,layer){
		layer.open({
			title:'确认删除',
			content:'你确定要删除:'+ $that.children('td:nth-child(4)').text().trim()+ ' 吗,该操作不可逆',
			btn: ['确认', '取消'],
			yes:function(index){
				$.ajax({
					type:'post',
					url:'user_edit',
					data:'del='+$that.children('td:nth-child(2)').text().trim(),
					 success: function(result){
						 if(result == 'ok'){
							parent.layer.msg('删除成功');
						 } else{
							 parent.layer.msg('删除失败');
						 }
							 layer.close(index);
							location.reload();
					 }
				});
			}
		});
	}
