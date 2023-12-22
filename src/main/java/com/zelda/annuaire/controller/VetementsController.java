package com.zelda.annuaire.controller;

import com.zelda.annuaire.model.Vetements;
import com.zelda.annuaire.service.exceptions.VetementsNotFoundException;
import com.zelda.annuaire.service.interfaces.IVetementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/vetements/")
public class VetementsController {
    private final IVetementsService vetementsService;

    public VetementsController(IVetementsService vetementsService) {
        this.vetementsService = vetementsService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vetements> addVetement(@RequestBody Vetements vetements) {
        vetementsService.addVetement(vetements);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Vetements> updateVetement(@PathVariable int id, @RequestBody Vetements vetements) throws VetementsNotFoundException {
        Vetements updatedVetement;
        try {
            updatedVetement = vetementsService.updateVetement(vetements, id);
        } catch (VetementsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(updatedVetement);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVetement(@PathVariable int id) {
        vetementsService.deleteVetementById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vetements>> getAllVetements() {
        return ResponseEntity.ok(vetementsService.getAllVetements());
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vetements> getVetementById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(vetementsService.getVetementById(id));
        } catch (VetementsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
