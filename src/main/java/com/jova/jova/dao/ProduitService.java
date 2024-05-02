package com.jova.jova.dao;

import com.jova.jova.dao.impl.ProduitRepository;
import com.jova.jova.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations liées aux produits.
 */
@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    /**
     * Constructeur pour l'injection de dépendances.
     * @param produitRepository Repository pour les opérations sur les produits.
     */
    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /**
     * Récupérer la liste de tous les produits.
     * @return La liste de tous les produits.
     */
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    /**
     * Récupérer un produit par son identifiant.
     * @param produitId L'identifiant du produit à récupérer.
     * @return Le produit correspondant à l'identifiant, s'il existe.
     */
    public Optional<Produit> getProduitById(Long produitId) {
        return produitRepository.findById(produitId);
    }

    /**
     * Ajouter un nouveau produit.
     * @param produit Le produit à ajouter.
     * @return Le produit ajouté.
     */
    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    /**
     * Mettre à jour un produit existant.
     * @param produitId L'identifiant du produit à mettre à jour.
     * @param updatedProduit Le produit mis à jour.
     * @return Le produit mis à jour.
     */
    public Produit updateProduit(Long produitId, Produit updatedProduit) {
        Optional<Produit> existingProduitOptional = produitRepository.findById(produitId);
        if (existingProduitOptional.isPresent()) {
            Produit existingProduit = existingProduitOptional.get();
            // Mettre à jour les propriétés du produit existant avec les nouvelles valeurs
            existingProduit.setNom(updatedProduit.getNom());
            existingProduit.setPrix(updatedProduit.getPrix());
            existingProduit.setDescription(updatedProduit.getDescription());
            existingProduit.setType(updatedProduit.getType());
            existingProduit.setPoids(updatedProduit.getPoids());
            existingProduit.setImageUrl(updatedProduit.getImageUrl());
            return produitRepository.save(existingProduit);
        } else {
            return null;
        }
    }

    /**
     * Rechercher des produits par un terme de recherche.
     * @param recherche Le terme de recherche.
     * @return La liste des produits correspondant à la recherche.
     */
    public List<Produit> rechercherProduits(String recherche) {
        return produitRepository.findByNomContainingIgnoreCase(recherche);
    }

    /**
     * Récupérer la liste des produits par type.
     * @param type Le type de produit.
     * @return La liste des produits correspondant au type spécifié.
     */
    public List<Produit> getProduitsByType(String type) {
        return produitRepository.findByType(type);
    }

    /**
     * Supprimer un produit par son identifiant.
     * @param produitId L'identifiant du produit à supprimer.
     */
    public void deleteProduit(Long produitId) {
        produitRepository.deleteById(produitId);
    }
}
