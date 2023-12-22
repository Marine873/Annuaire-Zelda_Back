package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Ingredients;
import com.zelda.annuaire.repository.IIngredientsDao;
import com.zelda.annuaire.service.exceptions.IngredientsNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class IngredientsServiceTest {
    private IngredientsService ingredientsService;
    @Mock
    private IIngredientsDao ingredientsDao;

    @BeforeEach
    void setUp() {
        ingredientsService = new IngredientsService(ingredientsDao);
    }

    @Test
    void addIngredient() {
        Ingredients ingredient = Ingredients.builder().id(1).build();

        Mockito.when(ingredientsDao.save(any())).thenReturn(ingredient);

        Ingredients request = Ingredients.builder().nom("ingredient").build();

        Ingredients addIngredient = ingredientsService.addIngredient(request);

        assertEquals(addIngredient, ingredient);
    }

    @Test
    void updatedIngredient() throws IngredientsNotFoundException {
        String nom1 = "old";
        Ingredients ingredientExistant = Ingredients.builder().id(1).nom(nom1).build();
        String nom2 = "new";
        Ingredients ingredientNew = Ingredients.builder().id(1).nom(nom2).build();

        Mockito.when(ingredientsDao.findById(anyInt())).thenReturn(Optional.of(ingredientExistant));
        Mockito.when(ingredientsDao.save(Mockito.any(Ingredients.class))).thenReturn(ingredientNew);

        Ingredients ingredientUpdate = ingredientsService.updateIngredient(ingredientNew, 1);

        assertEquals(nom2, ingredientUpdate.getNom());
    }

    @Test
    void deletedIngredientById() {
        Mockito.doNothing().when(ingredientsDao).deleteById(1);

        ingredientsService.deleteIngredientById(1);

        Mockito.verify(ingredientsDao).deleteById(1);
    }

    @Test
    void getAllIngredients() {
        Ingredients ingredient1 = Ingredients.builder().nom("ingredient1Nom").build();
        Ingredients ingredient2 = Ingredients.builder().nom("ingredient2Nom").build();
        List<Ingredients> listIngredients = List.of(ingredient1, ingredient2);

        Mockito.when(ingredientsDao.findAll()).thenReturn(listIngredients);

        List<Ingredients> result = ingredientsService.getAllIngredients();

        assertEquals(2, result.size());
        assertTrue(result.get(0).getNom().contentEquals("ingredient1Nom"));
    }

    @Test
    void getIngredientById() {
        Ingredients ingredient = Ingredients.builder().id(1).build();
        Mockito.when(ingredientsDao.findById(1)).thenReturn(Optional.ofNullable(ingredient));

        Ingredients result;

        try {
            result = ingredientsService.getIngredientById(1);
        } catch (IngredientsNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, result.getId());
    }

    @Test
    void getIngredientByIdKO() {
        int idIngredient = 2;
        Mockito.when(ingredientsDao.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IngredientsNotFoundException.class, () -> ingredientsService.getIngredientById(idIngredient));
        String expectedMessage = "L'ingrédient " + idIngredient + " n'existe pas.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
