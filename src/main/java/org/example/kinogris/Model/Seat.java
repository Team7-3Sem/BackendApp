package org.example.kinogris.Model;

import jakarta.persistence.*;

@Entity
@Table (name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    private int seatNumber;
    private int rowNumber;


    protected Seat() {}

    public Seat(Theater theater, int seatNumber, int rowNumber) {
        this.theater = theater;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatId() {
        return seatId;
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
}
