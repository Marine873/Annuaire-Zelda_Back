package com.zelda.annuaire.controller;

import com.zelda.annuaire.model.Armes;
import com.zelda.annuaire.service.exceptions.ArmesNotFoundException;
import com.zelda.annuaire.service.interfaces.IArmesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/armes/")
public class ArmesController {
    private final IArmesService armesService;

    public ArmesController(IArmesService armesService) {
        this.armesService = armesService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Armes> addArme(@RequestBody Armes armes) {
        armesService.addArme(armes);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Armes> updateArmes(@PathVariable int id, @RequestBody Armes armes) throws ArmesNotFoundException {
        Armes updatedArme;
        try {
            updatedArme = armesService.updateArme(armes, id);
        } catch (ArmesNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(updatedArme);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArme(@PathVariable int id) {
        armesService.deleteArmeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Armes>> getAllArmes() {
        return ResponseEntity.ok(armesService.getAllArmes());
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Armes> getArmeById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(armesService.getArmeById(id));
        } catch (ArmesNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
