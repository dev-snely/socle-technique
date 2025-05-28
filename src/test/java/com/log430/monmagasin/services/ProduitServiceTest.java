package com.log430.monmagasin.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.repository.ProduitRepository;

@SpringBootTest
@AutoConfigureTestDatabase
class ProduitServiceTest {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ProduitRepository produitRepository;

    @BeforeEach
    void setup() {
        produitRepository.deleteAll();
        produitRepository.save(new Produit("Clavier", 50.0,"Electronique", 10));
    }

    @Test
    void testListerProduitsEnStock() {
        List<Produit> produits = produitService.listerTous();
        assertFalse(produits.isEmpty());
    }

    @Test
    void testChercherParNom() {
        Produit produit = produitService.chercherParNom("Clavier");
        assertEquals("Clavier", produit.getNom());
    }
}
