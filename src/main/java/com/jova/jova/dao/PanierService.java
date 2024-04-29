package com.jova.jova.dao;

import com.jova.jova.dao.impl.PanierRepository;
import com.jova.jova.dao.impl.UtilisateurRepository;
import com.jova.jova.entity.Panier;
import com.jova.jova.entity.Produit;
import com.jova.jova.entity.ProduitsPanier;
import com.jova.jova.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierService {

    private final PanierRepository panierRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public PanierService(PanierRepository panierRepository, UtilisateurRepository utilisateurRepository) {
        this.panierRepository = panierRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Panier createPanier(Utilisateur utilisateur) {
        Panier panier = new Panier();
        panier.setUtilisateur(utilisateur);
        return panierRepository.save(panier);
    }

    //public Optional<Panier> getPanierByUtilisateur(Utilisateur utilisateur) {
       // return utilisateurRepository.findByUsernameAndPassword(utilisateur.getUsername(), utilisateur.getPassword()
       // );
    //}

    public void addProduitToPanier(Panier panier, Produit produit, int quantite) {

        ProduitsPanier produitsPanier = new ProduitsPanier();
        produitsPanier.setPanier(panier);
        produitsPanier.setProduit(produit);
        produitsPanier.setQuantite(quantite);

        panier.getProduitsPanier().add(produitsPanier);

        panierRepository.save(panier);
    }

    public void deletePanier(Long panierId) {
        panierRepository.deleteById(panierId);
    }
}

