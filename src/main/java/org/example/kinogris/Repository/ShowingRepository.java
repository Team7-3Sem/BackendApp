package org.example.kinogris.Repository;

import jakarta.validation.constraints.NotNull;
import org.example.kinogris.Model.Showing;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    List<Showing> findByStartTimeBetween(@NotNull(message = "Starttid mangler") Date startTimeAfter, @NotNull(message = "Starttid mangler") Date startTimeBefore, Sort sort, Limit limit);

    List<Showing> findByStartTimeBetween(Date startTime, Date endTime);
    //Set<Showing> findByName(String name);
}
