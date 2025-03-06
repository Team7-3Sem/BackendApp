package org.example.kinogris.Repository;

import org.example.kinogris.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Set<Seat> findBySeatId(int seatId);
}
