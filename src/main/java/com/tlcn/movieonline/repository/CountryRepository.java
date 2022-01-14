package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long > {
    Country getCountryById(Long id);
    void deleteById(Long id);
    Country[] getCountriesByName(String name);

}
