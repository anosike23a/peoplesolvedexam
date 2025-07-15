/**
 * 
 */
package com.examtest.worldcountryandcapital.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.examtest.worldcountryandcapital.dto.CountryDto.CountryRequestDTO;
import com.examtest.worldcountryandcapital.dto.CountryResponseDTO;

/**
 * @author anosi
 *
 */
public interface CountryService {
    List<CountryResponseDTO> getAllCountries();
    CountryResponseDTO getCountryByName(String name);
    List<CountryResponseDTO> searchByCapital(String capital);
    List<CountryResponseDTO> searchByPopulation(Long population);
    CountryResponseDTO addCountry(CountryRequestDTO dto, MultipartFile flag);
}
