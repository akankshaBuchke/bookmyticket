package com.bmt.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmt.dto.TheaterDto;
import com.bmt.service.TheaterService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	@GetMapping("{id}")
	public ResponseEntity<TheaterDto> getMoviesByTheaterId(@PathVariable(name = "id") @Min(value = 1, message = "Theater Id Cannot be -ve") long id) {

		log.info("Received Request to get theater details for Id: " + id);

		return ResponseEntity.ok(theaterService.getTheater(id));
	}
	
	
	@GetMapping("{id}/city/{city}")
	public ResponseEntity<TheaterDto> getMoviesByTheaterIdAndCity(@PathVariable(name = "id") @Min(value = 1, message = "Theater Id Cannot be -ve") long id, 
			@PathVariable(name = "city") String city) {

		log.info("Received Request to get movies for Tid: " + id + " city: "+city);

		return ResponseEntity.ok(theaterService.getTheaterByTidAndCity(id,city));
	}
	
	
	@GetMapping("city/{city}")
	public ResponseEntity<List<TheaterDto>> getMoviesByCity(
			@PathVariable(name = "city") String city) {

		log.info("Received Request to get movies for city: " + city);

		return ResponseEntity.ok(theaterService.getTheaterByCity(city));
	}
}