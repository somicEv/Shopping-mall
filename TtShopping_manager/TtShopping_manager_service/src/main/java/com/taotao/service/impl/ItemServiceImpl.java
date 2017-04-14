package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * @author 浩瀚
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper; 
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem getTbItem(long itemId) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 设置查询条件
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample tbItemExample = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(tbItemExample);
		// 取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 封装对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		// 生成商品ID
		long itemId = IDUtils.genItemId();
		// 补全item属性
		item.setId(itemId);
		// 商品状态：1-正常 2-下架 3-删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 向商品表插入数据
		itemMapper.insert(item);
		// 创建商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		// 补全pojo的属性
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		// 向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		// 返回结果
		return TaotaoResult.ok();
	}

}
