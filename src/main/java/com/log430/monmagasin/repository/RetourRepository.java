package com.log430.monmagasin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log430.monmagasin.model.Retour;

/**
 * Interface Repository pour l'entité {@link Retour}.
 * <p>
 * Faisant partie de la couche de persistance, cette interface étend
 * {@link org.springframework.data.jpa.repository.JpaRepository}.
 * Spring Data JPA génère ainsi automatiquement les opérations CRUD de base
 * pour l'entité {@link Retour}.
 * <p>
 * Il est également possible d'y définir des méthodes de requête personnalisées
 * spécifiques aux besoins de l'entité {@link Retour}.
 *
 * @see Retour Entité concernée.
 * @see org.springframework.data.jpa.repository.JpaRepository Interface de base Spring Data.
 * @author Snely
 * @since 26-05-2025
 * @version 1.0
 */
public interface RetourRepository extends JpaRepository<Retour, Long> {
}
