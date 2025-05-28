package com.log430.monmagasin.services;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.model.Vente;
import com.log430.monmagasin.repository.ProduitRepository;
import com.log430.monmagasin.repository.VenteRepository;

@Service
public class VenteService {

  private final VenteRepository venteRepository;
  private final ProduitRepository produitRepository;

  public VenteService(VenteRepository venteRepository, ProduitRepository produitRepository) {
    this.venteRepository = venteRepository;
    this.produitRepository = produitRepository;
  }

  /**
   * Enregistre une nouvelle vente.
   * 
   * @param produitsListe liste des produits vendus
   * @return la vente enregistrée
   */
  public Vente enregistrerVente(List<Produit> produitsListe) {
    double total = produitsListe.stream().mapToDouble(Produit::getPrix).sum();

    // Mettre à jour les stocks
    for (Produit produit : produitsListe) {
      produit.setQuantiteEnStock(produit.getQuantiteEnStock() - 1); // à adapter si quantité variable
      produitRepository.save(produit);
    }

    Vente vente = new Vente();
    vente.setDate(LocalDate.now());
    vente.setListeDeProduitVendu(produitsListe);
    vente.setTotalVente(total);

    return venteRepository.save(vente);
  }

  public Vente finaliserVenteAvecProduits(List<Produit> produits) {
    double total = produits.stream()
        .mapToDouble(Produit::getPrix)
        .sum();

    Vente vente = new Vente();
    vente.setListeDeProduitVendu(produits);

    vente.setTotalVente(total);
    vente.setDate(LocalDate.now());

    return venteRepository.save(vente);
  }

  public List<Vente> listerToutesLesVentes() {
    return venteRepository.findAll();
  }

  public Vente chercherParId(Long id) {
    return venteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Vente avec ID " + id + " introuvable"));
  }

  public void annulerVente(Long id) {
    Vente vente = chercherParId(id);
    venteRepository.delete(vente);
  }

    public Vente finaliserVenteAvecProduitsEtQuantites(Map<Produit, Integer> panier) {
        double total = panier.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrix() * entry.getValue())
                .sum();

        // Mettre à jour les stocks
        for (Map.Entry<Produit, Integer> entry : panier.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();
            produit.setQuantiteEnStock(produit.getQuantiteEnStock() - quantite);
            produitRepository.save(produit);
        }

        Vente vente = new Vente();
        vente.setListeDeProduitVendu(List.copyOf(panier.keySet()));
        vente.setTotalVente(total);
        vente.setDate(LocalDate.now());

        return venteRepository.save(vente);
    }

}
