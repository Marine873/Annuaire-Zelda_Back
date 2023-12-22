package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Armes;
import com.zelda.annuaire.repository.IArmesDao;
import com.zelda.annuaire.service.exceptions.ArmesNotFoundException;
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
public class ArmesServiceTest {
    private ArmesService armesService;
    @Mock
    private IArmesDao armesDao;

    @BeforeEach
    void setUp() {
        armesService = new ArmesService(armesDao);
    }

    @Test
    void addArme() {
        Armes armes = Armes.builder().id(1).build();

        Mockito.when(armesDao.save(any())).thenReturn(armes);

        Armes request = Armes.builder().nom("arme").build();

        Armes addArmes = armesService.addArme(request);

        assertEquals(addArmes, armes);
    }

    @Test
    void updatedArme() throws ArmesNotFoundException {
        String nom1 = "old";
        Armes armeExistant = Armes.builder().id(1).nom(nom1).build();
        String nom2 = "new";
        Armes armeNew = Armes.builder().id(1).nom(nom2).build();

        Mockito.when(armesDao.findById(anyInt())).thenReturn(Optional.of(armeExistant));
        Mockito.when(armesDao.save(Mockito.any(Armes.class))).thenReturn(armeNew);

        Armes armeUpdate = armesService.updateArme(armeNew, 1);

        assertEquals(nom2, armeUpdate.getNom());
    }

    @Test
    void deletedArmeById() {
        Mockito.doNothing().when(armesDao).deleteById(1);

        armesService.deleteArmeById(1);

        Mockito.verify(armesDao).deleteById(1);
    }

    @Test
    void getAllArmes() {
        Armes armes1 = Armes.builder().nom("arme1Nom").build();
        Armes armes2 = Armes.builder().nom("arme2Nom").build();
        List<Armes> listArmes = List.of(armes1, armes2);

        Mockito.when(armesDao.findAll()).thenReturn(listArmes);

        List<Armes> result = armesService.getAllArmes();

        assertEquals(2, result.size());
        assertTrue(result.get(0).getNom().contentEquals("arme1Nom"));
    }

    @Test
    void getArmeById() {
        Armes armes = Armes.builder().id(1).build();
        Mockito.when(armesDao.findById(1)).thenReturn(Optional.ofNullable(armes));

        Armes result;

        try {
            result = armesService.getArmeById(1);
        } catch (ArmesNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, result.getId());
    }

    @Test
    void getArmeByIdKO() {
        int idArme = 2;
        Mockito.when(armesDao.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ArmesNotFoundException.class, () -> armesService.getArmeById(idArme));
        String expectedMessage = "L'arme " + idArme + " n'existe pas.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}
