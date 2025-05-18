package com.log430.socle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur de base de l'application.
 */
@RestController
public final class HomeController {

    /**
     * Répond à la racine de l'application.
     * @return un message texte
     */
    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }
}
