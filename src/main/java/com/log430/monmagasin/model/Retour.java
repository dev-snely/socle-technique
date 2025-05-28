package com.log430.monmagasin.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Retour {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime date;

  @ManyToOne
  private Vente vente;

  @ManyToMany
  private List<Produit> produitsRetournes;

  public Retour() {
  }

  public Retour(LocalDateTime date, Vente vente, List<Produit> produitsRetournes) {
    this.date = date;
    this.vente = vente;
    this.produitsRetournes = produitsRetournes;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Vente getReferenceDeVente() {
    return vente;
  }

  /**
   * Associe ce retour à une vente précise.
   * Permet de savoir quelle vente est concernée par le retour.
   *
   * @param vente la vente liée à ce retour
   */
  public void setReferenceDeVente(Vente vente) {
    this.vente = vente;
  }

  public List<Produit> getProduitsRetournes() {
    return produitsRetournes;
  }

  public void setProduitsRetournes(List<Produit> produitsRetournes) {
    this.produitsRetournes = produitsRetournes;
  }

  @Override
  public String toString() {
    return "Retour{" +
        "id=" + id +
        ", date=" + date +
        ", vente=" + vente +
        ", produitsRetournes=" + produitsRetournes +
        '}';
  }
}
