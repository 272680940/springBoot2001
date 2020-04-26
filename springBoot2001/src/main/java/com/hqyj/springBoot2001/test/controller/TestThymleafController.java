package com.hqyj.springBoot2001.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.springBoot2001.test.model.City;
import com.hqyj.springBoot2001.test.model.Country;
import com.hqyj.springBoot2001.test.servce.CityService;
import com.hqyj.springBoot2001.test.servce.CountryService;

@Controller
@RequestMapping("/test")
public class TestThymleafController {
	@Autowired
	private CountryService countryService;
	@Autowired
	private CityService cityService;
	 
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
		modelMap.addAttribute("template", "test/index");
		return "index";
	}
}
