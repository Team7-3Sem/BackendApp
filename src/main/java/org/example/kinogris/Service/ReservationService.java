package org.example.kinogris.Service;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Reservation;
import org.example.kinogris.Model.ReservationRequest;
import org.example.kinogris.Model.Seat;
import org.example.kinogris.Model.Showing;
import org.example.kinogris.Repository.ReservationRepository;
import org.example.kinogris.Repository.SeatRepository;
import org.example.kinogris.Repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ShowingRepository showingRepository;
    private final SeatRepository seatRepository;

    public ReservationService(ReservationRepository reservationRepository, ShowingRepository showingRepository, SeatRepository seatRepository) {
        this.reservationRepository = reservationRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public int countReservationsByMovieId(int movieID) {
        return reservationRepository.countReservationsByMovieId(movieID);
    }

    public Optional<Reservation> getReservationById(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return reservationRepository.findById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public  Optional<Reservation> updateReservationById(int id, @Valid Reservation reservationDetails) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setReservationId(reservationDetails.getReservationId());
            reservation.setShowing(reservationDetails.getShowing());
            reservation.setCustomerName(reservationDetails.getCustomerName());
            reservation.setCustomerPhone(reservationDetails.getCustomerPhone());
            reservation.setCustomerEmail(reservationDetails.getCustomerEmail());
            reservation.setReservationTime(reservationDetails.getReservationTime());
            reservation.setReservationDate(reservationDetails.getReservationDate());
            reservation.setPaid(reservationDetails.getIsPaid());
            return reservationRepository.save(reservation);
        });
    }

        public String reserveSeats (ReservationRequest request){
            // Fetch the showing
            Showing showing = showingRepository.findById(request.getShowingId())
                    .orElseThrow(() -> new RuntimeException("Showing not found"));

            // Fetch the selected seats
            List<Seat> seats = seatRepository.findAllById(request.getSeatIds());

            // Create the reservation
            Reservation reservation = new Reservation();
            reservation.setShowing(showing);
            reservation.setCustomerName(request.getCustomerName());
            reservation.setCustomerPhone(request.getCustomerPhone());
            reservation.setCustomerEmail(request.getCustomerEmail());
            reservation.setReservationTime(new Date());
            reservation.setReservationDate(new Date()); // Assuming it's for today
            reservation.setPaid(false);
            reservation.setSeats(seats);

            // Save the reservation
            reservationRepository.save(reservation);

            return "Seats successfully reserved!";
        }


        public boolean deleteReservation ( int id){
            if (id <= 0) {
                throw new IllegalArgumentException("Id must be greater than 0");
            }
            if (reservationRepository.existsById(id)) {
                reservationRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }



