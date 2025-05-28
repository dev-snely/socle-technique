package com.log430.monmagasin.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.log430.monmagasin.model.Produit;
import com.log430.monmagasin.model.Retour;
import com.log430.monmagasin.model.Vente;
import com.log430.monmagasin.services.ProduitService;
import com.log430.monmagasin.services.RetourService;
import com.log430.monmagasin.services.VenteService;

public class ConsoleApp {
  private static final String VOTRE_CHOIX_PROMPT = "Votre choix : ";
  private static final Logger LOGGER = Logger.getLogger(ConsoleApp.class.getName());
  private final Scanner scanner = new Scanner(System.in);

  private final ProduitService produitService;
  private final VenteService venteService;
  private final RetourService retourService;

  public ConsoleApp(ProduitService produitService, VenteService venteService, RetourService retourService) {
    this.produitService = produitService;
    this.venteService = venteService;
    this.retourService = retourService;
  }

  public void lancer() {
    int choix = -1;
    while (choix != 0) {
      afficherMenu();
      choix = lireChoix();
      traiterChoix(choix);
    }
    LOGGER.info("Fermeture de l'application. A bientot !");
  }

  private void afficherMenu() {
    System.out.println("\n===================================");
    System.out.println("         MON MAGASIN - MENU        ");
    System.out.println("===================================");
    System.out.println("  1. Rechercher un produit");
    System.out.println("  2. Enregistrer une vente");
    System.out.println("  3. Annuler une vente");
    System.out.println("  4. Consulter l'état du stock");
    System.out.println("  0. Pour quitter");
    System.out.println("-----------------------------------");
    System.out.print(VOTRE_CHOIX_PROMPT);
  }

  private int lireChoix() {
    while (true) {
      try {
        String line = scanner.nextLine();
        int choix = Integer.parseInt(line);
        if (choix >= 0 && choix <= 4) {
          return choix;
        } else {
          System.out.println("Choix invalide, veuillez entrer un nombre entre 0 et 4.");
          System.out.print(VOTRE_CHOIX_PROMPT);
        }
      } catch (NumberFormatException e) {
        System.out.println("Attention: Entrée invalide. Veuillez entrer un nombre.");
        System.out.print(VOTRE_CHOIX_PROMPT);
      }
    }
  }

  private void traiterChoix(int choix) {
    switch (choix) {
      case 1 ->
        rechercherProduit();
      case 2 ->
        traiterVenteProduits();
      case 3 ->
        annulerUneVente();
      case 4 ->
        afficherStock();
      case 0 -> {
        // rien, on quitte
      }
      default ->
        System.out.println("Attention: Choix non reconnu.");
    }
  }

  /**
   * Recherche un produit par ID, nom ou catégorie.
   * Affiche les résultats trouvés.
   * Cette méthode permet à l'utilisateur de rechercher un produit
   * en fonction de différents critères.
   */
  private void rechercherProduit() {
    System.out.println("Vous avez choisi: RECHERCHER UN PRODUIT");
    System.out.print("Rechercher par ID[1], Nom[2] ou Catégorie[3] : ");
    int critere = lireChoixRecherche();
    List<Produit> resultats = new ArrayList<>();

    switch (critere) {
      case 1 -> {
        System.out.print("Entrez l'ID du produit : ");
        String id = scanner.nextLine();
        Produit p = produitService.chercherParId(Long.valueOf(id));
        if (p != null)
          resultats.add(p);
      }
      case 2 -> {
        System.out.print("Entrez le nom du produit : ");
        String nom = scanner.nextLine();
        resultats = produitService.chercherParNomPartiel(nom);
      }
      case 3 -> {
        System.out.print("Entrez la catégorie : ");
        String cat = scanner.nextLine();
        resultats = produitService.chercherParCategorie(cat);
      }
      default -> System.out.println("Critère non reconnu.");
    }

    // Affichage des résultats
    if (resultats.isEmpty()) {
      System.out.println("""

          Aucun produit trouv\u00e9.""");
    } else {
      System.out.println("""

          Produits trouv\u00e9s.""");
      for (Produit produit : resultats) {
        System.out.println(produit);
      }
    }
  }

