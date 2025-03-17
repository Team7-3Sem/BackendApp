package org.example.kinogris.Repository;

import org.example.kinogris.Model.Reservation;
import org.example.kinogris.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r.seats FROM Reservation r WHERE r.showing.showingID = :showingId")
    List<Seat> findReservedSeatsByShowing(@Param("showingId") int showingId);
    Optional<Reservation> findById(int id);

    @Query("SELECT COUNT(r) FROM Reservation r JOIN r.showing s WHERE s.movie.movieID = :movieId")
    int countReservationsByMovieId(@Param("movieId") int movieId);
}
