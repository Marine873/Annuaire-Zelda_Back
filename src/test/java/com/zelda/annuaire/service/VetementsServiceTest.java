package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Vetements;
import com.zelda.annuaire.repository.IVetementsDao;
import com.zelda.annuaire.service.exceptions.VetementsNotFoundException;
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
public class VetementsServiceTest {
    private VetementsService vetementsService;
    @Mock
    private IVetementsDao vetementsDao;

    @BeforeEach
    void setUp() {
        vetementsService = new VetementsService(vetementsDao);
    }

    @Test
    void addVetement() {
        Vetements vetement = Vetements.builder().id(1).build();

        Mockito.when(vetementsDao.save(any())).thenReturn(vetement);

        Vetements request = Vetements.builder().nom("vetement").build();

        Vetements addVetement = vetementsService.addVetement(request);

        assertEquals(addVetement, vetement);
    }

    @Test
    void updatedVetement() throws VetementsNotFoundException {
        String nom1 = "old";
        Vetements vetementExistant = Vetements.builder().id(1).nom(nom1).build();
        String nom2 = "new";
        Vetements vetementNew = Vetements.builder().id(1).nom(nom2).build();

        Mockito.when(vetementsDao.findById(anyInt())).thenReturn(Optional.of(vetementExistant));
        Mockito.when(vetementsDao.save(Mockito.any(Vetements.class))).thenReturn(vetementNew);

        Vetements vetementUpdate = vetementsService.updateVetement(vetementNew, 1);

        assertEquals(nom2, vetementUpdate.getNom());
    }

    @Test
    void deletedVetementById() {
        Mockito.doNothing().when(vetementsDao).deleteById(1);

        vetementsService.deleteVetementById(1);

        Mockito.verify(vetementsDao).deleteById(1);
    }

    @Test
    void getAllVetements() {
        Vetements vetement1 = Vetements.builder().nom("vetement1Nom").build();
        Vetements vetement2 = Vetements.builder().nom("vetement2Nom").build();
        List<Vetements> listVetements = List.of(vetement1, vetement2);

        Mockito.when(vetementsDao.findAll()).thenReturn(listVetements);

        List<Vetements> result = vetementsService.getAllVetements();

        assertEquals(2, result.size());
        assertTrue(result.get(0).getNom().contentEquals("vetement1Nom"));
    }

    @Test
    void getVetementById() {
        Vetements vetement = Vetements.builder().id(1).build();
        Mockito.when(vetementsDao.findById(1)).thenReturn(Optional.ofNullable(vetement));

        Vetements result;

        try {
            result = vetementsService.getVetementById(1);
        } catch (VetementsNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, result.getId());
    }

    @Test
    void getVetementByIdKO() {
        int idVetement = 2;
        Mockito.when(vetementsDao.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(VetementsNotFoundException.class, () -> vetementsService.getVetementById(idVetement));
        String expectedMessage = "Le vêtement " + idVetement + " n'existe pas.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
