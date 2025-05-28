package com.log430.monmagasin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log430.monmagasin.model.Vente;

/**
 * Interface Repository pour l'entité {@link Vente}.
 * <p>
 * Cette interface fait partie de la couche de persistance de l'application.
 * Elle fournit des méthodes d'accès aux données liées à l'entité {@code Vente},
 * en étendant {@link org.springframework.data.jpa.repository.JpaRepository}.
 * <p>
 * Grâce à Spring Data JPA, l'implémentation des méthodes courantes (CRUD) est générée automatiquement à l'exécution.
 * <p>
 * Rôle du Repository :
 * <ul>
 *   <li>Abstraire l'accès à la base de données.</li>
 *   <li>Encapsuler les opérations de lecture/écriture (Create, Read, Update, Delete).</li>
 *   <li>Fournir une interface claire pour manipuler les entités métiers sans exposer les détails techniques de la base.</li>
 * </ul>
 *
 * @author Snely
 */
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
