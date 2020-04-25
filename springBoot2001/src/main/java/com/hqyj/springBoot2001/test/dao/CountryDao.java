package com.hqyj.springBoot2001.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.hqyj.springBoot2001.test.model.Country;

@Mapper
public interface CountryDao {
	// 通过 countryId 查询国家
	@Select("select * from m_country where country_id=#{countryId} ")

	// 封装结果集
	@Results(id = "countryResult", // 将此结果集做一个唯一标识，方便调用
			value = { @Result(column = "country_id", property = "countryId"), // 再次映射countryId
					@Result(column = "country_id", property = "cities", // 通过countryId查询城市，返回数据到cities
					javaType = List.class,
					// 通过getCitiesByCountryId(countryId)方法查询城市信息
					// many表示返回多条数据，单条数据用one
					many = @Many(select = "com.hqyj.springBoot2001.test.dao.CityDao.getCitiesByCountryId")) })

	// 通过 countryId 查询该国家的所有城市cities
	Country getCountryById(int countryId);
	
	/**
	 * 通过 countryMapper.xml 文件查询
	 * 此时不用加注解【@select】
	 */
	Country getCountryById2(int countryId);

	
	// 调用写好的方法，避免代码冗余
	// 格式：【@ResultMap(value = "某个结果集的id值")】
	// 通过 countryName 查询国家
	@Select("select * from m_country where country_Name=#{countryName}")
	@ResultMap(value = "countryResult")
	Country getContryByCountryName(String countryName);

	
}