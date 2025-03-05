package org.example.kinogris.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int MovieID;
    private String movieTitle;
    private String genre;
    private int ageRestriction;
    private String description;
    private int duration;
    private Date releaseDate;
    private Date endDate;
    private boolean isActive;

    @OneToMany(mappedBy = "movie")
    private List<Showing> showings;

    public Movie(int movieID, String movieTitle, String genre, int ageRestriction, String description, int duration, Date releaseDate, Date endDate, boolean isActive, List<Showing> showings) {
        MovieID = movieID;
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.showings = showings;
    }

    protected Movie() {}

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void setShowings(List<Showing> showings) {
        this.showings = showings;
    }
}
