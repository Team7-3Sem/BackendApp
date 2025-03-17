package org.example.kinogris.Service;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Seat;
import org.example.kinogris.Model.Theater;
import org.example.kinogris.Repository.TheaterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater createTheater(Theater theater) {
       if(theater == null) {
           throw new IllegalArgumentException("The theater must not be null");
       }
        return theaterRepository.save(theater);
    }

    public Set<Theater> getTheatres(){
        Set<Theater> theaters = new HashSet<>();
        theaters.addAll(theaterRepository.findAll());
        return theaters;
    }

    public ResponseEntity<Theater> getTheaterById(int theaterId){
        if(theaterId <= 0){
            throw new IllegalArgumentException("The theater id must not be null");
        }
        Optional<Theater> theater = theaterRepository.findById(theaterId);
        return theater.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     *
     * @param theaterId
     * @return
     */
    public List<Integer> getTheaterLayoutById(int theaterId){
        if(theaterId <= 0){
            throw new IllegalArgumentException("The theater id must not be null");
        }
        List<Integer> theaterLayout = new ArrayList<>();
        Theater theater = theaterRepository.findById(theaterId).get();
        //Optional<Theater> theater = theaterRepository.findById(theaterId);
        theaterLayout.add(theater.getRowCount());
        theaterLayout.add(theater.getSeatsPerRow());
        return theaterLayout;
    }

    public Optional<Theater>updateTheater(int id, @Valid Theater theater){
        if(id <= 0){
            throw new IllegalArgumentException("The id must not be null");
        }
        return theaterRepository.findById(id).map(Theater ->{
            Theater.setTheaterName(theater.getTheaterName());
            Theater.setSeatsPerRow(theater.getSeatsPerRow());
            Theater.setRowCount(theater.getRowCount());
            return theaterRepository.save(Theater);
        });
    }

    public List<Seat> displayTheaterSeats(Theater theater){
    List<Seat> seats = new ArrayList<>(theater.getSeats());
    for (Seat seat : seats) {
        System.out.println("SædeId: " + seat.getSeatId());
        System.out.println("Sædenummer:  " + seat.getSeatNumber());
        System.out.println("Rækkenummer: " + seat.getRowNumber());
    }
    return seats;
    }

    public boolean deleteTheater(int id){
        if (theaterRepository.existsById(id)) {
            theaterRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
