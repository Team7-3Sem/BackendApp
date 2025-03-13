package org.example.kinogris.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int seatId;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)  // FK to Theater
    private Theater theater;

    @Column(name = "seat_number", nullable = false)
    @NotNull(message = "Nummer på sæde mangler")
    private int seatNumber;

    @Column(name = "seat_row", nullable = false)
    @NotNull(message = "Rækkenummer mangler")
    private int rowNumber;

    protected Seat() {}

    public Seat(Theater theater, int seatNumber, int rowNumber) {
        this.theater = theater;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Theater getTheater() {
    return theater;
    }
}
