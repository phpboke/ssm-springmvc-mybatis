package pers.ken.ssm.po;

import java.util.List;

/**
 * @desc 商品包装类，里面可以包装和商品相关的各种查询条件
 * @author ken
 *
 */
public class ItemsQueryVo {
	//商品基本信息
	private Items items;
	//为了系统扩展性，对原始items进行了扩展
	private ItemsCustom itemsCustom;
	//List对象中包含pojo
	private List<ItemsCustom> itemsList;
	
	public Items getItems(){
		return items;
	}
	
	public void setItems(Items items){
		this.items = items;
	}
	
	public ItemsCustom getItemsCustom(){
		return itemsCustom;
	}
	
	public void setItemsCustom(ItemsCustom itemsCustom){
		this.itemsCustom = itemsCustom;
	}

	/**
	 * @return the itemsList
	 */
	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	/**
	 * @param itemsList the itemsList to set
	 */
	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}
}
