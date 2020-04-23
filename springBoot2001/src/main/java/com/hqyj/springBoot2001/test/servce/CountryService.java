package com.hqyj.springBoot2001.test.servce;

import com.hqyj.springBoot2001.test.model.Country;

public interface CountryService {
	
	Country getCountryById(int countryId);

	Country getContryByCountryName(String countryName);
}
