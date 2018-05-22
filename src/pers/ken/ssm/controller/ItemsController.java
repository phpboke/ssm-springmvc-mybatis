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
	
	@RequestMapping("queryItems")
	public ModelAndView queryItems(HttpServletRequest request) throws Exception{
		//调用service的findItemsList方法
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemsList", itemsList);
		
		modelAndView.setViewName("items/itemsList");
		
		return modelAndView;
		
	}
}
