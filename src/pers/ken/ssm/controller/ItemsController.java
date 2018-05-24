package pers.ken.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pers.ken.ssm.po.ItemsCustom;
import pers.ken.ssm.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	/**
	 * @desc 查询商品
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryItems")
	public ModelAndView queryItems(HttpServletRequest request) throws Exception{
		//调用service的findItemsList方法
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemsList", itemsList);
		
		modelAndView.setViewName("items/itemsList");
		
		return modelAndView;
		
	}
	
	/**
	 * @desc 更新商品的页面
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping(value="/editItems", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView editItems() throws Exception{
		//调用service的findItemsById的方法
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemsCustom", itemsCustom);
		
		modelAndView.setViewName("items/editItems");
		
		return modelAndView;
		
	}*/
	
	/**
	 * @desc controller返回一个字符串
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping(value="/editItems", method={RequestMethod.GET, RequestMethod.POST})
	public String editItems(Model model, @RequestParam(value="id", defaultValue="2") Integer id) throws Exception{
		//调用service的findItemsById的方法
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("itemsCustom", itemsCustom);
//		modelAndView.setViewName("items/editItems");
		
		//这里就相当于上面的3条语句
		model.addAttribute("itemsCustom", itemsCustom);
		
		return "items/editItems";
		
	}*/
	
	/**
	 * @desc RequestParam参数绑定
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editItems", method={RequestMethod.GET, RequestMethod.POST})
	public String editItems(Model model, @RequestParam(value="id", required=true) Integer id) throws Exception{
		//调用service的findItemsById的方法
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		
		model.addAttribute("itemsCustom", itemsCustom);
		
		return "items/editItems";
		
	}
	
	/**
	 * @desc 更新商品信息
	 * @param request
	 * @param id
	 * @param itemsCustom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request, Integer id, ItemsCustom itemsCustom) throws Exception{
		
		itemsService.updateItems(id, itemsCustom);
		
		return "success";
	}
}
