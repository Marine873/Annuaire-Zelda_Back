package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Boucliers;
import com.zelda.annuaire.repository.IBoucliersDao;
import com.zelda.annuaire.service.exceptions.BoucliersNotFoundException;
import com.zelda.annuaire.service.interfaces.IBoucliersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoucliersService implements IBoucliersService {
    private IBoucliersDao boucliersDao;

    public BoucliersService(IBoucliersDao boucliersDao) {
        this.boucliersDao = boucliersDao;
    }

    /**
     * Ajouter un bouclier
     */
    @Override
    public Boucliers addBouclier(Boucliers bouclier) {
        return boucliersDao.save(bouclier);
    }

    /**
     * Modifier un bouclier
     */
    @Override
    public Boucliers updateBouclier(Boucliers bouclier, int id) throws BoucliersNotFoundException {
        Boucliers boucliers = getBouclierById(id);
        boucliers.setJeux(bouclier.getJeux());
        boucliers.setNom(bouclier.getNom());
        boucliers.setDurabilite(bouclier.getDurabilite());
        boucliers.setAttaque(bouclier.getAttaque());
        boucliers.setDisponibilite(bouclier.getDisponibilite());
        return boucliersDao.save(boucliers);
    }

    /**
     * Supprimer un bouclier
     */
    @Override
    public void deleteBouclierById(int id) {
        boucliersDao.deleteById(id);
    }

    /**
     * Afficher tous les boucliers
     */
    @Override
    public List<Boucliers> getAllBoucliers() {
        return boucliersDao.findAll();
    }

    /**
     * Afficher un bouclier
     */
    @Override
    public Boucliers getBouclierById(int id) throws BoucliersNotFoundException {
        return boucliersDao.findById(id).orElseThrow(()-> new BoucliersNotFoundException(
                "Le bouclier " + id + " n'existe pas."
        ));
    }
}
