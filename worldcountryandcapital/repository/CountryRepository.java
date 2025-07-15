/**
 * 
 */
package com.examtest.worldcountryandcapital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examtest.worldcountryandcapital.model.Country;

/**
 * @author anosi
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByNameIgnoreCase(String name);
    List<Country> findByCapitalIgnoreCase(String capital);
    List<Country> findByPopulation(Long population);
}

