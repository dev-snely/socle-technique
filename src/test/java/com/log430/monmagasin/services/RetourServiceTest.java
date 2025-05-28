package com.log430.monmagasin.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.model.Retour;
import com.log430.monmagasin.model.Vente;
import com.log430.monmagasin.repository.ProduitRepository;
import com.log430.monmagasin.repository.RetourRepository;
import com.log430.monmagasin.repository.VenteRepository;

class RetourServiceTest {

  private RetourRepository retourRepository;
  private ProduitRepository produitRepository;
  private RetourService retourService;
  private VenteRepository venteRepository;

  @BeforeEach
  void setUp() {
    retourRepository = mock(RetourRepository.class);
    produitRepository = mock(ProduitRepository.class);
    venteRepository = mock(VenteRepository.class);
    retourService = new RetourService(retourRepository, venteRepository, produitRepository);
  }

  @Test
  void testEnregistrerRetour() {
    Produit produit = new Produit();
    produit.setId(1L);
    produit.setNom("Pain");
    produit.setQuantiteEnStock(5);

    Vente vente = new Vente();
    vente.setId(1L);

    Retour retour = new Retour();
    retour.setReferenceDeVente(vente); // obligatoire
    retour.setProduitsRetournes(List.of(produit));

    // MOCK DU COMPORTEMENT attendu
    when(venteRepository.findById(1L)).thenReturn(java.util.Optional.of(vente));
    when(produitRepository.save(produit)).thenReturn(produit);
    when(retourRepository.save(retour)).thenReturn(retour);

    retourService.enregistrerRetour(retour);

    verify(retourRepository).save(retour);
    verify(produitRepository).save(produit);
    assertEquals(6, produit.getQuantiteEnStock());
  }

}