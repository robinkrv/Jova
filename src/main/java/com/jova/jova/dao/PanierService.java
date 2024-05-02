package com.jova.jova.dao;

import com.jova.jova.dao.impl.PanierRepository;
import com.jova.jova.dao.impl.ProduitRepository;
import com.jova.jova.dao.impl.UtilisateurRepository;
import com.jova.jova.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations liées aux paniers.
 */
@Service
public class PanierService {

    private final PanierRepository panierRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ProduitRepository produitRepository;

    /**
     * Constructeur pour l'injection de dépendances.
     *
     * @param panierRepository      Repository pour les opérations sur les paniers.
     * @param utilisateurRepository Repository pour les opérations sur les utilisateurs.
     */
    @Autowired
    public PanierService(PanierRepository panierRepository, UtilisateurRepository utilisateurRepository,ProduitRepository produitRepository) {
        this.panierRepository = panierRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.produitRepository = produitRepository;
    }

    /**
     * Créer un nouveau panier pour un utilisateur.
     *
     * @param utilisateur L'utilisateur pour lequel créer le panier.
     * @return Le nouveau panier créé ou le panier existant de l'utilisateur.
     */
    public Panier createPanier(Utilisateur utilisateur) {
        if (utilisateur.getPanier() == null) {
            Panier panier = new Panier();
            panier.setProduitsPanier(new ArrayList<>());
            utilisateur.setPanier(panier);
            panier.setUtilisateur(utilisateur);
            panierRepository.save(panier);
            return panier;
        } else {
            return utilisateur.getPanier();
        }
    }

    /**
     * Récupérer un panier par son identifiant.
     *
     * @param idPanier L'identifiant du panier à récupérer.
     * @return Le panier correspondant à l'identifiant, s'il existe.
     */
    public Panier getPanierById(Long idPanier) {
        return panierRepository.findById(idPanier).orElse(null);
    }

    /**
     * Ajouter un produit à un panier avec une quantité spécifiée.
     *
     * @param panier   Le panier auquel ajouter le produit.
     * @param produit  Le produit à ajouter.
     * @param quantite La quantité du produit à ajouter.
     */
    public void addProduitToPanier(Panier panier, Produit produit, int quantite) {

        ProduitsPanier produitsPanier = new ProduitsPanier();
        produitsPanier.setPanier(panier);
        produitsPanier.setProduit(produit);
        produitsPanier.setQuantite(quantite);

        panier.getProduitsPanier().add(produitsPanier);

        panierRepository.save(panier);
    }

    /**
     * Calculer le total du panier en fonction des produits et de leur quantité.
     *
     * @param panier Le panier pour lequel calculer le total.
     * @return Le total du panier.
     */
    public double calculerTotalPanier(Panier panier) {
        double total = 0;
        for (ProduitsPanier produitsPanier : panier.getProduitsPanier()) {
            total += produitsPanier.getProduit().getPrix() * produitsPanier.getQuantite();
        }
        return total;
    }
    /**
     * Récupérer les détails de commande à partir du panier de l'utilisateur.
     *
     * @param utilisateur L'utilisateur dont récupérer le panier et les détails de commande.
     * @return La liste des détails de commande à partir du panier de l'utilisateur.
     */
    public List<DetailsCommande> getDetailsCommandeFromPanier(Utilisateur utilisateur) {
        List<DetailsCommande> detailsCommandeList = new ArrayList<>();
        Panier panier = utilisateur.getPanier();

        if (panier != null) {
            for (ProduitsPanier produitsPanier : panier.getProduitsPanier()) {
                DetailsCommande detailsCommande = new DetailsCommande();
                detailsCommande.setProduit(produitsPanier.getProduit());
                detailsCommande.setQuantite(produitsPanier.getQuantite());

                detailsCommandeList.add(detailsCommande);
            }
        }

        return detailsCommandeList;
    }
    /**
     * Supprimer un panier par son identifiant.
     *
     * @param panierId L'identifiant du panier à supprimer.
     */
    public void deletePanier(Long panierId) {
        panierRepository.deleteById(panierId);
    }

    /**
     * Supprimer un produit du panier.
     *
     * @param panier  Le panier duquel supprimer le produit.
     * @param produit Le produit à supprimer.
     */
    public void removeProduitFromPanier(Panier panier, Produit produit) {
        for (ProduitsPanier produitsPanier : panier.getProduitsPanier()) {

            if (produitsPanier.getProduit().equals(produit)) {

                panier.getProduitsPanier().remove(produitsPanier);
                break;
            }
        }


        panierRepository.save(panier);
    }

    /**
     * Vider le panier de l'utilisateur en supprimant tous les produits.
     *
     * @param utilisateur L'utilisateur dont le panier doit être vidé.
     */
    public void viderPanier(Utilisateur utilisateur) {
        Panier panier = utilisateur.getPanier();
        panier.getProduitsPanier().clear();
        panierRepository.save(panier);
    }
}
