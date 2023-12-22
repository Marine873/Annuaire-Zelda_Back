package com.zelda.annuaire.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Armes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String jeux;

    @Column(nullable = false, length = 50, name = "typearme")
    private String typeArme;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(name = "attaquebase")
    private int attaqueBase;

    @Column(name = "attaquerang1max")
    private int attaqueRang1Max;

    @Column(name = "attaquerang2max")
    private int attaqueRang2Max;

    @Column(name = "durabilitebase")
    private int durabiliteBase;

    @Column(name = "durabiliterang1max")
    private int durabiliteRang1Max;

    @Column(name = "durabiliterang2max")
    private int durabiliteRang2Max;

    @Column(name = "jeteffet")
    private String jetEffet;

    @Column(name = "jetbase")
    private int jetBase;

    @Column(name = "jetbonus")
    private int jetBonus;

    private String disponibilite;

}
