/**
 * 
 */
package com.examtest.worldcountryandcapital.service.impl;

/**
 * @author anosi
 *
 */

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examtest.worldcountryandcapital.dto.CountryCreateDto;
import com.examtest.worldcountryandcapital.dto.CountryResponseDto;
import com.examtest.worldcountryandcapital.dto.CountryUpdateDto;
import com.examtest.worldcountryandcapital.exception.ResourceAlreadyExistsException;
import com.examtest.worldcountryandcapital.exception.ResourceNotFoundException;
import com.examtest.worldcountryandcapital.model.Country;
import com.examtest.worldcountryandcapital.repository.CountryRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final FileStorageService fileStorageService;

    public List<CountryResponseDto> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public CountryResponseDto getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + id));
        return mapToDto(country);
    }

    public List<CountryResponseDto> getCountriesByRegion(String region) {
        return countryRepository.findByRegion(region).stream()
                .map(this::mapToDto)
                .toList();
    }

    @Transactional
    public CountryResponseDto createCountry(CountryCreateDto dto) throws IOException {
       
    	countryRepository.findByName(dto.name())
        .ifPresent(country -> {
            throw new ResourceAlreadyExistsException("Country with name '" + dto.name() + "' already exists");
        });

        String imagePath = dto.image() != null ? fileStorageService.storeFile(dto.image()) : null;

        Country country = new Country();
        country.setName(dto.name());
        country.setPopulation(dto.population());
        country.setRegion(dto.region());
        country.setCapital(dto.capital());
        country.setImagePath(imagePath);

        Country savedCountry = countryRepository.save(country);
        return mapToDto(savedCountry);
    }

    @Transactional
    public CountryResponseDto updateCountry(Long id, CountryUpdateDto dto) throws IOException {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + id));

        if (dto.name() != null) {
            country.setName(dto.name());
        }
        if (dto.population() != null) {
            country.setPopulation(dto.population());
        }
        if (dto.region() != null) {
            country.setRegion(dto.region());
        }
        if (dto.capital() != null) {
            country.setCapital(dto.capital());
        }
        if (dto.image() != null && !dto.image().isEmpty()) {
            String imagePath = fileStorageService.storeFile(dto.image());
            country.setImagePath(imagePath);
        }

        Country updatedCountry = countryRepository.save(country);
        return mapToDto(updatedCountry);
    }

    @Transactional
    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country not found with id: " + id);
        }
        countryRepository.deleteById(id);
    }

    private CountryResponseDto mapToDto(Country country) {
        String imageUrl = country.getImagePath() != null ? 
                "/api/countries/images/" + country.getImagePath() : null;
        
        return new CountryResponseDto(
                country.getId(),
                country.getName(),
                country.getPopulation(),
                country.getRegion(),
                country.getCapital(),
                imageUrl
        );
    }
}
