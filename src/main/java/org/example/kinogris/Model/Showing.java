package org.example.kinogris.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "showing")
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ShowingID;

    @ManyToOne
    @JoinColumn(name = "MovieID", referencedColumnName = "MovieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "TheaterID", referencedColumnName = "TheaterID")
    private Theater theater;

    private Date StartTime;
    private Date EndTime;
    private boolean isActive;

    public Showing(int showingID, Movie movie, Theater theater, Date startTime, Date endTime, boolean isActive) {
        ShowingID = showingID;
        this.movie = movie;
        this.theater = theater;
        StartTime = startTime;
        EndTime = endTime;
        this.isActive = isActive;
    }

    public Showing() {}

    public int getShowingID() {
        return ShowingID;
    }

    public void setShowingID(int showingID) {
        ShowingID = showingID;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
