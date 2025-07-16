/**
 * 
 */
package com.examtest.worldcountryandcapital.repository;

/**
 * @author anosi
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.examtest.worldcountryandcapital.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);
    List<Country> findByRegion(String region);
}
