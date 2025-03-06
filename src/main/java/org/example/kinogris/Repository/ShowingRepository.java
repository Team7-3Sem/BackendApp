package org.example.kinogris.Repository;

import org.example.kinogris.Model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    //Set<Showing> findByName(String name);
}
