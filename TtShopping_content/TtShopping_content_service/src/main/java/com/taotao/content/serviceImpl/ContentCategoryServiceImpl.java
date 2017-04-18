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
	private TbContentCategoryMapper categoryMapper;
	
	@Override
	// 根据parentId 查询子节点列表
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		// 添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = categoryMapper.selectByExample(example);
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
		categoryMapper.insert(contentCategory);
		// 判断父节点的状态
		TbContentCategory parent = categoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()){
			// 如果父节点为叶子节点应该改为父节点
			parent.setIsParent(true);
			// 更新父节点
			categoryMapper.updateByPrimaryKey(parent);
		}
		// 返回结果
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public void updateContentgory(Long id, String name) {
		// 根据ID查询要更新的节点
		TbContentCategory cateGory = categoryMapper.selectByPrimaryKey(id);
		// 修改数据
		cateGory.setName(name);
		// 更新数据库
		categoryMapper.updateByPrimaryKey(cateGory);
	}

	@Override
	/**
	 *  1.判断是叶子节点还是父节点
	 *  2.1如果为叶子节点，直接删除，并查询其父节点下是否还有其他子节点
	 *  有则不改变父节点身份；没有则将父节点变为叶子节点
	 *  2.2如果为父节点，（1）递归删除叶子节点 （2）如果下面有子节点，则不删除，没有就删除
	 *  
	 *  返回结果：
	 *  	true -- 父节点下没有子节点/该节点为叶子节点,删除该节点
	 *      false -- 父节点下有子节点，不删除节点，向用户提示信息
	 */
	public boolean deleteContentgory(Long id) {
		// 根据ID获取节点
		TbContentCategory cateGory = categoryMapper.selectByPrimaryKey(id);
		// 判断是叶子节点还是父节点
		if(!cateGory.getIsParent()){ // 说明为叶子节点
			// 先获得父节点ID
			Long parentId = cateGory.getParentId();
			// 删除叶子节点
			categoryMapper.deleteByPrimaryKey(id);
			// 判断父节点是否有其他子节点
			boolean result = parentHasChild(parentId);
			if(!result){
				TbContentCategory parentCateGory = categoryMapper.selectByPrimaryKey(parentId);
				// 设置父节点为子节点
				parentCateGory.setIsParent(false);
				// 更新数据库
				categoryMapper.updateByPrimaryKey(parentCateGory);
			}
			return true;
		}else{ // 说明为父节点
			boolean result = parentHasChild(id);
			if(!result){ // 父节点没有子节点
				// 删除父节点
				categoryMapper.deleteByPrimaryKey(id);
				return true;
			}
			return false;
		}
	}
	
	// 判断父节点是否有其他子节点
	private boolean parentHasChild(Long parentId){
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		// 添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = categoryMapper.selectByExample(example);
		if(list.size() == 0){
			return false;
		}
		return true;
	}

}
