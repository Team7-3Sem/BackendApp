package org.example.kinogris.Service;

import org.example.kinogris.Model.Showing;
import org.example.kinogris.Repository.ShowingRepository;
import org.springframework.stereotype.Service;

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
        return showingRepository.save(showing);
    }

    public Optional<Showing> updateShowing(int id, Showing showingDetails) {
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
        if (showingRepository.existsById(id)) {
            showingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
