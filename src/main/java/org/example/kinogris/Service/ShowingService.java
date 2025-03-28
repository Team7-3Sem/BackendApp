package org.example.kinogris.Service;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Showing;
import org.example.kinogris.Repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowingService {

    private final ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public List<Showing> getAllShowings() {
        return showingRepository.findAll();
    }

    public Optional<Showing> getShowingById(int id) {
        return showingRepository.findById(id);
    }

    public Showing saveShowing(Showing showing) {
        if(isConflict(showing)){
            throw new IllegalArgumentException("There is a conflict with this showing");
        }
        return showingRepository.save(showing);
    }

    public Optional<Showing> updateShowing(int id, @Valid Showing showingDetails) {
        if(showingDetails == null){
            throw new IllegalArgumentException("The showing details cannot be null");
        }
        return showingRepository.findById(id).map(showing -> {
            showing.setMovie(showingDetails.getMovie());
            showing.setTheater(showingDetails.getTheater());
            showing.setStartTime(showingDetails.getStartTime());
            showing.setEndTime(showingDetails.getEndTime());
            showing.setActive(showingDetails.isActive());
            return showingRepository.save(showing);
        });
    }

    public boolean deleteShowing(int id) {
        if(id <= 0 ){ //checker for en valid id
            throw new IllegalArgumentException("The showing details cannot be null");
        }
        if (showingRepository.existsById(id)) {
            showingRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean isConflict(Showing showing) {
        if(showing.getMovie() == null || showing.getEndTime() == null){
            throw new IllegalArgumentException("The showing details cannot be null");
        }
        List<Showing> existingShowings = showingRepository.findByStartTimeBetween(showing.getStartTime(), showing.getEndTime());
        return !existingShowings.isEmpty();
    }
}
