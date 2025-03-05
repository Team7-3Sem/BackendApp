package org.example.kinogris.Repository;

import org.example.kinogris.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Set<Reservation> findById(int id);
}
