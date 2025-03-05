package org.example.kinogris.Service;

import org.example.kinogris.Model.Movie;
import org.example.kinogris.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> updateMovie(int id, Movie movieDetails) {
        return movieRepository.findById(id).map(movie -> {
            movie.setMovieTitle(movieDetails.getMovieTitle());
            movie.setGenre(movieDetails.getGenre());
            movie.setAgeRestriction(movieDetails.getAgeRestriction());
            movie.setDescription(movieDetails.getDescription());
            movie.setDuration(movieDetails.getDuration());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setEndDate(movieDetails.getEndDate());
            movie.setActive(movieDetails.isActive());
            return movieRepository.save(movie);
        });
    }

    public boolean deleteMovie(int id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
