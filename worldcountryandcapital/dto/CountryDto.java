/**
 * 
 */
package com.examtest.worldcountryandcapital.dto;

/**
 * @author anosi
 *
 */
public class CountryDto {

	public record CountryRequestDTO(String name, Long population, String capital) {}

	public record CountryResponseDTO(String name, String capital, Long population, String flagUrl) {}

}
