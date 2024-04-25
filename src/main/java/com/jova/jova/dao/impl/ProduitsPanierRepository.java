package com.jova.jova.dao.impl;

import com.jova.jova.entity.ProduitsPanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitsPanierRepository extends JpaRepository<ProduitsPanier, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}

