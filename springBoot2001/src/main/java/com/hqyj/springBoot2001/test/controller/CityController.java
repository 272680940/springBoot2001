package com.hqyj.springBoot2001.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot2001.common.Result;
import com.hqyj.springBoot2001.test.model.City;
import com.hqyj.springBoot2001.test.servce.CityService;

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
	
	@PostMapping(value="/city",consumes = "application/json")
	public Result<City> insertCity(City city){
		return cityService.inesertCity(city);
	}
}
