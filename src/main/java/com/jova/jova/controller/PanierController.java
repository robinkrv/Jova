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

        Panier panier = (Panier) session.getAttribute("panier");

        if (panier != null) {

            model.addAttribute("panier", panier);
        }


        return "panier";
    }


    @PostMapping("/ajouter-au-panier")
    @ResponseBody
    public String ajouterAuPanier(@RequestParam("idProduit") Long idProduit,
                                  @RequestParam("quantite") int quantite,
                                  HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
            panier = panierService.createPanier(utilisateur);
            session.setAttribute("panier", panier);
        }

        Produit produit = produitService.getProduitById(idProduit).orElse(null);

        if (produit != null) {
            panierService.addProduitToPanier(panier, produit, quantite);
            return "Produit ajouté au panier avec succès.";
        } else {
            return "Erreur : Produit non trouvé.";
        }
    }

    @PostMapping("/retirer-du-panier")
    @ResponseBody
    public String retirerDuPanier(@RequestParam("idProduit") Long idProduit, HttpSession session) {

        Panier panier = (Panier) session.getAttribute("panier");
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
