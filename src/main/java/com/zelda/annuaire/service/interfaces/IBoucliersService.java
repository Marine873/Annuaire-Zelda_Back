package com.zelda.annuaire.service.interfaces;

import com.zelda.annuaire.model.Boucliers;
import com.zelda.annuaire.service.exceptions.BoucliersNotFoundException;

import java.util.List;

public interface IBoucliersService {
    Boucliers addBouclier(Boucliers bouclier);

    Boucliers updateBouclier(Boucliers bouclier, int id) throws BoucliersNotFoundException;

    void deleteBouclierById(int id);

    List<Boucliers> getAllBoucliers();

    Boucliers getBouclierById(int id) throws BoucliersNotFoundException;
}
