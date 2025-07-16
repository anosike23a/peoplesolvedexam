/**
 * 
 */
package com.examtest.worldcountryandcapital.dto;

/**
 * @author anosi
 *
 */
public record CountryResponseDto(
	    Long id,
	    String name,
	    Long population,
	    String region,
	    String capital,
	    String imageUrl
	) {}
