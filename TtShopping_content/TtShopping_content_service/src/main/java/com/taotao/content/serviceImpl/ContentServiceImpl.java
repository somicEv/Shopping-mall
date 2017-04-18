package com.taotao.content.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

@Service
@Transactional(readOnly=false)
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public EasyUIDataGridResult getContentList(int page, int rows,Long categoryId) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		//取查询结果
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		for (TbContent tbContent : list) {
			System.out.println(tbContent.toString());
		}
		//返回结果
		return result;
	}
	
	@Override
	public TaotaoResult addContent(TbContent content) {
		// 补全pojo的属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		// 插入数据库
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}

	

}
