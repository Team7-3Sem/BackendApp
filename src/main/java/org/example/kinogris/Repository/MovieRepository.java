package org.example.kinogris.Repository;

import org.example.kinogris.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Set<Movie> findByTitle(String title);
}
