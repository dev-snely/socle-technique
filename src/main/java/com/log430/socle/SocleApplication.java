package com.log430.socle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée de l'application Spring Boot.
 */
@SpringBootApplication
public final class SocleApplication {
    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private SocleApplication() {
        // Empêche l'instanciation
    }

    /**
     * Méthode principale.
     * @param args les arguments de la ligne de commande
     */
    public static void main(final String[] args) {
        SpringApplication.run(SocleApplication.class, args);
    }
}
