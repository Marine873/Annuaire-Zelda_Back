package com.zelda.annuaire.repository;

import com.zelda.annuaire.model.Vetements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVetementsDao extends JpaRepository<Vetements, Integer> {
}
