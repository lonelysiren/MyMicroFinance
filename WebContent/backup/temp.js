//前台客户修改差异处理
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