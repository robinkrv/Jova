package com.jova.jova.dao;

import com.jova.jova.dao.impl.ProduitRepository;
import com.jova.jova.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long produitId) {
        return produitRepository.findById(produitId);
    }

    // Méthode pour ajouter un nouveau produit
    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Méthode pour mettre à jour un produit existant
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
            // Enregistrer les modifications dans la base de données
            return produitRepository.save(existingProduit);
        } else {
            // Gérer le cas où le produit n'est pas trouvé
            // Dans cet exemple, je renvoie null, mais tu peux choisir de lever une exception ou de retourner un résultat différent
            return null;
        }
    }

    public List<Produit> getProduitsByType(String type) {
        return produitRepository.findByType(type);
    }
    // Méthode pour supprimer un produit
    public void deleteProduit(Long produitId) {
        produitRepository.deleteById(produitId);
    }
}

