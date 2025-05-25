package com.log430.socle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.log430.monmagasin.HomeController;

class HomeControllerUnitTest {

    @Test
    void testGetIndexContent() {
        HomeController controller = new HomeController();
        String result = controller.index();
        // Vérifie que le résultat est "index.html"
        // Cela signifie que la méthode getViewName() retourne le nom de la vue correcte
        assertEquals("Hello World!", result);
    }

}
