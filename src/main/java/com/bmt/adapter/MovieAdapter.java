package com.bmt.adapter;

import com.bmt.dto.MovieDto;
import com.bmt.model.MovieEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieAdapter {

	public static MovieEntity toEntity(MovieDto movieDto) {

		return MovieEntity.builder()
				.name(movieDto.getName())
				.releaseDate(movieDto.getReleaseDate())
				.build();

	}

	public static MovieDto toDto(MovieEntity movieEntity) {

		return MovieDto.builder()
				.id(movieEntity.getId())
				.name(movieEntity.getName())
				.releaseDate(movieEntity.getReleaseDate())
				.build();
	}

}