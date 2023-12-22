package com.zelda.annuaire.service.interfaces;

import com.zelda.annuaire.model.Vetements;
import com.zelda.annuaire.service.exceptions.VetementsNotFoundException;

import java.util.List;

public interface IVetementsService {
    Vetements addArmure(Vetements armure);

    Vetements updateArmure(Vetements armure, int id) throws VetementsNotFoundException;

    void deleteArmureById(int id);

    List<Vetements> getAllArmures();

    Vetements getArmureById(int id) throws VetementsNotFoundException;
}
