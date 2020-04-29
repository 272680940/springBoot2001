package com.hqyj.springBoot2001.modules.test.service;

import com.hqyj.springBoot2001.modules.test.entity.Country;

public interface CountryService {
	
	Country getCountryById(int countryId);
	
	//通过mapper.xml配置文件查询
	Country getCountryById2(int countryId);

	Country getContryByCountryName(String countryName);
}
