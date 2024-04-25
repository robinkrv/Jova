package com.jova.jova.dao.impl;

import com.jova.jova.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}

