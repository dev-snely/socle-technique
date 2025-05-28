package com.log430.monmagasin.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.model.Vente;
import com.log430.monmagasin.repository.ProduitRepository;

@SpringBootTest
@AutoConfigureTestDatabase
class VenteServiceTest {

    @Autowired
    private VenteService venteService;

    @Autowired
    private ProduitRepository produitRepository;

    @BeforeEach
    void init() {
        produitRepository.deleteAll();
        produitRepository.save(new Produit("Souris", 25.0,"Electronique", 5));
    }

    @Test
    void contextLoads() {
        // Vérifie que le contexte de l'application se charge correctement
        // Aucun test spécifique à écrire ici, juste s'assurer que l'application démarre
    }

    @Test
    void testCreerVenteEtCalculerTotal() {
        List<Produit> produits = produitRepository.findAll();
        Vente vente = venteService.enregistrerVente(produits);
        assertEquals(25.0, vente.getTotal());
    }
}
