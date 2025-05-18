package com.log430.socle;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController // Utilise @RestController au lieu de @Controller
public class HomeController {

    @GetMapping("/") // Utilise @GetMapping pour simplifier le mapping GET sur "/"
    public String index() {
        return "Hello World!"; // Retourne directement le contenu
    }
}