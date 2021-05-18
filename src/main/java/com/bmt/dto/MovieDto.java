/**
 * 
 */
package com.bmt.dto;

import java.time.LocalDate;


import javax.validation.constraints.NotNull;

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
public class MovieDto {

	@NotNull(message = "Theater is Mandatory for Show")
	private TheaterDto theatre;
	
	private long id;

	private String name;

	private LocalDate releaseDate;
	
	private TheaterDto theaterDto;

}