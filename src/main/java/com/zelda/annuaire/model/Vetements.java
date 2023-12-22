package com.zelda.annuaire.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vetements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String jeux;

    private String setVetement;

    @Column(nullable = false, length = 100)
    private String nom;

    private int defMini;

    private int defMax;

    private String partieCorps;

    private String localisation;

    private String effet;

    private String bonusSet;
}
