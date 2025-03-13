package org.example.kinogris.Service;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Seat;
import org.example.kinogris.Repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
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
}
