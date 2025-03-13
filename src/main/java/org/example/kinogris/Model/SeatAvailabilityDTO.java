package org.example.kinogris.Model;

public class SeatAvailabilityDTO {
    private int seatId;
    private int row;
    private int number;
    private boolean reserved;

    public SeatAvailabilityDTO(int seatId, int row, int number, boolean reserved) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.reserved = reserved;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
