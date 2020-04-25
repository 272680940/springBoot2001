package com.hqyj.springBoot2001.test.servce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	//添加
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

	
	
	//修改
	@Override
	public Result<City> updateCity(City city) {
		Result<City> result = new Result<>(ResultEnum.SUCCESS.status,"update success.");
		try {
			cityDao.updateCity(city);
			result.setObject(city);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	
	
	@Override
	/**
	 * 添加事务的注解【@Transactional】
	 * @Transactional(属性)
	 * 	常用属性：
	 * 		noRollbackFor（如：noRollbackFor = ArithmeticException.class）  作用：遇此异常报错，但不回滚
	 * 		RollbackFor 作用：自定义的异常，回滚
	 * 		propagation 作用：指定事务的种类
	 */
	//删除
	@Transactional
	public Result<Object> deleteCity(int cityId) {
		Result<Object> result = new Result<>(ResultEnum.SUCCESS.status,"delete success.");
		cityDao.deleteCity(cityId);
		//自定义算数异常
		//int sum = 1/0;
		return result;
	}

	
	//通过cityName和localCityName查询
	//多参数查询
	@Override
	public City queryCityByName(String cityName, String localCityName) {
		return cityDao.queryCityByName(cityName, localCityName);
	}

	
	//条件查询
	@Override
	public City queryCityByName2(String cityName, String localCityName) {
		return cityDao.queryCityByName2(cityName, localCityName);
	}
	
	
	

}
