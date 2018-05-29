package pers.ken.ssm.mapper;

import pers.ken.ssm.po.Items;
import pers.ken.ssm.po.ItemsCustom;
import pers.ken.ssm.po.ItemsExample;
import pers.ken.ssm.po.ItemsQueryVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
	
	//商品查询列表
	public List<ItemsCustom> findItemsList (ItemsQueryVo itemsQueryVo) throws Exception ; 
	
	//根据id查询商品信息
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	//根据id更新商品信息
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
	
	//删除商品
	public void deleteItems(Integer[] id);
}
