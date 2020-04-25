package com.hqyj.springBoot2001.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.springBoot2001.test.model.City;

@Mapper
public interface CityDao {
	// 通过countryId查询城市
	@Select("select * from m_city where country_id=#{countryId}")
	List<City> getCitiesByCountryId(int countryId);
	
	
	/**
	 * 	Mybatis多参数查询
	 * 	单个参数时，直接使用
	 * 	多个参数时，需要用注解【@Param】
	 */
	@Select("select * from m_city where city_name = #{cityName,jdbcType=VARCHAR} "
			+ "and local_city_name = #{localCityName,jdbcType=VARCHAR}")
	City queryCityByName(@Param("cityName") String cityName,@Param("localCityName") String localCityName);
	
	
	
	/**
	 * 条件查询
	 */
	@Select("<script> "
			+ "select * from m_city"
			+ "<where>"
			+ "<if test='cityName != \"\" and cityName != null'>"
			+ "and city_name = #{cityName,jdbcType=VARCHAR}"
			+ "</if>"
			+ "<if test='localCityName != \"\" and localCityName != null'>"
			+ "and local_city_name = #{localCityName,jdbcType=VARCHAR}"
			+ "</if>"
			+ "</where>"
			+ "limit 1"
			+ "</script>")
	City queryCityByName2(@Param("cityName") String cityName,@Param("localCityName") String localCityName);
	
	
	

	/**
	 * 在插入数据时，要将其主键值返回 在添加数据的方法名上加上注解【@options】
	 * 【useGeneratedKeys】表示：是否使用其主键唯一标识 
	 * 【keyColumn】表示：映射到数据库对应model的字段
	 * 【keyProperty】表示：映射到model层的属性
	 */
	@Insert("insert into m_city (city_name, local_city_name,country_id,date_created) "
			+ "values (#{cityName,jdbcType=VARCHAR}, #{localCityName,jdbcType=VARCHAR},"
			+ "#{countryId,jdbcType=INTEGER},#{dateCreated,jdbcType=TIMESTAMP})")
	// 添加数据,使用注解【@Options】
	@Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
	void insertCity(City city);

	
	
	
	// 修改数据，使用注解【@Update】
	@Update("update m_city set city_name = #{cityName,jdbcType=VARCHAR} "
			+ "where city_id=#{cityId,jdbcType=INTEGER}")
	void updateCity(City city);
	
	
	
	//删除数据，使用注解【@Delete】
	@Delete("delete from m_city where city_id=#{cityId,jdbcType=INTEGER}")
	void deleteCity(int cityId);
	
	
	
	
}