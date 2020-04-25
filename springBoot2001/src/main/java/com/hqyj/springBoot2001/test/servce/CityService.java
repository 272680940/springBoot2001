package com.hqyj.springBoot2001.test.servce;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot2001.common.Result;
import com.hqyj.springBoot2001.test.model.City;

public interface CityService {

	List<City> getCitiesByCountryId(int countryId);
	
	/**
	 * 多参数查询
	 * 通过cityName和localCityName查询
	 */
	City queryCityByName(String cityName,String localCityName);
	
	/**
	 * 多条件查询
	 * 通过cityName和localCityName查询
	 */
	City queryCityByName2(@Param("cityName") String cityName,@Param("localCityName") String localCityName);

	/**
	 * 分页技术 currentPage 
	 * 
	 * 当前页 pageSize 页大小
	 * 
	 * 使用分页插件 PageInfo<分页对象模型> 需要参数： 当前页：currentPage 每页大小：pageSize
	 * 
	 */
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);

	/**
	 * 添加城市
	 */
	Result<City> inesertCity(City city);
	
	
	/**
	 * 修改城市
	 */
	Result<City> updateCity(City city);

	
	/**
	 * 删除城市
	 */
	Result<Object> deleteCity(int cityId);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
