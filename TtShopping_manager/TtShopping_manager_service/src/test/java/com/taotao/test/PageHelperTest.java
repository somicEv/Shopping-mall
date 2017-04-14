package com.taotao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;


public class PageHelperTest {
	@Test
	public void testPageHelper() throws Exception{
		
		// 使用PageHelper静态方法，设置分页条件
		PageHelper.startPage(1, 10);
		// 执行查询
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		// 创建Example对象
		TbItemExample itemExample = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(itemExample);
		// 取得分页信息
		PageInfo<TbItem> pageInfo = new PageInfo(list);
		
		System.out.println("总记录数："+pageInfo.getTotal());
		System.out.println("总页数："+pageInfo.getPages());
		for (TbItem tbItem : list) {
			System.out.println(tbItem.toString());
		}
	}
}
