package com.taotao.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	TbItem getTbItem(long itemId);
	
	EasyUIDataGridResult getItemList(int page,int rows);
	
	TaotaoResult addItem(TbItem item,String desc);
}
