package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Arcs;
import com.zelda.annuaire.repository.IArcsDao;
import com.zelda.annuaire.service.exceptions.ArcsNotFoundException;
import com.zelda.annuaire.service.interfaces.IArcsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArcsService implements IArcsService {
    private IArcsDao arcsDao;

    public ArcsService(IArcsDao arcsDao) {
        this.arcsDao = arcsDao;
    }

    /**
     * Ajouter un arc
     */
    @Override
    public Arcs addArc(Arcs arc) {
        return arcsDao.save(arc);
    }

    /**
     * Modifier un arc
     */
    @Override
    public Arcs updateArc(Arcs arc, int id) throws ArcsNotFoundException {
        Arcs arcs = getArcById(id);
        arcs.setJeux(arc.getJeux());
        arcs.setNom(arc.getNom());
        arcs.setAttaque(arc.getAttaque());
        arcs.setDurabilite(arc.getDurabilite());
        arcs.setPortee(arc.getPortee());
        arcs.setMultifleches(arc.getMultifleches());
        arcs.setVisee(arc.getVisee());
        arcs.setDisponibilite(arc.getDisponibilite());
        return arcsDao.save(arcs);
    }

    /**
     * Supprimer un arc
     */
    @Override
    public void deleteArcById(int id) {
        arcsDao.deleteById(id);
    }

    /**
     * Afficher tous les arcs
     */
    @Override
    public List<Arcs> getAllArcs() {
        return arcsDao.findAll();
    }

    /**
     * Afficher un arc
     */
    @Override
    public Arcs getArcById(int id) throws ArcsNotFoundException {
        return arcsDao.findById(id).orElseThrow(() -> new ArcsNotFoundException(
                "L'arc " + id + " n'existe pas."
        ));
    }
}
