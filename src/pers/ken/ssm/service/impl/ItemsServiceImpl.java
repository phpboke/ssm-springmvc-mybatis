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

	private ItemsMapperCustom itemsMapperCustom;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		// 通过 itemsMapperCustom 查询数据库
		return itemsMapperCustom.findItemsCustoms(itemsQueryVo);
	}
	
	
}
