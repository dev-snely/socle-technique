package com.log430.monmagasin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.log430.monmagasin.exception.ProduitNonTrouveException;
import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.repository.ProduitRepository;

@Service
public class ProduitService {

  private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /**
     * Recherche un produit par son ID.
     * @param id identifiant du produit
     * @return le produit trouvé
     * @throws ProduitNonTrouveException si le produit n'existe pas
     */
    public Produit chercherParId(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNonTrouveException("Produit avec ID " + id + " introuvable"));
    }

    /**
     * Recherche un produit par son nom.
     * @param nom nom du produit
     * @return le produit trouvé
     */
    public Produit chercherParNom(String nom) {
        return produitRepository.findByNomContainingIgnoreCase(nom).stream()
                .findFirst()
                .orElseThrow(() -> new ProduitNonTrouveException("Produit nommé '" + nom + "' introuvable"));
    }
    
    /**
     * Liste tous les produits en base de données.
     */
    public List<Produit> listerTous() {
        return produitRepository.findAll();
    }

    /**
     * Décrémente la quantité d'un produit (ex: après une vente).
     * @param id identifiant du produit
     * @param quantite quantité à décrémenter
     */
    public void decrementerStock(Long id, int qte) {
        Produit produit = chercherParId(id);
        if (produit.getQuantiteEnStock() < qte) {
            throw new IllegalArgumentException("Stock insuffisant pour le produit " + produit.getNom());
        }
        produit.setQuantiteEnStock(produit.getQuantiteEnStock() - qte);
        produitRepository.save(produit);
    }

    /**
     * Recherche des produits par nom partiel.
     * @param nom nom partiel du produit
     * @return liste de produits correspondants
     */
    public List<Produit> chercherParNomPartiel(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Erreur: Le nom ne peut pas être vide");
        }
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Recherche des produits par catégorie.
     * @param cat catégorie du produit
     * @return liste de produits dans la catégorie
     */
    public List<Produit> chercherParCategorie(String cat) {
        if (cat == null || cat.isEmpty()) {
            throw new IllegalArgumentException("Erreur: La catégorie ne peut pas être vide");
        }
        return produitRepository.findByCategorieContainingIgnoreCase(cat);
    }
}
