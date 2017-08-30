layui.define(['table','form'], function (exports) {
    "use strict";
    var table = layui.table,
    form = layui.form,
    $ = layui.$,
    layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
    layer = layui.layer,
    tableIns = {};

    var Paging = function () {
        this.config = {
        		even:true,
            url: undefined, //数据远程地址
            where: {}, //获取数据时传递的额外参数
            page: true,//是否显示分页组件
            elem: undefined, //内容容器
            cols:undefined,
            filter:undefined,
            del:undefined,
            edit:undefined,
            success: undefined, //type:function
            fail: function (res) {
                console.log(res.msg);
                //layer.msg(res.msg, { icon: 2 });
            }, //type:function
            complate: undefined, //type:function
            serverError: function (xhr, status, error) { //ajax的服务错误
                throwError("错误提示： " + xhr.status + " " + xhr.statusText);
            }
        };
    };
	/**
	 * 版本号
	 */
    Paging.prototype.v = '1.0.0';

	/**
	 * 设置
	 * @param {Object} options
	 */
    Paging.prototype.set = function (options) {
        var that = this;
        $.extend(true, that.config, options);
        return that;
    };
	/**
	 * 初始化
	 * @param {Object} options
	 */
    Paging.prototype.init = function (options) {
        var that = this;
        $.extend(true, that.config, options);
        var _config = that.config;
        if (_config.url === undefined) {
            throwError('Paging Error:请配置远程URL!');
        }
        if (_config.elem === undefined) {
            throwError('Paging Error:请配置参数elem!');
        }
        if ($(_config.elem).length === 0) {
            throwError('Paging Error:找不到配置的容器elem!');
        }
        that.get();
        return that;
    };
    
    Paging.prototype.reload = function(options) {
    	tableIns.reload();
    }
    
	/**
	 * 获取数据
	 * @param {Object} options
	 */
    Paging.prototype.get = function (options) {
        var that = this;
        var _config = that.config;
        //默认参数
         tableIns = table.render({
        	url:_config.url,
        	where:_config.where,
        	elem:_config.elem,
        	page: _config.page,
        	even: _config.even,
        	cols:_config.cols
        })
        table.on('tool('+ _config.filter+')', function(obj){
        	 var odata = obj.data; //获得当前行数据
       	  var layEvent = obj.event; //获得 lay-event 对应的值
       	  var tr = obj.tr; //获得当前行 tr 的DOM对象
       	 if(layEvent === 'del'){ //删除
       	    layer.confirm('确定删除： '+odata[_config.del.name]+' 吗？该操作不可逆', function(index){
       	     $.ajax({
       	    	 type:'post',
       	    	 url:_config.del.url,
       	    	 data:{'delete_id':odata[_config.del.id]},
       	    	 success:function(result){
            	    	obj.del(); //删除对应行（tr）的DOM结构
            	      layer.close(index);
       	    	 },
       	    	error: function (xhr, status, error) {
                    _config.serverError(xhr, status, error); //服务器错误
                }
       	     })
       	    });
       	  } else if(layEvent === 'edit'){ //编辑
       		$.get(_config.edit.page, null, function(form) {
       			addBoxIndex = layer.open({
       				type: 1,
       				title: '编辑',
       				content: form,
       				btn: ['保存', '取消'],
       				shade: false,
       				offset: ['15%', '0%'],
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
       					var form = layui.form
       						$("form input").each(function(index,element){
       							if(element.name =="product_isAdvance" ){
       								  $(element).attr("checked",odata[element.name]);
       									console.log(odata[element.name])
       								  return
       							}
       							$(element).val(odata[element.name]);
       						})
       						  $("select[name='product_payment_method']").val(odata['product_payment_method']);
       						$("#product_remark").val(odata['product_remark']);
       						form.render();
       					form.on('submit(edit)', function(data) {
       						var data = data.field,temp={},fields={}
       						layui.each(odata,function(key,value){
       							if(value != data[key]){
       								temp[key] =  data[key]
       								fields[key]=data[key]
       							}
       						})
       						 if($.isEmptyObject(temp)) {layer.close(index); return false;}
       							
       						 temp['product_id'] = data['product_id']
       						$.ajax({
       							type:'post',
       							url:_config.edit.url,
       							data:{'product':JSON.stringify(temp)},
       							success: function (result) {
       								if(result == "success"){
       									parent.layer.msg("修改成功");
       									 obj.update(fields);
       								}else{
       									parent.layer.msg("修改失败");
       								}
       								layer.close(index);
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
        })
    };
	/**
	 * 抛出一个异常错误信息
	 * @param {String} msg
	 */
    function throwError(msg) {
        throw new Error(msg);
    };

    var paging = new Paging();
    exports('paging', function (options) {
        return paging.set(options);
    });
});