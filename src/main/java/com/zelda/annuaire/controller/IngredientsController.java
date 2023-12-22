package com.zelda.annuaire.controller;

import com.zelda.annuaire.model.Ingredients;
import com.zelda.annuaire.service.exceptions.IngredientsNotFoundException;
import com.zelda.annuaire.service.interfaces.IIngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/ingredients/")
public class IngredientsController {
    private final IIngredientsService ingredientsService;

    public IngredientsController(IIngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredients> addIngredient(@RequestBody Ingredients ingredients) {
        ingredientsService.addIngredient(ingredients);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Ingredients> updateIngredient(@PathVariable int id, @RequestBody Ingredients ingredients) throws IngredientsNotFoundException {
        Ingredients updatedIngredient;
        try {
            updatedIngredient = ingredientsService.updateIngredient(ingredients, id);
        } catch (IngredientsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        ingredientsService.deleteIngredientById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ingredients>> getAllIngredients() {
        return ResponseEntity.ok(ingredientsService.getAllIngredients());
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredients> getIngredientById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(ingredientsService.getIngredientById(id));
        } catch (IngredientsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
