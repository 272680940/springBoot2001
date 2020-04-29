package com.hqyj.springBoot2001.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot2001.modules.common.vo.Result;
import com.hqyj.springBoot2001.modules.test.entity.City;
import com.hqyj.springBoot2001.modules.test.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {
	@Autowired
	private CityService cityService;

	/**
	 * 通过CountryId查询城市 localhost/api/getCitiesByCountryId/522
	 */
	@RequestMapping("/getCitiesByCountryId/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		return cityService.getCitiesByCountryId(countryId);
	}

	/**
	 * 分页 localhost/api/getCitiesByPage?currentPage=1&pageSize=10&countryId=522
	 */
	@RequestMapping("/getCitiesByPage")
	public PageInfo<City> getCitiesByPage(@RequestParam int currentPage, @RequestParam int pageSize,
			@RequestParam int countryId) {
		return cityService.getCitiesByPage(currentPage, pageSize, countryId);
	}
	
	/**
	 * 新增：用@PostMapping
	 * 修改：用@PutMapping
	 * 删除：用@DeleteMapping
	 */
	
	/*
		新增：用@PostMapping
		post请求使用注解【@PostMapping】
		【consumes】设置进入接口的参数格式
		【application/json】表示json格式
		参数格式是json,则方法中接收参数的注解为【@RequestBody】
		localhost/api/city
	*/
	@PostMapping(value="/city",consumes="application/json")
	public Result<City> insertCity(@RequestBody City city){
		return cityService.inesertCity(city);
	}
	
	
	/*
		修改：使用注解【@PutMapping】
		【consumes】设置进入接口的参数格式
		【application/x-www-form-urlencoded】表示form表单提交
		参数格式是form表单，则方法中接收city的注解为【@ModelAttribute】
		localhost/api/city
	 */
	@PutMapping(value="/city",consumes="application/x-www-form-urlencoded")
	public Result<City> updateCity(@ModelAttribute City city) {
		return cityService.updateCity(city);
	}
	
	
	
	/*
		删除：使用注解【@DeleteMapping】
		localhost/api/deleteCity/2254
	 */
	@DeleteMapping("/deleteCity/{cityId}")
	public Result<Object> deleteCity(@PathVariable int cityId) {
		return cityService.deleteCity(cityId);
	}
	
	
	//多参数查询
	//通过cityName和localCityName查询
	//@RequestParam 设置传入参数
	//localhost/api/queryCityByName?cityName=testName&localCityName=liupanshui
	@RequestMapping("/queryCityByName")
	public City queryCityByName(@RequestParam String cityName,@RequestParam  String localCityName) {
		return cityService.queryCityByName(cityName, localCityName);
	}
	
	
	//条件查询
	//@RequestParam 设置传入参数
	//localhost/api/queryCityByName2?cityName=testName&localCityName=liupanshui
	@RequestMapping("/queryCityByName2")
	public City queryCityByName2(@RequestParam(required = false) String cityName,
			@RequestParam(required = false) String localCityName) {
		return  cityService.queryCityByName2(cityName, localCityName);
	}
}
