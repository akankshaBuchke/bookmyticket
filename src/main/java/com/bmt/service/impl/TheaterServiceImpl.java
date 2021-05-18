/**
 * 
 */
package com.bmt.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmt.adapter.TheaterAdapter;
import com.bmt.dto.TheaterDto;
import com.bmt.model.TheaterEntity;
import com.bmt.repository.TheaterRepository;
import com.bmt.service.TheaterService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	@Override
	public TheaterDto getTheater(long id) {
		log.info("Searching Theater by id: " + id);

		Optional<TheaterEntity> theaterEntity = theaterRepository.findById(id);

		if (!theaterEntity.isPresent()) {
			log.error("Theater not found for id: " + id);
			throw new EntityNotFoundException("Theater Not Found with ID: " + id);
		}

		return TheaterAdapter.toDto(theaterEntity.get());
	}

	@Override
	public TheaterDto getTheaterByTidAndCity(@Min(value = 1, message = "Theater Id Cannot be -ve") long id, String city) {
		// TODO Auto-generated method stub
		log.info("Searching Theater by id: " + id);

		Optional<TheaterEntity> theaterEntity = theaterRepository.findByIdAndCity(id,city);

		if (!theaterEntity.isPresent()) {
			log.error("Movie not found for id: " + id + "city"+ city);
			throw new EntityNotFoundException("No movie found for given combination of ID: " + id +" City: "+ city);
		}

		return TheaterAdapter.toDto(theaterEntity.get());
	}

	@Override
	public List<TheaterDto> getTheaterByCity(String city) {
		// TODO Auto-generated method stub
		log.info("Searching Theater by city: " + city);

		Optional<List<TheaterEntity>> theaterEntity = theaterRepository.findByCity(city);

		if (!theaterEntity.isPresent()) {
			log.error("Movie not found for city: "+ city);
			throw new EntityNotFoundException("No movie found for City: "+ city);
		}

		return TheaterAdapter.toDtoList(theaterEntity.get());
	}


}