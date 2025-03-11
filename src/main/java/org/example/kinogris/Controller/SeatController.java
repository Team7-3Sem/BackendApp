package org.example.kinogris.Controller;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Seat;
import org.example.kinogris.Service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5502")
@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/kinogrisen/seats")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/kinogrisen/seats/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable int id) {
        return seatService.getSeatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/kinogrisen/seats")
    public Seat createSeat(@Valid @RequestBody Seat seat) {
        return seatService.saveSeat(seat);
    }

    @PutMapping("/kinogrisen/seats/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable int id, @RequestBody Seat seatDetails) {
        return seatService.updateSeat(id, seatDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/kinogrisen/seats/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable int id) {
        if (seatService.deleteSeat(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
