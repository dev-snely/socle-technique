package com.log430.monmagasin.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.repository.ProduitRepository;

@Configuration
public class DonneesInitiales {
  @Bean
  CommandLineRunner initDatabase(ProduitRepository produitRepository) {
    return args -> {
      produitRepository.save(new Produit("Pain", 2.5, "Féculents", 20));
      produitRepository.save(new Produit("Lait", 1.8, "Laitier", 15));
      produitRepository.save(new Produit("Fromage", 5.0, "Laitier", 10));
      produitRepository.save(new Produit("Pomme", 0.9, "Fruits", 30));
      produitRepository.save(new Produit("Poulet", 7.5, "Viande", 12));
      produitRepository.save(new Produit("Café", 4.2, "Boissons", 25));
      produitRepository.save(new Produit("Savon", 1.2, "Hygiène", 40));

      System.out.println("🟢 Données initiales insérées dans la base H2.");
      
    };
  }
}
