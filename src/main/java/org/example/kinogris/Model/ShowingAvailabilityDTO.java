package org.example.kinogris.Model;

import java.util.List;

public class ShowingAvailabilityDTO {
    private String movieTitle;
    private String movieDescription;
    private List<SeatAvailabilityDTO> seats;

    // ✅ Constructor
    public ShowingAvailabilityDTO(String movieTitle, String movieDescription, List<SeatAvailabilityDTO> seats) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.seats = seats;
    }

    // ✅ Getters
    public String getMovieTitle() { return movieTitle; }
    public String getMovieDescription() { return movieDescription; }
    public List<SeatAvailabilityDTO> getSeats() { return seats; }
}
