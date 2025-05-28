package com.log430.monmagasin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.log430.monmagasin.console.ConsoleApp;
import com.log430.monmagasin.services.ProduitService;
import com.log430.monmagasin.services.RetourService;
import com.log430.monmagasin.services.VenteService;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(MainApplication.class, args);

        // Lancer l'application console après démarrage du contexte Spring
        ProduitService produitService = context.getBean(ProduitService.class);
        VenteService venteService = context.getBean(VenteService.class);
        RetourService retourService = context.getBean(RetourService.class);
        ConsoleApp consoleApp = new ConsoleApp(produitService, venteService, retourService);
        consoleApp.lancer();
    }
}
