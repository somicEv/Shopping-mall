package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

/**
 * 表现层--内容管理
 * @author 浩瀚
 *
 */
@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 查询内容列表
	 * @param page
	 * @param rows
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(int page, int rows,Long categoryId){
		EasyUIDataGridResult queryResult = contentService.getContentList(page, rows, categoryId);
		return queryResult;
	}
	
	/**
	 * 内容添加
	 * @param content
	 * @return
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content){
		TaotaoResult addResult = contentService.addContent(content);
		return addResult;
	}
}
