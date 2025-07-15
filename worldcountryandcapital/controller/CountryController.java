/**
 * 
 */
package com.examtest.worldcountryandcapital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.examtest.worldcountryandcapital.dto.CountryDto.CountryRequestDTO;
import com.examtest.worldcountryandcapital.dto.CountryResponseDTO;
import com.examtest.worldcountryandcapital.service.CountryService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @author anosi
 *
 */
@RestController
@RequestMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CountryController {
	
    private final CountryService service;

    @Operation(summary = "Retrieve all countries")
    @GetMapping
    public ResponseEntity<List<CountryResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllCountries());
    }

    @GetMapping("/{name}")
    public ResponseEntity<CountryResponseDTO> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getCountryByName(name));
    }

    @GetMapping("/capital/{capital}")
    public ResponseEntity<List<CountryResponseDTO>> byCapital(@PathVariable String capital) {
        return ResponseEntity.ok(service.searchByCapital(capital));
    }

    @GetMapping("/population/{population}")
    public ResponseEntity<List<CountryResponseDTO>> byPopulation(@PathVariable Long population) {
        return ResponseEntity.ok(service.searchByPopulation(population));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CountryResponseDTO> create(
        @RequestPart("data") @Valid CountryRequestDTO dto,
        @RequestPart("flag") MultipartFile flag
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCountry(dto, flag));
    }
}

