/**
 * 
 */
layui.config({
base: './lib/js/'
});
layui.use(['form','element'], function(){
  var form = layui.form(),
  element = layui.element();
$("select").each(function(index,dom){
	select($(dom).attr("id"));

});
  form.render();

  //监听提交
  form.on('submit(formDemo)', function(data){
	  element.tabChange('customer', '个人信息');
	 
    return false;
  });
});