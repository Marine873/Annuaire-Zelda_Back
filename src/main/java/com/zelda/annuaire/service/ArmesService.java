package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Armes;
import com.zelda.annuaire.repository.IArmesDao;
import com.zelda.annuaire.service.exceptions.ArmesNotFoundException;
import com.zelda.annuaire.service.interfaces.IArmesService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArmesService implements IArmesService {
    private IArmesDao armesDao;

    public ArmesService(IArmesDao armesDao) {
        this.armesDao = armesDao;
    }

    /**
     * Ajouter une arme
     */
    @Override
    public Armes addArme(Armes arme) {
        return armesDao.save(arme);
    }

    /**
     * Modifier une arme
     */
    @Override
    public Armes updateArme(Armes arme, int id) throws ArmesNotFoundException {
        Armes armes = getArmeById(id);
        armes.setJeux(armes.getJeux());
        armes.setTypeArme(armes.getTypeArme());
        armes.setNom(armes.getNom());
        armes.setAttaqueBase(armes.getAttaqueBase());
        armes.setAttaqueRang1Max(armes.getAttaqueRang1Max());
        armes.setAttaqueRang2Max(armes.getAttaqueRang2Max());
        armes.setDurabiliteBase(armes.getDurabiliteBase());
        armes.setDurabiliteRang1Max(armes.getDurabiliteRang1Max());
        armes.setDurabiliteRang2Max(armes.getDurabiliteRang2Max());
        armes.setJetEffet(armes.getJetEffet());
        armes.setJetBase(armes.getJetBase());
        armes.setJetBonus(armes.getJetBonus());
        armes.setDisponibilite(armes.getDisponibilite());
        return armesDao.save(armes);
    }

    /**
     * Supprimer une arme
     */
    @Override
    public void deleteArmeById(int id) {
        armesDao.deleteById(id);
    }

    /**
     * Afficher toutes les armes
     */
    @Override
    public List<Armes> getAllArmes() {
        return armesDao.findAll();
    }

    /**
     * Afficher une arme
     */
    @Override
    public Armes getArmeById(int id) throws ArmesNotFoundException {
        return armesDao.findById(id).orElseThrow(() -> new ArmesNotFoundException(
                "L'arme " + id + " n'existe pas."
        ));
    }
}
