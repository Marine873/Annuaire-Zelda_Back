package com.zelda.annuaire.controller;

import com.zelda.annuaire.model.Arcs;
import com.zelda.annuaire.model.Boucliers;
import com.zelda.annuaire.repository.IBoucliersDao;
import com.zelda.annuaire.service.exceptions.ArcsNotFoundException;
import com.zelda.annuaire.service.exceptions.BoucliersNotFoundException;
import com.zelda.annuaire.service.interfaces.IBoucliersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/boucliers/")
public class BoucliersController {
    private final IBoucliersService boucliersService;

    public BoucliersController(IBoucliersService boucliersService) {
        this.boucliersService = boucliersService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boucliers> addBouclier(@RequestBody Boucliers boucliers) {
        boucliersService.addBouclier(boucliers);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Boucliers> updateBouclier(@PathVariable int id, @RequestBody Boucliers boucliers) throws BoucliersNotFoundException {
        Boucliers updatedBouclier;
        try {
            updatedBouclier = boucliersService.updateBouclier(boucliers, id);
        } catch (BoucliersNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(updatedBouclier);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletecBouclier(@PathVariable int id) {
        boucliersService.deleteBouclierById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Boucliers>> getAllBoucliers() {
        return ResponseEntity.ok(boucliersService.getAllBoucliers());
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boucliers> getBouclierById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(boucliersService.getBouclierById(id));
        } catch (BoucliersNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
