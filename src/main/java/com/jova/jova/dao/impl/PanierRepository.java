package com.jova.jova.dao.impl;

import com.jova.jova.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
