package com.hqyj.springBoot2001.test.servce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot2001.common.Result;
import com.hqyj.springBoot2001.common.Result.ResultEnum;
import com.hqyj.springBoot2001.test.dao.CityDao;
import com.hqyj.springBoot2001.test.model.City;
import com.hqyj.springBoot2001.test.servce.CityService;
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;

	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		List<City> cityList= cityDao.getCitiesByCountryId(countryId);
		return cityList;
	}

	//分页
	@Override
	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		//分页插件的写法
		/* 
		 * 	使用包装好的类：PageHelper
		 * 	currentPage	当前页
		 * 	pageSize	页大小
		 */
		PageHelper.startPage(currentPage, pageSize);
		//准备数据
		List<City> cities = cityDao.getCitiesByCountryId(countryId);
		
		return new PageInfo<City>(cities);
	}

	@Override
	public Result<City> inesertCity(City city) {
		Result<City> result = new Result<>(ResultEnum.SUCCESS.status,"insert success.");
		try {
			cityDao.insertCity(city);
			result.setObject(city);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
