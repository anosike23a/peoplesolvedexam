/**
 * 
 */
package com.examtest.worldcountryandcapital.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examtest.worldcountryandcapital.dto.CountryDto.CountryRequestDTO;
import com.examtest.worldcountryandcapital.dto.CountryResponseDTO;
import com.examtest.worldcountryandcapital.exception.CountryNotFoundException;
import com.examtest.worldcountryandcapital.model.Country;
import com.examtest.worldcountryandcapital.repository.CountryRepository;
import com.examtest.worldcountryandcapital.service.CountryService;

import lombok.RequiredArgsConstructor;

/**
 * @author anosi
 *
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository repository;

    @Value("${app.flag.upload-dir}")
    private String flagUploadDir;

    @Override
    public List<CountryResponseDTO> getAllCountries() {
        return repository.findAll().stream()
            .map(this::mapToDTO).toList();
    }

    @Override
    public CountryResponseDTO getCountryByName(String name) {
        return mapToDTO(repository.findByNameIgnoreCase(name)
            .orElseThrow(() -> new CountryNotFoundException(name)));
    }

    @Override
    public List<CountryResponseDTO> searchByCapital(String capital) {
        return repository.findByCapitalIgnoreCase(capital).stream()
            .map(this::mapToDTO).toList();
    }

    @Override
    public List<CountryResponseDTO> searchByPopulation(Long population) {
        return repository.findByPopulation(population).stream()
            .map(this::mapToDTO).toList();
    }

    @Override
    public CountryResponseDTO addCountry(CountryRequestDTO dto, MultipartFile flag) {
        String filename = UUID.randomUUID() + "_" + flag.getOriginalFilename();
        Path path = Paths.get(flagUploadDir + "/" + filename);
        try {
            Files.copy(flag.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException("Flag upload failed");
        }

        Country country = new Country();
        country.setName(dto.name());
        country.setPopulation(dto.population());
        country.setCapital(dto.capital());
        country.setFlagPath("/flags/" + filename);
        return mapToDTO(repository.save(country));
    }

    private CountryResponseDTO mapToDTO(Country country) {
        return new CountryResponseDTO(
            country.getName(),
            country.getCapital(),
            country.getPopulation(),
            country.getFlagPath()
        );
    }
    
    
}

