package com.bmt.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bmt.dto.TheaterDto;
import com.bmt.model.TheaterEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TheaterAdapter {

	public static TheaterEntity toEntity(TheaterDto theaterDto) {

		return TheaterEntity.builder()
				.name(theaterDto.getName())
				.city(theaterDto.getCity())
				.address(theaterDto.getAddress())
				.shows(theaterDto.getShows())
				.movies(theaterDto.getMovies())
				.build();
	}

	public static TheaterDto toDto(TheaterEntity theaterEntity) {

		return TheaterDto.builder()
				.id(theaterEntity.getId())
				.name(theaterEntity.getName())
				.city(theaterEntity.getCity())
				.address(theaterEntity.getAddress())
				.shows(theaterEntity.getShows())
				.movies(theaterEntity.getMovies())
				.build();
	}

	public static List<TheaterDto> toDtoList(List<TheaterEntity> theaterEntityList) {
		// TODO Auto-generated method stub
		List<TheaterDto> theaterDto= new ArrayList<>();
		for(TheaterEntity theaterEntity:theaterEntityList)
		{
			TheaterDto theaterDto1 =
					TheaterDto.builder()
			.id(theaterEntity.getId())
			.name(theaterEntity.getName())
			.city(theaterEntity.getCity())
			.address(theaterEntity.getAddress())
			.shows(theaterEntity.getShows())
			.movies(theaterEntity.getMovies())
			.build();
			if(theaterDto1.getMovies().isEmpty()==false)
			theaterDto.add(theaterDto1);
		}
		return theaterDto;

	}

}