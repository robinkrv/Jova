package com.jova.jova.dao.impl;

import com.jova.jova.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}

