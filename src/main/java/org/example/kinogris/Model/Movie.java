package org.example.kinogris.Model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieID;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Titel mangler")
    @Size(max = 100, message = "Titel kan maksimalt være 100 karakterer")
    private String title;

    @Column(name = "genre")
    @NotBlank(message = "Genre mangler")
    private String genre;

    @Column(name = "age_restriction")
    @Min(value = 0, message = "Aldersgrænse skal minimum være 0")
    @Max(value = 21, message = "Aldersgrænse kan ikke være højere end 21")
    private int ageRestriction;

    @Column(name = "description")
    @Size(max = 500, message = "Beskrivelse kan ikke være højere end 500 karaktere")
    private String description;

    @Column(name = "duration")
    @Positive(message = "Varighed skal være et positivt tal")
    private int duration;

    @Column(name = "release_date")
    @NotNull(message = "Udgivelsesdato mangler")
    private Date releaseDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Showing> showings;

    protected Movie() {}

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
