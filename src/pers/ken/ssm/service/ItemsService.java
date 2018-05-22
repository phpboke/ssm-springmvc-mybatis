package pers.ken.ssm.service;

import java.util.List;

import pers.ken.ssm.po.ItemsCustom;
import pers.ken.ssm.po.ItemsQueryVo;

public interface ItemsService {

	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
