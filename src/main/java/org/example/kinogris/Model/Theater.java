package org.example.kinogris.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "theatres")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int theaterId;
    private String theaterName;
    private int rowCount;
    private int seatsPerRow;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    protected Theater() {}

    public Theater(String theaterName, int rowCount, int seatsPerRow) {
        this.theaterName = theaterName;
        this.rowCount = rowCount;
        this.seatsPerRow = seatsPerRow;

    }

}
