package com.jova.jova.dao.impl;

import com.jova.jova.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByType(String type);

    List<Produit> findByNomContainingIgnoreCase(String nom);
}

