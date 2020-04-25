package com.hqyj.springBoot2001.test.servce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.springBoot2001.test.dao.CountryDao;
import com.hqyj.springBoot2001.test.model.Country;
import com.hqyj.springBoot2001.test.servce.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryDao countryDao;

	@Override
	public Country getCountryById(int countryId) {
		Country country = countryDao.getCountryById(countryId);
		return country;
	}
	
	
	/**
	 *	通过mapper.xml文件查询
	 */
	@Override
	public Country getCountryById2(int countryId) {
		return countryDao.getCountryById2(countryId);
	}

	@Override
	public Country getContryByCountryName(String countryName) {
		Country country = countryDao.getContryByCountryName(countryName);
		return country;
	}


}
