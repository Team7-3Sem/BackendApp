package org.example.kinogris.Controller;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Movie;
import org.example.kinogris.Service.MovieService;
import org.example.kinogris.Service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5502")
@RestController
@RequestMapping("")
public class MovieController {

    private final MovieService movieService;
    private final ReservationService reservationService;


    public MovieController(MovieService movieService, ReservationService reservationService) {
        this.movieService = movieService;
        this.reservationService = reservationService;
    }

    @GetMapping("/kinogrisen/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/kinogrisen/movies/{id}/reservation-count")
    public ResponseEntity<Integer> getMovieReservationCount(@PathVariable int movieID) {
        // Check if movie exists
        if (!movieService.getMovieById(movieID).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        int count = reservationService.countReservationsByMovieId(movieID);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/kinogrisen/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/kinogrisen/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PutMapping("/kinogrisen/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movieDetails) {
        return movieService.updateMovie(id, movieDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/kinogrisen/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
