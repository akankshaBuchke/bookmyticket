/**
 * 
 */
package com.bmt.dto;

import java.util.List;

import com.bmt.model.MovieEntity;
import com.bmt.model.ShowEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TheaterDto {

	private long id;

	private String name;

	private String city;

	private String address;

	private List<ShowEntity> shows;
	
	private List<MovieEntity> movies;
}