package com.taotao.service;

import java.util.List;

import com.taotao.commom.pojo.EasyUITreeNode;

public interface ItemCatService {
	
	/**
	 * 查询商品列表
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNode> getItemCatList(long parentId);
	
	
}
