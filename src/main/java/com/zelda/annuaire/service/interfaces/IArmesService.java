package com.zelda.annuaire.service.interfaces;

import com.zelda.annuaire.model.Armes;
import com.zelda.annuaire.service.exceptions.ArmesNotFoundException;

import java.util.List;

public interface IArmesService {
    Armes addArme(Armes arme);

    Armes updateArme(Armes arme, int id) throws ArmesNotFoundException;

    void deleteArmeById(int id);

    List<Armes> getAllArmes();

    Armes getArmeById(int id) throws ArmesNotFoundException;
}
