package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	 
	/**
	 * 返回内容类型列表
	 * @param parentId
	 * @return
	 */
	 List<EasyUITreeNode> getContentCategoryList(long parentId);
	
	 /**
	  * 添加新的内容分类节点
	  * @param parentId
	  * @param name
	  * @return
	  */
	 TaotaoResult addContentCategory(Long parentId,String name);
	 
	 /**
	  * 更新内容分类节点的名称
	  * @param id
	  * @param name
	  */
	 void updateContentgory(Long id,String name);
	 
	 /**
	  * 删除内容分类列表
	  * @param id
	  */
	 boolean deleteContentgory(Long id);
	
}
