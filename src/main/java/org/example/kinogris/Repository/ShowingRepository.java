package org.example.kinogris.Repository;

import org.example.kinogris.Model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    Set<Showing> findByName(String name);
}
