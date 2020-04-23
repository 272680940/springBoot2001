package com.hqyj.springBoot2001.test.servce;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot2001.common.Result;
import com.hqyj.springBoot2001.test.model.City;

public interface CityService {

	List<City> getCitiesByCountryId(int countryId);

	/**
	 * 分页技术 currentPage 当前页 pageSize 页大小
	 * 
	 * 使用分页插件 PageInfo<分页对象模型> 需要参数： 当前页：currentPage 每页大小：pageSize
	 * 
	 */
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);

	/**
	 * 添加城市
	 */
	Result<City> inesertCity(City city);

}
