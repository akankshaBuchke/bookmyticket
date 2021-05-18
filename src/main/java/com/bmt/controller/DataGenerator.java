package com.bmt.controller;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmt.enums.SeatType;
import com.bmt.model.MovieEntity;
import com.bmt.model.ShowEntity;
import com.bmt.model.ShowSeatsEntity;
import com.bmt.model.TheaterEntity;
import com.bmt.model.TheaterSeatsEntity;
import com.bmt.model.UserEntity;
import com.bmt.repository.MovieRepository;
import com.bmt.repository.ShowRepository;
import com.bmt.repository.ShowSeatsRepository;
import com.bmt.repository.TheaterRepository;
import com.bmt.repository.TheaterSeatsRepository;
import com.bmt.repository.TicketRepository;
import com.bmt.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("initialize")
public class DataGenerator {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showsRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private ShowSeatsRepository showSeatsRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;

	@GetMapping("generate")
	public ResponseEntity<String> generate() {

		log.info("Deleting Data from Shows, Movies and Theaters");

		showSeatsRepository.deleteAllInBatch();		
		ticketRepository.deleteAllInBatch();
		theaterSeatsRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();

		showsRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();
		theaterRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();

		log.info("Adding Starting Shows");

		UserEntity userEntity = UserEntity.builder().name("User1").build();
		userRepository.save(userEntity);

		TheaterEntity th1city1 = getTheater("Theater1","address1", "city1");
		TheaterEntity th1city2 = getTheater("Theater1", "address2", "city2");
		TheaterEntity th2city1 = getTheater("Theater2", "address3", "city1");

		MovieEntity movie1 = getMovie("Movie1", LocalDate.now(),th2city1);
		MovieEntity movie2 = getMovie("Movie2", LocalDate.now().minusDays(1),th1city2);

		List<ShowEntity> showEntities = new ArrayList<>();

		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.0f, th1city1, movie1));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.1f, th1city2, movie1));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.0f, th1city1, movie1));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.4f, th1city2, movie1));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.2f, th2city1, movie1));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON.plusHours(1), 1.5f, th2city1, movie2));

		showsRepository.saveAll(showEntities);

		log.debug("Added " + showEntities.size() + " Shows");

		return ResponseEntity.ok(showEntities.size() + " Shows Added");
	}

	private ShowEntity getShow(LocalDate showDate, LocalTime showTime, float multiplier, TheaterEntity theaterEntity, MovieEntity movieEntity) {

		ShowEntity showEntity =
				ShowEntity.builder()
						.showDate(showDate)
						.showTime(showTime)
						.rateMultiplier(multiplier)
						.theater(theaterEntity)
						.movie(movieEntity)
						.build();

		theaterEntity.getShows().add(showEntity);
		movieEntity.getShows().add(showEntity);
		showEntity.setSeats(generateShowSeats(theaterEntity.getSeats(), showEntity));

		for (ShowSeatsEntity seatsEntity : showEntity.getSeats()) {
			seatsEntity.setShow(showEntity);
		}

		return showEntity;

	}

	private List<ShowSeatsEntity> generateShowSeats(List<TheaterSeatsEntity> theaterSeatsEntities, ShowEntity showEntity) {

		List<ShowSeatsEntity> showSeatsEntities = new ArrayList<>();

		for (TheaterSeatsEntity theaterSeatsEntity : theaterSeatsEntities) {

			ShowSeatsEntity showSeatsEntity =
					ShowSeatsEntity.builder()
							.seatNumber(theaterSeatsEntity.getSeatNumber())
							.seatType(theaterSeatsEntity.getSeatType())
							.rate((int) (theaterSeatsEntity.getRate() * showEntity.getRateMultiplier()))
							.build();

			showSeatsEntities.add(showSeatsEntity);
		}

		return showSeatsRepository.saveAll(showSeatsEntities);
	}

	private TheaterEntity getTheater(String name, String address, String city) {

		TheaterEntity theaterEntity =
				TheaterEntity.builder()
						.name(name)
						.city(city)
						.address(address)
						.build();

		theaterEntity.getSeats().addAll(getTheaterSeats());

		for (TheaterSeatsEntity seatsEntity : theaterEntity.getSeats()) {
			seatsEntity.setTheater(theaterEntity);
		}

		theaterEntity = theaterRepository.save(theaterEntity);

		return theaterEntity;
	}

	private List<TheaterSeatsEntity> getTheaterSeats() {

		List<TheaterSeatsEntity> seats = new ArrayList<>();

		seats.add(getTheaterSeat("1A", SeatType.GOLD, 200));
		seats.add(getTheaterSeat("1B", SeatType.GOLD, 200));
		seats.add(getTheaterSeat("1C", SeatType.GOLD, 200));
		seats.add(getTheaterSeat("1D", SeatType.GOLD, 200));
		seats.add(getTheaterSeat("1E", SeatType.GOLD, 200));

		seats.add(getTheaterSeat("2A", SeatType.SILVER, 250));
		seats.add(getTheaterSeat("2B", SeatType.SILVER, 250));
		seats.add(getTheaterSeat("2C", SeatType.SILVER, 250));
		seats.add(getTheaterSeat("2D", SeatType.SILVER, 250));
		seats.add(getTheaterSeat("2E", SeatType.SILVER, 250));
		
		seats.add(getTheaterSeat("3A", SeatType.PLATINUM, 300));
		seats.add(getTheaterSeat("3B", SeatType.PLATINUM, 300));
		seats.add(getTheaterSeat("3C", SeatType.PLATINUM, 300));
		seats.add(getTheaterSeat("3D", SeatType.PLATINUM, 300));
		seats.add(getTheaterSeat("3E", SeatType.PLATINUM, 300));
		seats = theaterSeatsRepository.saveAll(seats);

		return seats;
	}

	private TheaterSeatsEntity getTheaterSeat(String seatNumber, SeatType seatType, int rate) {
		return TheaterSeatsEntity.builder().seatNumber(seatNumber).seatType(seatType).rate(rate).build();
	}

	private MovieEntity getMovie(String name, LocalDate releaseDate,TheaterEntity theaterEntity) {
		MovieEntity movieEntity =
				MovieEntity.builder()
						.name(name)
						.releaseDate(releaseDate)
						.theater(theaterEntity)
						.build();

		movieEntity = movieRepository.save(movieEntity);

		return movieEntity;
	}
}