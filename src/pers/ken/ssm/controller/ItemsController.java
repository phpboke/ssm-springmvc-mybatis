package pers.ken.ssm.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.taglibs.standard.tag.common.xml.ForEachTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pers.ken.ssm.controller.validation.ValidGroup1;
import pers.ken.ssm.po.ItemsCustom;
import pers.ken.ssm.po.ItemsQueryVo;
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
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception{
		//调用service的findItemsList方法
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		
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
	public String editItemsSubmit(Model model, 
			HttpServletRequest request, 
			Integer id, 
			@Validated(value={ValidGroup1.class}) ItemsCustom itemsCustom, 
			BindingResult bindingResult,
			MultipartFile items_pic//接收商品图片
			) throws Exception{
		
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError objectError : allErrors){
				//输出错误信息
				System.out.print(objectError.getDefaultMessage());
			}
			
			//显示错误信息
			model.addAttribute("allErrors", allErrors);
			//展示模型数据
			model.addAttribute("itemsCustom", itemsCustom);
			
			return "items/editItems";
		}
		
		//上传文件
		String originalFilename = items_pic.getOriginalFilename();
		if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){
			String pic_path = "D:\\eclipse_wyh\\ssm-springmvc-mybatis\\WebRoot\\WEB-INF\\jsp\\temp\\";
			
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			
			File newFile = new File(pic_path + newFileName);
			
			items_pic.transferTo(newFile);
			
			itemsCustom.setPic(newFileName);
		}
		
		
		itemsService.updateItems(id, itemsCustom);
		
		return "success";
	}
	
	@RequestMapping(value="deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception{
		
		itemsService.deleteItems(items_id);
		
		return "success";
	}
	
	//批量展示数据
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception{
		//调用service的findItemsList方法
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemsList", itemsList);
		
		modelAndView.setViewName("items/editItemsQuery");
		
		return modelAndView;
		
	}
	
	//批量更新数据，使用List批量接收数据。前提是在ItemsQueryVo中定义一个List<ItemsCustom>
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		
		return "success";
	}
	
	//查询商品信息，并输出json格式
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception{
		
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		
		return itemsCustom;
	}
	
}
