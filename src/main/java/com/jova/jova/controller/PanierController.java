package com.jova.jova.controller;

import com.jova.jova.dao.PanierService;
import com.jova.jova.dao.ProduitService;
import com.jova.jova.entity.Panier;
import com.jova.jova.entity.Produit;
import com.jova.jova.entity.ProduitsPanier;
import com.jova.jova.entity.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PanierController {

    private final PanierService panierService;
    private final ProduitService produitService;

    @Autowired
    public PanierController(PanierService panierService, ProduitService produitService) {
        this.panierService = panierService;
        this.produitService = produitService;
    }

    @GetMapping("/panier")
    public String afficherPanier(Model model, HttpSession session) {
        // Récupérer le panier de la session
        Panier panier = (Panier) session.getAttribute("panier");

        // Vérifier si le panier est vide
        if (panier != null) {
            // Calculer le total du panier

            // Ajouter le panier et le total au modèle pour l'afficher dans la vue
            model.addAttribute("panier", panier);
        }

        // Retourner le nom de la vue HTML du panier
        return "panier";
    }


    @PostMapping("/ajouter-au-panier")
    @ResponseBody
    public String ajouterAuPanier(@RequestParam("idProduit") Long idProduit,
                                  @RequestParam("quantite") int quantite,
                                  HttpSession session) {
        // Vérifier si le panier existe déjà dans la session
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            // Si le panier n'existe pas, créez un nouveau panier pour l'utilisateur
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
            panier = panierService.createPanier(utilisateur);
            // Enregistrez le nouveau panier dans la session
            session.setAttribute("panier", panier);
        }

        // Récupérer le panier et le produit à partir de leur identifiant
        Produit produit = produitService.getProduitById(idProduit).orElse(null);

        // Vérifier si le produit existe
        if (produit != null) {
            // Ajouter le produit au panier
            panierService.addProduitToPanier(panier, produit, quantite);
            return "Produit ajouté au panier avec succès.";
        } else {
            return "Erreur : Produit non trouvé.";
        }
    }

    @PostMapping("/retirer-du-panier")
    @ResponseBody
    public String retirerDuPanier(@RequestParam("idProduit") Long idProduit, HttpSession session) {
        // Récupérer le panier de la session
        Panier panier = (Panier) session.getAttribute("panier");

        // Récupérer le produit à partir de son identifiant
        Produit produit = produitService.getProduitById(idProduit).orElse(null);

        // Récupérer le ProduitsPanier correspondant à ce produit dans le panier
        //ProduitsPanier produitsPanier = null;
        //if (panier != null && produit != null) {
        // for (ProduitsPanier produits : panier.getProduitsPanier()) {
        // if (produits.getProduit().equals(produit)) {
        // produitsPanier = produits;
        //break;
        //}
        // }
        // }

        // Vérifier si le panier et le ProduitsPanier existent

        // Appeler la méthode pour retirer le ProduitsPanier du panier
        panierService.removeProduitFromPanier(panier, produit);
        // Retourner un message de succès
        return "success";
    }




}
