package com.zelda.annuaire.service.interfaces;

import com.zelda.annuaire.model.Arcs;
import com.zelda.annuaire.service.exceptions.ArcsNotFoundException;

import java.util.List;

public interface IArcsService {
    Arcs addArc(Arcs arc);

    Arcs updateArc(Arcs arc, int id) throws ArcsNotFoundException;

    void deleteArcById(int id);

    List<Arcs> getAllArcs();

    Arcs getArcById(int id) throws ArcsNotFoundException;
}
