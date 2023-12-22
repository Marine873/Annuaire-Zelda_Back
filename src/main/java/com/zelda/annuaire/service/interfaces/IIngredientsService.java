package com.zelda.annuaire.service.interfaces;

import com.zelda.annuaire.model.Ingredients;
import com.zelda.annuaire.service.exceptions.IngredientsNotFoundException;

import java.util.List;

public interface IIngredientsService {
    Ingredients addIngredient(Ingredients ingredient);

    Ingredients updateIngredient(Ingredients ingredient, int id) throws IngredientsNotFoundException;

    void deleteIngredientById(int id);

    List<Ingredients> getAllIngredients();

    Ingredients getIngredientById(int id) throws IngredientsNotFoundException;
}
