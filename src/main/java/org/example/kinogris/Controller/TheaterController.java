package org.example.kinogris.Controller;

import org.example.kinogris.Model.Theater;
import org.example.kinogris.Service.TheaterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/kinogrisen/theatres")
    public List<Theater> getTheaters() {

        return null;
    }



}
