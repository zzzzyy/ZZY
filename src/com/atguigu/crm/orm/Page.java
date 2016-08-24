package com.atguigu.crm.orm;

import java.util.List;

public class Page<T> {

	private int pageNo;
	private int pageSize = 3;

	private long totalElements;
	private List<T> content;
	
	//返回下一页
	public int getNextPage(){
		if(isHasNextPage()){
			return this.pageNo + 1;
		}
		
		return this.pageNo;
	}
	
	//校验是否有下一页
	public boolean isHasNextPage(){
		if(this.pageNo < this.getTotalPages()){
			return true;
		}
		
		return false;
	}
	
	//返回上一页
	public int getPrevPage(){
		if(isHasPrevPage()){
			return this.pageNo - 1;
		}
		
		return this.pageNo;
	}
	
	//校验是否有上一页
	public boolean isHasPrevPage(){
		if(this.pageNo > 1){
			return true;
		}
		
		return false;
	}
	
	//返回总的页数
	public int getTotalPages() {
		int totalPages = (int) (this.totalElements / this.pageSize);
		
		if(this.totalElements % this.pageSize != 0){
			totalPages++;
		}
		
		return totalPages;
		//return (int)((this.totalElements + this.pageSize - 1) / this.pageSize);
	}
	
	public int getPageNo() {
		return pageNo;
	}

	//校验 pageNo 的合法性, 校验其是否小于 1
	public void setPageNo(int pageNo) {
		if(pageNo < 1){
			pageNo = 1;
		}
		
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	//设置总的记录数后, 可以校验 pageNo 的合法性. 校验其是否大于总页数
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
		
		if(this.pageNo > this.getTotalPages()){
			this.pageNo = this.getTotalPages();
		}
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
