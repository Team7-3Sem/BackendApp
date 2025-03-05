package org.example.kinogris.Controller;

import org.example.kinogris.Model.Showing;
import org.example.kinogris.Service.ShowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showings")
public class ShowingController {

    private final ShowingService showingService;

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @GetMapping
    public List<Showing> getAllShowings() {
        return showingService.getAllShowings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showing> getShowingById(@PathVariable int id) {
        return showingService.getShowingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Showing createShowing(@RequestBody Showing showing) {
        return showingService.saveShowing(showing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Showing> updateShowing(@PathVariable int id, @RequestBody Showing showingDetails) {
        return showingService.updateShowing(id, showingDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowing(@PathVariable int id) {
        if (showingService.deleteShowing(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
