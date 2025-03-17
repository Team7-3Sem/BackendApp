package org.example.kinogris.Service;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Seat;
import org.example.kinogris.Model.SeatAvailabilityDTO;
import org.example.kinogris.Model.Showing;
import org.example.kinogris.Model.Theater;
import org.example.kinogris.Repository.ReservationRepository;
import org.example.kinogris.Repository.SeatRepository;
import org.example.kinogris.Repository.ShowingRepository;
import org.example.kinogris.Repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final ShowingRepository showingRepository;
    private final ReservationRepository reservationRepository;
    private final TheaterRepository theaterRepository;

    public SeatService(SeatRepository seatRepository, ReservationRepository reservationRepository, ShowingRepository showingRepository, TheaterRepository theaterRepository) {
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
        this.showingRepository = showingRepository;
        this.theaterRepository = theaterRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatById(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return seatRepository.findById(id);
    }

    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public Optional<Seat> updateSeat(int id, @Valid Seat seatDetails) {
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return seatRepository.findById(id).map(seat -> {
                seat.setSeatNumber(seatDetails.getSeatNumber());
                seat.setRowNumber(seatDetails.getRowNumber());
                return seatRepository.save(seat);
        });
    }

    public boolean deleteSeat(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        if (seatRepository.existsById(id)) {
            seatRepository.deleteById(id);
            return true;
        }
        return false;
    }
        public List<SeatAvailabilityDTO> getSeatAvailability(int showingID) {
        // Get the showing
        Showing showing = showingRepository.findById(showingID)
                .orElseThrow(() -> new RuntimeException("Showing not found"));

        // Get all seats for the theatre
        List<Seat> allSeats = seatRepository.findByTheater(showing.getTheater());

        // Get reserved seats for the showing
        List<Seat> reservedSeats = reservationRepository.findReservedSeatsByShowing(showingID);

        // Build seat availability response
        return allSeats.stream().map(seat ->
                new SeatAvailabilityDTO(
                        seat.getSeatId(),
                        seat.getRowNumber(),
                        seat.getSeatNumber(),
                        reservedSeats.contains(seat) // true if reserved, false otherwise
                )
        ).collect(Collectors.toList());
    }

    public String generateSeatsForTheatre(int theaterId, int rows, int seatsPerRow) {
        Theater theatre = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= rows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat();
                seat.setRowNumber(row);
                seat.setSeatNumber(seatNum);
                seat.setTheater(theatre);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
        return "Successfully added " + (rows * seatsPerRow) + " seats to Theatre " + theatre.getTheaterName();
    }
}
