package com.log430.monmagasin.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;

@Entity
public class Vente {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;

  private double total;

  @ManyToMany
  private List<Produit> produitsVendus;

  public Vente() {
  }

  public Vente(LocalDate date, List<Produit> produitsVendus, double total) {
    this.date = date;
    this.produitsVendus = produitsVendus;
    this.total = total;
  }

  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public List<Produit> getProduitsVendus() {
    return produitsVendus;
  }

  public void setListeDeProduitVendu(List<Produit> produitsVendus) {
    this.produitsVendus = produitsVendus;
  }

  public double getTotal() {
    return total;
  }

  public void setTotalVente(double total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "Vente{" +
           "id=" + id +
           ", date=" + date +
           ", total=" + total +
           ", produitsVendus=" + produitsVendus +
           '}';
  }
}
