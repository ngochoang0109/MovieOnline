package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Country;
import com.tlcn.movieonline.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    CountryRepository CountryRepository;

    @Override
    @Transactional
    public Country addCountry(Country g) {
        Country Country= CountryRepository.save(g);
        return Country;
    }

    @Override
    public List<Country> findAll() {
        List<Country> lstCountry=CountryRepository.findAll();
        return lstCountry;
    }

    @Override
    public Country getCountryById(Long id) {
        Country Country= CountryRepository.getCountryById(id);
        return Country;
    }

    @Override
    public void deleteCountryById(Long id) {
        CountryRepository.deleteById(id);
    }
}
