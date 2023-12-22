package com.zelda.annuaire.repository;

import com.zelda.annuaire.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientsDao extends JpaRepository<Ingredients, Integer> {
}
