package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		
		TbItemCatExample itemCatExample = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = itemCatExample.createCriteria();
		// 设置parentId
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(itemCatExample);
		// 封装参数
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for(TbItemCat tbItemCat : list){
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			// 添加节点
			resultList.add(node);
		}
		
		return resultList;
	}

}
