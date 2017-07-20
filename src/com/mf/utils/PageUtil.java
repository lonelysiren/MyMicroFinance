package com.mf.utils;

import java.util.List;
import java.util.Map;

public class PageUtil {
	private List<Map<String, Object>>  list; //�����¼�����
    private int total = 0; // �ܼ�¼��
    private int pageSize = 0; // ÿҳ��ʾ��¼��
    private int count = 0; // ��ҳ��
    private int pageIndex = 1; // ��ǰҳ

     
    public PageUtil(List<Map<String, Object>> list, int pageIndex, int pageSize) {
        init( list, pageIndex, pageSize);
    }
     
    private void init(List<Map<String, Object>> list, int pageIndex, int pageSize){
        //���û�������
        this.list=list;
        this.total=list.size();
        this.pageSize=pageSize;
        this.count=(this.total-1)/this.pageSize+1;
         
        //����������ܴ���ĵ�ǰ��������Զ�����
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
