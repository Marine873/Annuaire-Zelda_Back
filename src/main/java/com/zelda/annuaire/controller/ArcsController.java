package com.zelda.annuaire.controller;

import com.zelda.annuaire.model.Arcs;
import com.zelda.annuaire.service.exceptions.ArcsNotFoundException;
import com.zelda.annuaire.service.interfaces.IArcsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/arcs/")
public class ArcsController {
    private final IArcsService arcsService;

    public ArcsController(IArcsService arcsService) {
        this.arcsService = arcsService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Arcs> addArc(@RequestBody Arcs arcs) {
        arcsService.addArc(arcs);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Arcs> updateArc(@PathVariable int id, @RequestBody Arcs arcs) throws ArcsNotFoundException {
        Arcs updatedArc;
        try {
            updatedArc = arcsService.updateArc(arcs, id);
        } catch (ArcsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(updatedArc);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArc(@PathVariable int id) {
        arcsService.deleteArcById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Arcs>> getAllArcs() {
        return ResponseEntity.ok(arcsService.getAllArcs());
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Arcs> getArcById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(arcsService.getArcById(id));
        } catch (ArcsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
