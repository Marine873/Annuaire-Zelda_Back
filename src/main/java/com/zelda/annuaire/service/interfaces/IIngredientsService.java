package com.zelda.annuaire.service.interfaces;

import com.zelda.annuaire.model.Ingredients;
import com.zelda.annuaire.service.exceptions.IngredientsNotFoundException;

import java.util.List;

public interface IIngredientsService {
    Ingredients addMateriel(Ingredients materiel);

    Ingredients updateMateriel(Ingredients materiel, int id) throws IngredientsNotFoundException;

    void deleteMaterielById(int id);

    List<Ingredients> getAllMateriaux();

    Ingredients getMaterielById(int id) throws IngredientsNotFoundException;
}
