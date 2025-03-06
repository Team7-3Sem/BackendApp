package org.example.kinogris.Repository;

import org.example.kinogris.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Set<Reservation> findById(int id);
}
