package com.log430.monmagasin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Produit {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;
  private String nom;
  private String categorie;
  private double prix;
  private int quantiteEnStock;

  // Constructeur vide requis par JPA
  public Produit() {
    // Nécessaire pour JPA
  }

  public Produit(String nom, double prix, String categorie, int quantiteEnStock) {
    this.nom = nom;
    this.prix = prix;
    this.categorie = categorie;
    this.quantiteEnStock = quantiteEnStock;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getCategorie() {
    return categorie;
  }

  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }

  public int getQuantiteEnStock() {
    return quantiteEnStock;
  }

  public void setQuantiteEnStock(int quantiteEnStock) {
    this.quantiteEnStock = quantiteEnStock;
  }

  @Override
  public String toString() {
    return String.format(
        "{ \"id\": %d, \"nom\": \"%s\", \"prix\": %.2f, \"categorie\": \"%s\", \"quantiteEnStock\": %d }",
        id, nom, prix, categorie, quantiteEnStock);
  }

}
