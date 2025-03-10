package org.example.kinogris.Controller;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Theater;
import org.example.kinogris.Service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/kinogrisen/theatres")
    public Set<Theater> getTheaters() {
        return theaterService.getTheatres();
    }

    @GetMapping("/kinogrisen/theatres/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable int id) {
        return theaterService.getTheaterById(id);
    }

    @GetMapping("/kinogrisen/theatres/{id}/layout")
    public List<Integer> getTheaterLayoutById(@PathVariable int id) {
        return theaterService.getTheaterLayoutById(id);
    }

    @PostMapping("/kinogrisen/theatres")
    public Theater createTheater(@Valid @RequestBody Theater theater) {
        return theaterService.createTheater(theater);
    }

   // @GetMapping("/kinogrisen/theatres/statistics")
    @PutMapping("kinogrisen/theatres/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable int id, Theater theater) {
    return null;
    }
}
