package com.log430.socle;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest // Configuration automatique de MockMvc
class HomeControllerWebTest {

	@Test
	void contextLoads() {
		 assertTrue(true);
				// Cette méthode est appelée par Spring Boot pour vérifier que le contexte de l'application
				// se charge correctement. Si le contexte ne se charge pas, le test échoue.
				//
				// En gros, ça vérifie que tout ce qui est nécessaire pour faire fonctionner
				// l'application (comme les dépendances et la configuration) est bien en place.
				// Si tout est bon, le test passe.
	}

	@Autowired
	private MockMvc mockMvc; // Permet de simuler des requêtes HTTP
	
	@Test
	void testIndexEndpoint() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().string("Hello World!"));
	}


}
