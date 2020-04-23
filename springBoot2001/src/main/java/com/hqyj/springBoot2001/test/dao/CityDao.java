package com.hqyj.springBoot2001.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hqyj.springBoot2001.test.model.City;

@Mapper
public interface CityDao {
	// 通过countryId查询城市
	@Select("select * from m_city where country_id=#{countryId}")
	List<City> getCitiesByCountryId(int countryId);

	// 添加数据
	@Insert(" insert into m_city (city_name, local_city_name,country_id,date_created) values (#{cityName,jdbcType=VARCHAR}, #{localCityName,jdbcType=VARCHAR},#{countryId,jdbcType=INTEGER},#{dateCreated,jdbcType=TIMESTAMP})")
	void insertCity(City city);

}