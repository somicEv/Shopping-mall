package com.taotao.content.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

/**
 * Service--内容分类管理
 * @author 浩瀚
 *
 */
@Service
@Transactional(readOnly = false)
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	// 根据parentId 查询子节点列表
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		// 添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		// 转换结果
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			easyUITreeNode.setId(tbContentCategory.getId());
			easyUITreeNode.setText(tbContentCategory.getName());
			easyUITreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			// 添加到列表
			resultList.add(easyUITreeNode);
		}
		return resultList;
		
	}

	@Override
	public TaotaoResult addContentCategory(Long parentId, String name) {
		// 创建一个pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
	    // 补全对象的属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		// 状态：可选值：1(正常)，2(删除)
		contentCategory.setStatus(1);
		// 排序 默认值1
		contentCategory.setSortOrder(1);
		// 插入的节点为叶子节点
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		// 插入到数据库
		tbContentCategoryMapper.insert(contentCategory);
		// 判断父节点的状态
		TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()){
			// 如果父节点为叶子节点应该改为父节点
			parent.setIsParent(true);
			// 更新父节点
			tbContentCategoryMapper.updateByPrimaryKey(parent);
		}
		// 返回结果
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public void updateContentgory(Long id, String name) {
		// 根据ID查询要更新的节点
		TbContentCategory cateGory = tbContentCategoryMapper.selectByPrimaryKey(id);
		// 修改数据
		cateGory.setName(name);
		// 更新数据库
		tbContentCategoryMapper.updateByPrimaryKey(cateGory);
	}

	@Override
	public void deleteContentgory(Long id) {
		tbContentCategoryMapper.deleteByPrimaryKey(id);
	}

}
