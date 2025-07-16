/**
 * 
 */
package com.examtest.worldcountryandcapital.dto;

/**
 * @author anosi
 *
 */
import org.springframework.web.multipart.MultipartFile;

public record CountryUpdateDto(
    String name,
    Long population,
    String region,
    String capital,
    MultipartFile image
) {}
