package org.example.kinogris.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "theaters")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private int theaterId;

    @Column(name = "theater_name", nullable = false)
    @NotBlank(message = "Navn mangler")
    private String theaterName;

    @Column(name = "row_count", nullable = false)
    @NotNull(message = "Antal rækker mangler")
    private int rowCount;

    @Column(name = "seats_per_row", nullable = false)
    @NotNull(message = "Antal sæder per række mangler")
    private int seatsPerRow;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("theater")
    private List<Seat> seats;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("theater")
    private List<Showing> showings;

    protected Theater() {}

    public Theater(String theaterName, int rowCount, int seatsPerRow) {
        this.theaterName = theaterName;
        this.rowCount = rowCount;
        this.seatsPerRow = seatsPerRow;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void setShowings(List<Showing> showings) {
        this.showings = showings;
    }
}