  private int lireChoixRecherche() {
    while (true) {
      try {
        String line = scanner.nextLine();
        int choix = Integer.parseInt(line);
        if (choix >= 1 && choix <= 3) {
          return choix;
        } else {
          LOGGER.warning("Choix invalide, veuillez entrer un nombre entre 1 et 3.");
          System.out.print(VOTRE_CHOIX_PROMPT);
        }
      } catch (NumberFormatException e) {
        LOGGER.warning("Entrée invalide. Veuillez entrer un nombre.");
        System.out.print(VOTRE_CHOIX_PROMPT);
      }
    }
  }

  private void traiterVenteProduits() {
    Map<Produit, Integer> panier = new HashMap<>();

    System.out.println("=== Vente de produits ===");

    // Affichage des produits disponibles
    List<Produit> tousProduits = produitService.listerTous();
    System.out.println("Produits disponibles :");
    tousProduits.stream()
        .filter(p -> p.getQuantiteEnStock() > 0)
        .forEach(System.out::println);

    while (true) {
      System.out.print("\nNom du produit (ou taper 'fin' pour finaliser l'achat) : ");
      String nom = scanner.nextLine().trim();
      if (nom.equalsIgnoreCase("fin"))
        break;

      Produit produit = produitService.chercherParNom(nom);
      if (produit == null) {
        System.out.println("Produit non trouvé.");
        continue;
      }

      if (produit.getQuantiteEnStock() == 0) {
        System.out.println("Ce produit est en rupture de stock.");
        continue;
      }

      System.out.print("Quantité à acheter (max " + produit.getQuantiteEnStock() + ") : ");
      int quantite;
      try {
        quantite = Integer.parseInt(scanner.nextLine().trim());
        if (quantite <= 0 || quantite > produit.getQuantiteEnStock()) {
          System.out.println("Quantité invalide.");
          continue;
        }
      } catch (NumberFormatException e) {
        System.out.println("Veuillez entrer un nombre valide.");
        continue;
      }

      panier.put(produit, panier.getOrDefault(produit, 0) + quantite);
      System.out.println(quantite + " x " + produit.getNom() + " ajouté au panier.");
    }

    if (panier.isEmpty()) {
      LOGGER.warning("Aucun produit sélectionné. Vente annulée.");
      return;
    }

    Vente vente = venteService.finaliserVenteAvecProduitsEtQuantites(panier);
    System.out.println("Vente enregistrée avec succès. Total : " + vente.getTotal() + " €\n");
  }

  private void annulerUneVente() {
    LOGGER.info("=== Annulation d'une vente ===");

    // Afficher les ventes existantes
    List<Vente> ventes = venteService.listerToutesLesVentes();
    if (ventes.isEmpty()) {
      System.out.println("Aucune vente enregistrée pour le moment.");
      return;
    }

    System.out.println("Liste des ventes existantes :");
    for (Vente vente : ventes) {
      System.out.println("ID: " + vente.getId() +
          " | Date: " + vente.getDate() +
          " | Total: " + vente.getTotal() + " €");
    }

    // Saisie de l'ID à annuler
    System.out.print("\nEnterez l'ID de la vente à annuler : ");
    String id = scanner.nextLine().trim();

    try {
      venteService.annulerVente(Long.valueOf(id));
      LOGGER.info("Vente annulée avec succès.");
    } catch (NumberFormatException e) {
      System.out.println("ID invalide. Veuillez entrer un nombre.");
    } catch (IllegalArgumentException e) {
      System.out.println("Erreur : " + e.getMessage());
    }
  }

  private void afficherStock() {
    List<Produit> produits = produitService.listerTous();
    System.out.println("ÉTAT DU STOCK :");
    for (Produit p : produits) {
      System.out.println(String.format("ID : %d | - %s | %s | Prix : %.2f | Stock : %d",
          p.getId(), p.getNom(), p.getCategorie(), p.getPrix(), p.getQuantiteEnStock()));
    }
    System.out.print("\n");
  }

}
