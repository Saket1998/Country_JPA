package com.cognizant.ormlearn.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;

@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepository;

	@Transactional
	public List<Country> getAllCountries() {
		List<Country> findAll = new ArrayList<Country>();
		findAll = countryRepository.findAll();
		return findAll;
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException{
		Optional<Country> result = countryRepository.findById(countryCode);
		if (!result.isPresent()) {
			throw new CountryNotFoundException();
		}
		Country country = result.get();
		return country;
	}
	
	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String co_code, String co_name) throws CountryNotFoundException {
		Optional<Country> findById = countryRepository.findById(co_code);
		if(!findById.isPresent()) {
			throw new CountryNotFoundException();
		}
		Country country = findById.get();
		country.setName(co_name);
		countryRepository.save(country);
	}
	
	@Transactional
	public void deleteCountry(String co_code) {
		countryRepository.deleteById(co_code);
	}
}

