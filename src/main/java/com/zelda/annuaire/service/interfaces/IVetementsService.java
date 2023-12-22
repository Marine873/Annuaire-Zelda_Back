package com.zelda.annuaire.service.interfaces;

import com.zelda.annuaire.model.Vetements;
import com.zelda.annuaire.service.exceptions.VetementsNotFoundException;

import java.util.List;

public interface IVetementsService {
    Vetements addVetement(Vetements vetement);

    Vetements updateVetement(Vetements vetement, int id) throws VetementsNotFoundException;

    void deleteVetementById(int id);

    List<Vetements> getAllVetements();

    Vetements getVetementById(int id) throws VetementsNotFoundException;
}
