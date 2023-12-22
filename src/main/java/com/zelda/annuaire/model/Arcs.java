package com.zelda.annuaire.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Arcs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String jeux;

    @Column(nullable = false, length = 100)
    private String nom;

    private int attaque;

    private int durabilite;

    private int portee;

    @Column(name = "multifleches")
    private int multifleches;

    private String visee;

    private String disponibilite;

}
