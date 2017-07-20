package com.mf.utils;

import java.util.List;
import java.util.Map;

public class PageUtil {
	private List<Map<String, Object>>  list; //对象记录结果集
    private int total = 0; // 总记录数
    private int pageSize = 0; // 每页显示记录数
    private int count = 0; // 总页数
    private int pageIndex = 1; // 当前页

     
    public PageUtil(List<Map<String, Object>> list, int pageIndex, int pageSize) {
        init( list, pageIndex, pageSize);
    }
     
    private void init(List<Map<String, Object>> list, int pageIndex, int pageSize){
        //设置基本参数
        this.list=list;
        this.total=list.size();
        this.pageSize=pageSize;
        this.count=(this.total-1)/this.pageSize+1;
         
        //根据输入可能错误的当前号码进行自动纠正
        if(pageIndex<1){
            this.pageIndex=1;
        }else if(pageIndex>this.count){
            this.pageIndex=this.count;
        }else{
            this.pageIndex=pageIndex;
        }
        
    }

	public int getConut() {
		return count;
	}
	  public List<Map<String, Object>> getList() {
		  
		
	        return list;
	    }
	 
}
