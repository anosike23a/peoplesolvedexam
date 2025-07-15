/**
 * 
 */
package com.examtest.worldcountryandcapital.exception;

/**
 * @author anosi
 *
 */
@SuppressWarnings("serial")
public class CountryNotFoundException extends RuntimeException {
	
    public CountryNotFoundException(String m) {
        super(m);
    } 
}
