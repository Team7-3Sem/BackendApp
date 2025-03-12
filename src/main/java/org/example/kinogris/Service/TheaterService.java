package org.example.kinogris.Service;

import jakarta.validation.Valid;
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
       return theaterRepository.save(theater);
    }

    public Set<Theater> getTheatres(){
        Set<Theater> theaters = new HashSet<>();
        theaters.addAll(theaterRepository.findAll());
        return theaters;
    }

    public ResponseEntity<Theater> getTheaterById(int theaterId){
        Optional<Theater> theater = theaterRepository.findById(theaterId);
        return theater.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     *
     * @param theaterId
     * @return
     */
    public List<Integer> getTheaterLayoutById(int theaterId){
        List<Integer> theaterLayout = new ArrayList<>();
        Theater theater = theaterRepository.findById(theaterId).get();
        //Optional<Theater> theater = theaterRepository.findById(theaterId);
        theaterLayout.add(theater.getRowCount());
        theaterLayout.add(theater.getSeatsPerRow());
        return theaterLayout;
    }

    public Optional<Theater>updateTheater(int id, @Valid Theater theater){
        return theaterRepository.findById(id).map(Theater ->{
            Theater.setTheaterName(theater.getTheaterName());
            Theater.setSeatsPerRow(theater.getSeatsPerRow());
            Theater.setRowCount(theater.getRowCount());
            return theaterRepository.save(Theater);
        });
    }

    public boolean deleteTheater(int id){
        if (theaterRepository.existsById(id)) {
            theaterRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
