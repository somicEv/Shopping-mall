package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentService;
	
	/**
	 * 获取分类列表
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getConentCategoryList(
			@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUITreeNode> list = contentService.getContentCategoryList(parentId);
		return list;
	}
	
	/**
	 * 添加内容分类节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/content/category/create")
	@ResponseBody
	public TaotaoResult addContentCategory(Long parentId, String name){
		TaotaoResult result = contentService.addContentCategory(parentId, name);
		return result;
	}
	
	/**
	 * 更新内容分类节点
	 * @param id
	 * @param name
	 */
	@RequestMapping(value="/content/category/update")
	public void updateContentCategory(Long id,String name){
		contentService.updateContentgory(id, name);
	}
	
	/**
	 * 删除内容分类节点
	 * @param id
	 */
	@RequestMapping(value="/content/category/delete/")
	public void deleteContentCategory(Long id){
		contentService.deleteContentgory(id);
	}
	
}
