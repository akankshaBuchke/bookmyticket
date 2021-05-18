package com.bmt.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bmt.model.TheaterEntity;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Long>, JpaSpecificationExecutor<TheaterEntity> {

	@Query("from TheaterEntity u WHERE u.id=:id AND u.city=:city")
	Optional<TheaterEntity> findByIdAndCity(@Param("id") @Min(value = 1, message = "Theater Id Cannot be -ve") long id, @Param("city") String city);

	Optional<List<TheaterEntity>> findByCity(String city);

}