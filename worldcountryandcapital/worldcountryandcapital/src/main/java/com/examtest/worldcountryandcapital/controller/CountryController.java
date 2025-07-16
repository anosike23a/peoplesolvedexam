/**
 * 
 */
package com.examtest.worldcountryandcapital.controller;

/**
 * @author anosi
 *
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examtest.worldcountryandcapital.dto.CountryCreateDto;
import com.examtest.worldcountryandcapital.dto.CountryResponseDto;
import com.examtest.worldcountryandcapital.dto.CountryUpdateDto;
import com.examtest.worldcountryandcapital.service.impl.CountryService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Countries", description = "API for managing countries and their capitals")
public class CountryController {
	
	
	@Autowired
    private final CountryService countryService;

    @GetMapping
    @Operation(summary = "Get all countries")
    public ResponseEntity<List<CountryResponseDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get country by ID")
    @ApiResponse(responseCode = "404", description = "Country not found")
    public ResponseEntity<CountryResponseDto> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @GetMapping("/region/{region}")
    @Operation(summary = "Get countries by region")
    public ResponseEntity<List<CountryResponseDto>> getCountriesByRegion(@PathVariable String region) {
        return ResponseEntity.ok(countryService.getCountriesByRegion(region));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a new country")
    @ApiResponse(responseCode = "201", description = "Country created successfully")
    @ApiResponse(responseCode = "409", description = "Country with this name already exists")
    public ResponseEntity<CountryResponseDto> createCountry(
            @Valid @ModelAttribute CountryCreateDto dto) throws IOException {
        CountryResponseDto createdCountry = countryService.createCountry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update a country")
    @ApiResponse(responseCode = "404", description = "Country not found")
    public ResponseEntity<CountryResponseDto> updateCountry(
            @PathVariable Long id,
            @Valid @ModelAttribute CountryUpdateDto dto) throws IOException {
        return ResponseEntity.ok(countryService.updateCountry(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a country")
    @ApiResponse(responseCode = "204", description = "Country deleted successfully")
    @ApiResponse(responseCode = "404", description = "Country not found")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/images/{filename:.+}")
    @Operation(summary = "Get country image")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        // Implementation to serve images would go here
        // This would use FileStorageService to read the file and return it
        return ResponseEntity.notFound().build();
    }
}
