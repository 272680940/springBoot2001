package com.hqyj.springBoot2001.modules.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.springBoot2001.modules.test.entity.Country;
import com.hqyj.springBoot2001.modules.test.service.CountryService;


@RestController
@RequestMapping("/api")
public class CountryController {
	@Autowired
	private CountryService countryService ;
	
	/**
	 * 	没有通过配置文件查询
	 *	通过 countryId 查询国家
	 * 	localhost/api/getCountryById/522
	 */
	@RequestMapping("/getCountryById/{countryId}")
	//传参是int类型，使用【@PathVariable】
	public Country getCountryById(@PathVariable int countryId){
		return countryService.getCountryById(countryId);
	}
	
	/**
	 * 通过countryMapper.xml文件查询
	 * localhost/api/getCountryById2/522
	 */
	@RequestMapping("/getCountryById2/{countryId}")
	public Country getCountryById2(@PathVariable int countryId){
		return countryService.getCountryById2(countryId);
	}
	
	
	/**
	 	通过 countryName 查询国家
	  	localhost/api/getContryByCountryName?countryName = china（数据库的数据）
	 */
	@RequestMapping("/getContryByCountryName")
	//传参是字符串，使用【@RequestParam】
	public Country getContryByCountryName(@RequestParam String countryName) {
		return countryService.getContryByCountryName(countryName);
	}
	
	
	
}
