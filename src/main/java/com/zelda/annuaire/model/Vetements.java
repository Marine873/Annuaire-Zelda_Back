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

    @Column(name = "setvetement")
    private String setVetement;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(name = "defmini")
    private int defMini;

    @Column(name = "defmax")
    private int defMax;

    @Column(name = "partiecorps")
    private String partieCorps;

    private String localisation;

    private String effet;

    @Column(name = "bonusset")
    private String bonusSet;
}
