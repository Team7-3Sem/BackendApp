package org.example.kinogris.Service;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Reservation;
import org.example.kinogris.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
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
        if(id <= 0){
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
            reservation.setIsPaid(reservationDetails.getIsPaid());
            return reservationRepository.save(reservation);
        });

    }

    public boolean deleteReservation(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

