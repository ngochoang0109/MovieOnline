package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Country;

import java.util.List;

public interface CountryService {
    Country addCountry(Country Country);
    List<Country> findAll();
    Country getCountryById(Long id);
    void deleteCountryById(Long id);
}
