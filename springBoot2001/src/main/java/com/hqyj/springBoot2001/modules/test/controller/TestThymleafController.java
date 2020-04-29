package com.hqyj.springBoot2001.modules.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hqyj.springBoot2001.modules.test.entity.City;
import com.hqyj.springBoot2001.modules.test.entity.Country;
import com.hqyj.springBoot2001.modules.test.service.CityService;
import com.hqyj.springBoot2001.modules.test.service.CountryService;


@Controller
@RequestMapping("/test")
public class TestThymleafController {
	@Autowired
	private CountryService countryService;
	@Autowired
	private CityService cityService;
	
	/** 
	 * 文件上传
	 * localhost/test/file
	*/
	@PostMapping(value = "/file", consumes = "multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, 
			RedirectAttributes redirectAttributes) {
		//判断文件是否为空
		if (file.isEmpty()) {
			//上传文件为空，返回提示信息到页面
			//redirect重定向页面，需要使用【RedirectAttributes】传递信息
			redirectAttributes.addFlashAttribute("message", "请选择文件！");
			return "redirect:/test/index";
		}
		
		//得到文件的相关信息
		try {
			//1、得到原始名字
			String fileName = file.getOriginalFilename();
			
			//2、指定文件存放路径
			String destFilePath = "F:\\Download\\"+fileName;
			
			//3、在指定文件存放路径下 创建一个空的文件
			File destFile = new File(destFilePath);
			
			//4、将上传的文件传输到这个文件中
			file.transferTo(destFile);
			
			//5、上传文件成功，返回页面的提示信息
			redirectAttributes.addFlashAttribute("message", "上传文件成功！");
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			//上传出错或是出现异常，
			redirectAttributes.addFlashAttribute("message", "上传出错或是出现异常！");
			return "redirect:/test/index";
		}
		
		return "redirect:/test/index";
	}
	
	
	/** 
	 * 上传多个文件
	 * localhost/test/files
	 */
	@PostMapping(value = "/files", consumes = "multipart/form-data")
	public String uploadFiles(@RequestParam MultipartFile[] files, 
			RedirectAttributes redirectAttributes) {
		boolean  isEmpty = true;
		
		try {
			//遍历上传的多个文件
			for (MultipartFile file : files) {
				//判断每一个文件是否为空
				if (file.isEmpty()) {
					//文件为空，则跳出当前循环
					continue;
				}
				
				/**
				 * 	若上传的文件有不为空的
				 */
				//1、得到原始名字
				String fileName = file.getOriginalFilename();
				
				//2、指定文件存放路径
				String destFilePath = "F:\\Download\\"+fileName;
				
				//3、在指定文件存放路径下 创建一个空的文件
				File destFile = new File(destFilePath);
				
				//4、将上传的文件传输到这个文件中
				file.transferTo(destFile);
				
				//表示至少有一个文件上传
				isEmpty = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//上传出错或是出现异常，
			redirectAttributes.addFlashAttribute("message", "上传出错或是出现异常！");
			return "redirect:/test/index";
		}
		
		// isEmpty=false 表示没有文件上传，全部为空
		if (isEmpty) {
			//返回上传失败的信息
			redirectAttributes.addFlashAttribute("message", "请选择文件！");
		}else {
			// isEmpty=true 表示有文件上传
			//返回上传成功的信息
			redirectAttributes.addFlashAttribute("message", "上传成功！");
		}
		return "redirect:/test/index";
		
	}
	
	
	 
	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap) {
		//传到页面的参数
		modelMap.addAttribute("thymeleafTitle", "thymeleafTitle");
		modelMap.addAttribute("checked", true);
		modelMap.addAttribute("currentNumber", 22);
		modelMap.addAttribute("thymeleafTitle", null);
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("baiduUrl", "http://www.baidu.com");
		int countryId = 522;
		Country country = countryService.getCountryById(countryId);
		modelMap.addAttribute("country", country);
		List<City> cities = cityService.getCitiesByCountryId(countryId);
		City city = cities.get(0);
		modelMap.addAttribute("city",city);
		modelMap.addAttribute("cities",cities);
		modelMap.addAttribute("updateCityUri","/api/city");
		
		
		//要返回外层的index.html(碎片组装器),将header、body、footer都加载到碎片组装器里面展示
		//需要设置template变量，更换碎片组装器中的body部分
		/*
		 外层的index.html(碎片组装器)的内容：
		 	<!-- body -->
			<div th:if="${template}">
				<div th:replace="${template}"></div>
			</div>
		 */
		//modelMap.addAttribute("template", "test/index");
		return "index";
	}
}
