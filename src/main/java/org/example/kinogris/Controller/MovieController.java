package org.example.kinogris.Controller;

import org.example.kinogris.Model.Movie;
import org.example.kinogris.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("/newmovie")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            Movie mov1 = movieRepository.save(movie);
            return new ResponseEntity<>(mov1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("updatemovie")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        Optional<Movie> mov = movieRepository.findById(movie.getMovieID());
        if (mov.isPresent()) {
            movieRepository.save(mov.get());
            return new ResponseEntity<>(mov.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable int id) {
        Optional<Movie> mov = movieRepository.findById(id);
        if (mov.isPresent()) {
            movieRepository.delete(mov.get());
            return new ResponseEntity<>(mov.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
