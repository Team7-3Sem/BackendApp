package org.example.kinogris.Controller;


import jakarta.validation.Valid;
import org.example.kinogris.Model.Reservation;
import org.example.kinogris.Service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5502")
@RestController
@RequestMapping("")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/kinogrisen/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id){
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/kinogrisen/reservations")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }
    
    @PostMapping("/kinogrisen/reservations")
    public ResponseEntity<String> reserveSeats(@RequestBody ReservationRequest request) {
        String message = reservationService.reserveSeats(request);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/kinogrisen/reservations/{id}")
    public ResponseEntity<Reservation> updateReservationById(@PathVariable int id, @RequestBody Reservation reservationDetails){
        return reservationService.updateReservationById(id, reservationDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/kinogrisen/reservations/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable int id){
        if (reservationService.deleteReservation(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

