package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Boucliers;
import com.zelda.annuaire.repository.IBoucliersDao;
import com.zelda.annuaire.service.exceptions.BoucliersNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(MockitoExtension.class)
public class BoucliersServiceTest {
    private BoucliersService boucliersService;
    @Mock
    private IBoucliersDao boucliersDao;

    @BeforeEach
    void setUp() {
        boucliersService = new BoucliersService(boucliersDao);
    }

    @Test
    void addBouclier() {
        Boucliers bouclier = Boucliers.builder().id(1).build();

        Mockito.when(boucliersDao.save(any())).thenReturn(bouclier);

        Boucliers request = Boucliers.builder().nom("bouclier").build();

        Boucliers addBouclier = boucliersService.addBouclier(request);

        assertEquals(addBouclier, bouclier);
    }

    @Test
    void updateBouclier() throws BoucliersNotFoundException {
        String nom1 = "old";
        Boucliers bouclierExistant = Boucliers.builder().id(1).nom(nom1).build();
        String nom2 = "new";
        Boucliers bouclierNew = Boucliers.builder().id(1).nom(nom2).build();

        Mockito.when(boucliersDao.findById(anyInt())).thenReturn(Optional.of(bouclierExistant));
        Mockito.when(boucliersDao.save(Mockito.any(Boucliers.class))).thenReturn(bouclierNew);

        Boucliers bouclierUpdate = boucliersService.updateBouclier(bouclierNew, 1);

        assertEquals(nom2, bouclierUpdate.getNom());
    }

    @Test
    void deleteBouclierById() {
        Mockito.doNothing().when(boucliersDao).deleteById(1);

        boucliersService.deleteBouclierById(1);

        Mockito.verify(boucliersDao).deleteById(1);
    }

    @Test
    void getAllBoucliers() {
        Boucliers bouclier1 = Boucliers.builder().nom("bouclier1").build();
        Boucliers bouclier2 = Boucliers.builder().nom("bouclier2").build();
        List<Boucliers> boucliersList = List.of(bouclier1, bouclier2);

        Mockito.when(boucliersDao.findAll()).thenReturn(boucliersList);

        List<Boucliers> result = boucliersService.getAllBoucliers();

        assertEquals(2, result.size());
        assertTrue(result.get(0).getNom().contentEquals("bouclier1"));
    }

    @Test
    void getBouclierById() {
        Boucliers bouclier = Boucliers.builder().id(1).build();
        Mockito.when(boucliersDao.findById(1)).thenReturn(Optional.ofNullable(bouclier));

        Boucliers result;

        try {
            result = boucliersService.getBouclierById(1);
        } catch (BoucliersNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, result.getId());
    }

    @Test
    void getBouclierByIdKO() {
        int idBouclier = 3;
        Mockito.when(boucliersDao.findById(3)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BoucliersNotFoundException.class, () -> boucliersService.getBouclierById(idBouclier));
        String expectedMessage = "Le bouclier " + idBouclier + " n'existe pas.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
