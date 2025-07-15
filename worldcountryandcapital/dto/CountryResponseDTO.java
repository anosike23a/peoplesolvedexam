/**
 * 
 */
package com.examtest.worldcountryandcapital.dto;

/**
 * @author anosi
 *
 */

public record CountryResponseDTO(
    String name,
    String capital,
    Long population,
    String flagUrl
) {}

