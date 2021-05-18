/**
 * 
 */
package com.bmt.service;

import java.util.List;

import javax.validation.constraints.Min;

import com.bmt.dto.TheaterDto;

public interface TheaterService {

	TheaterDto getTheater(long id);

	TheaterDto getTheaterByTidAndCity(@Min(value = 1, message = "Theater Id Cannot be -ve") long id, String city);

	List<TheaterDto> getTheaterByCity(String city);

}