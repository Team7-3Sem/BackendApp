package org.example.kinogris.Repository;

import org.example.kinogris.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Set<Movie> findByTitle(String title);
}
