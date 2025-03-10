package org.example.kinogris.Controller;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Showing;
import org.example.kinogris.Service.ShowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ShowingController {

    private final ShowingService showingService;

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @GetMapping("/kinogrisen/showings")
    public List<Showing> getAllShowings() {
        return showingService.getAllShowings();
    }

    @GetMapping("/kinogrisen/showings/{id}")
    public ResponseEntity<Showing> getShowingById(@PathVariable int id) {
        return showingService.getShowingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/kinogrisen/showings")
    public Showing createShowing(@Valid @RequestBody Showing showing) {
        return showingService.saveShowing(showing);
    }

    @PutMapping("/kinogrisen/showings/{id}")
    public ResponseEntity<Showing> updateShowing(@PathVariable int id, @RequestBody Showing showingDetails) {
        return showingService.updateShowing(id, showingDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/kinogrisen/showings/{id}")
    public ResponseEntity<Void> deleteShowing(@PathVariable int id) {
        if (showingService.deleteShowing(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
