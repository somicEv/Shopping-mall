package com.taotao.commom.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 页面回显数据实体类
 * @author 浩瀚
 *
 */
public class EasyUIDataGridResult implements Serializable{
	
	private Long total;
	
	private List rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(long l) {
		this.total = l;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows; 
	}
	
	
}
