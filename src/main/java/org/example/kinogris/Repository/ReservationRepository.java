package org.example.kinogris.Repository;

import org.example.kinogris.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(int id);

    @Query("SELECT COUNT(r) FROM Reservation r JOIN r.showing s WHERE s.movie.movieID = :movieId")
    int countReservationsByMovieId(@Param("movieId") int movieId);
}
