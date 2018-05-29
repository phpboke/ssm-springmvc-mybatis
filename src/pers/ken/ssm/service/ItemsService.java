package pers.ken.ssm.service;

import java.util.List;

import pers.ken.ssm.po.ItemsCustom;
import pers.ken.ssm.po.ItemsQueryVo;

public interface ItemsService {

	//查询商品列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//根据商品id查询商品信息
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	//根据商品id更新商品信息
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
	
	//根据商品id批量删除商品
	public void deleteItems(Integer[] id) throws Exception;
	
}
