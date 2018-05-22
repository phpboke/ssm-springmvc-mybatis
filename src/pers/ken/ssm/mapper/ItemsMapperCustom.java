package pers.ken.ssm.mapper;

import pers.ken.ssm.po.Items;
import pers.ken.ssm.po.ItemsCustom;
import pers.ken.ssm.po.ItemsExample;
import pers.ken.ssm.po.ItemsQueryVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
	
	//商品查询列表
	public List<ItemsCustom> findItemsCustoms (ItemsQueryVo itemsQueryVo) throws Exception ; 
}
