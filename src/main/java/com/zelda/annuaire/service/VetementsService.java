package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Vetements;
import com.zelda.annuaire.repository.IVetementsDao;
import com.zelda.annuaire.service.exceptions.VetementsNotFoundException;
import com.zelda.annuaire.service.interfaces.IVetementsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetementsService implements IVetementsService {
    private IVetementsDao vetementsDao;

    public VetementsService(IVetementsDao vetementsDao) {
        this.vetementsDao = vetementsDao;
    }

    /**
     * Ajouter un vêtement
     */
    @Override
    public Vetements addVetement(Vetements vetement) {
        return vetementsDao.save(vetement);
    }

    /**
     * Modifier un vêtement
     */
    @Override
    public Vetements updateVetement(Vetements vetement, int id) throws VetementsNotFoundException {
        Vetements vetements = getVetementById(id);
        vetements.setJeux(vetement.getJeux());
        vetements.setSetVetement(vetement.getSetVetement());
        vetements.setNom(vetement.getNom());
        vetements.setDefMini(vetement.getDefMini());
        vetements.setDefMax(vetement.getDefMax());
        vetements.setPartieCorps(vetement.getPartieCorps());
        vetements.setLocalisation(vetement.getLocalisation());
        vetements.setEffet(vetement.getEffet());
        vetements.setBonusSet(vetement.getBonusSet());
        return vetementsDao.save(vetements);
    }

    /**
     * Supprimer un vêtement
     */
    @Override
    public void deleteVetementById(int id) {
        vetementsDao.deleteById(id);
    }

    /**
     * Afficher tous les vêtements
     */
    @Override
    public List<Vetements> getAllVetements() {
        return vetementsDao.findAll();
    }

    /**
     * Afficher un vêtement
     */
    @Override
    public Vetements getVetementById(int id) throws VetementsNotFoundException {
        return vetementsDao.findById(id).orElseThrow(()-> new VetementsNotFoundException(
                "Le vêtement " + id + " n'existe pas."
        ));
    }
}
