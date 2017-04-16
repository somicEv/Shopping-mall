package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * 用于EasyUI树形节点
 * 返回json数据
 * @author 浩瀚
 *
 */
public class EasyUITreeNode implements Serializable{
	// id
	private long id;
	// 信息
	private String text;
	// 状态
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
