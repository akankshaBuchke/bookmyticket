/**
 * 
 */
package com.bmt.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "movies")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "release_date", columnDefinition = "DATE", nullable = false)
	private LocalDate releaseDate;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	@JsonIgnore
	@Builder.Default
	private List<ShowEntity> shows = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private TheaterEntity theater;
}