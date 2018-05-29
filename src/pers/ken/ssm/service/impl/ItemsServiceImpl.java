package pers.ken.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import pers.ken.ssm.mapper.ItemsMapper;
import pers.ken.ssm.mapper.ItemsMapperCustom;
import pers.ken.ssm.po.Items;
import pers.ken.ssm.po.ItemsCustom;
import pers.ken.ssm.po.ItemsQueryVo;
import pers.ken.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		// 通过 itemsMapperCustom 查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	
	//根据id查询获取商品的信息
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		//调用po自动生成的mapper方法
		Items items = itemsMapper.selectByPrimaryKey(id);
		//返回一个ItemsCustom的扩展对象
		ItemsCustom itemsCustom = new ItemsCustom();
		//复制对象属性
		BeanUtils.copyProperties(items, itemsCustom);
		
		return itemsCustom;
	}

	//通过id更新商品信息
	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

	//根据商品id批量删除商品
	@Override
	public void deleteItems(Integer[] id) throws Exception {
		// TODO Auto-generated method stub
		itemsMapperCustom.deleteItems(id);
	}
	
}
