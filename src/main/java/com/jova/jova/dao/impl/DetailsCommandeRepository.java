package com.jova.jova.dao.impl;

import com.jova.jova.entity.DetailsCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsCommandeRepository extends JpaRepository<DetailsCommande, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}

