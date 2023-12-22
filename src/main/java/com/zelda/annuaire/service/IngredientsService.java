package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Ingredients;
import com.zelda.annuaire.repository.IIngredientsDao;
import com.zelda.annuaire.service.exceptions.IngredientsNotFoundException;
import com.zelda.annuaire.service.interfaces.IIngredientsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService implements IIngredientsService {
    private IIngredientsDao ingredientsDao;

    public IngredientsService(IIngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    /**
     * Ajouter un ingrédient
     */
    @Override
    public Ingredients addIngredient(Ingredients ingredient) {
        return ingredientsDao.save(ingredient);
    }

    /**
     * Modifier un ingrédient
     */
    @Override
    public Ingredients updateIngredient(Ingredients ingredient, int id) throws IngredientsNotFoundException {
        Ingredients ingredients = getIngredientById(id);
        ingredients.setJeux(ingredient.getJeux());
        ingredients.setNom(ingredient.getNom());
        return ingredientsDao.save(ingredients);
    }

    /**
     * Supprimer un ingrédient
     */
    @Override
    public void deleteIngredientById(int id) {
        ingredientsDao.deleteById(id);
    }

    /**
     * Afficher tous les ingrédients
     */
    @Override
    public List<Ingredients> getAllIngredients() {
        return ingredientsDao.findAll();
    }

    /**
     * Afficher un ingrédient
     */
    @Override
    public Ingredients getIngredientById(int id) throws IngredientsNotFoundException {
        return ingredientsDao.findById(id).orElseThrow(() -> new IngredientsNotFoundException(
                "L'ingrédient " + id + " n'existe pas."
        ));
    }
}
