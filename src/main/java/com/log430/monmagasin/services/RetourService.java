package com.log430.monmagasin.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.model.Retour;
import com.log430.monmagasin.model.Vente;
import com.log430.monmagasin.repository.ProduitRepository;
import com.log430.monmagasin.repository.RetourRepository;
import com.log430.monmagasin.repository.VenteRepository;

@Service
public class RetourService {

  private final RetourRepository retourRepository;
  private final VenteRepository venteRepository;
  private final ProduitRepository produitRepository;

  public RetourService(RetourRepository retourRepository, VenteRepository venteRepository,
      ProduitRepository produitRepository) {
    this.retourRepository = retourRepository;
    this.venteRepository = venteRepository;
    this.produitRepository = produitRepository;
  }

  /**
   * Enregistre un retour de produits.
   *
   * @param retour le retour à enregistrer, incluant les produits et la vente
   * @return le retour enregistré
   */
  public Retour enregistrerRetour(Retour retour) {
    // Vérifier que la vente est valide
    Long venteId = retour.getReferenceDeVente().getId();
    Vente vente = venteRepository.findById(venteId)
        .orElseThrow(() -> new RuntimeException("Vente non trouvée"));

    retour.setReferenceDeVente(vente); // réassigne l'objet persistant

    // Mettre à jour les stocks
    for (Produit produit : retour.getProduitsRetournes()) {
      produit.setQuantiteEnStock(produit.getQuantiteEnStock() + 1); // à adapter si besoin
      produitRepository.save(produit);
    }

    retour.setDate(LocalDateTime.now());

    return retourRepository.save(retour);
  }

}
