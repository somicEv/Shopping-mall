package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	
	/**
	 * 获取内容列表
	 * @param categoryId
	 * @return
	 */
	EasyUIDataGridResult getContentList(int page, int rows,Long categoryId);
	
	/**
	 * 添加列表内容
	 * @param content
	 * @return
	 */
	TaotaoResult addContent(TbContent content);
	
}
