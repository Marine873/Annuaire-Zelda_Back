package com.zelda.annuaire.service;

import com.zelda.annuaire.model.Arcs;
import com.zelda.annuaire.repository.IArcsDao;
import com.zelda.annuaire.service.exceptions.ArcsNotFoundException;
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
class ArcsServiceTest {
    private ArcsService arcsService;
    @Mock
    private IArcsDao arcsDao;

    @BeforeEach
    void setUp() {
        arcsService = new ArcsService(arcsDao);
    }

    @Test
    void addArc() {
        Arcs arc = Arcs.builder().id(1).build();

        Mockito.when(arcsDao.save(any())).thenReturn(arc);

        Arcs request = Arcs.builder().nom("arc").build();

        Arcs addArc = arcsService.addArc(request);

        assertEquals(addArc, arc);
    }

    @Test
    void updatedArc() throws ArcsNotFoundException {
        String nom1 = "old";
        Arcs arcExistant = Arcs.builder().id(1).nom(nom1).build();
        String nom2 = "new";
        Arcs arcNew = Arcs.builder().id(1).nom(nom2).build();

        Mockito.when(arcsDao.findById(anyInt())).thenReturn(Optional.of(arcExistant));
        Mockito.when(arcsDao.save(Mockito.any(Arcs.class))).thenReturn(arcNew);

        Arcs arcUpdate = arcsService.updateArc(arcNew, 1);

        assertEquals(nom2, arcUpdate.getNom());
    }

    @Test
    void deletedArcById() {
        Mockito.doNothing().when(arcsDao).deleteById(1);

        arcsService.deleteArcById(1);

        Mockito.verify(arcsDao).deleteById(1);
    }

    @Test
    void getAllArcs() {
        Arcs arc1 = Arcs.builder().nom("arc1Nom").build();
        Arcs arc2 = Arcs.builder().nom("arc2Nom").build();
        List<Arcs> listArcs = List.of(arc1, arc2);

        Mockito.when(arcsDao.findAll()).thenReturn(listArcs);

        List<Arcs> result = arcsService.getAllArcs();

        assertEquals(2, result.size());
        assertTrue(result.get(0).getNom().contentEquals("arc1Nom"));
    }

    @Test
    void getArcById() {
        Arcs arc = Arcs.builder().id(1).build();
        Mockito.when(arcsDao.findById(1)).thenReturn(Optional.ofNullable(arc));

        Arcs result;

        try {
            result = arcsService.getArcById(1);
        } catch (ArcsNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, result.getId());
    }

    @Test
    void getArcByIdKO() {
        int idArc = 2;
        Mockito.when(arcsDao.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ArcsNotFoundException.class, () -> arcsService.getArcById(idArc));
        String expectedMessage = "L'arc " + idArc + " n'existe pas.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


}
