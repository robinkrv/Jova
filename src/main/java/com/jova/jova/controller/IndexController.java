package com.jova.jova.controller;

import com.jova.jova.dao.ProduitService;
import com.jova.jova.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final ProduitService produitService;

    @Autowired
    public IndexController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Produit> produits = produitService.getAllProduits(); // Récupère tous les produits depuis le service
        model.addAttribute("produits", produits); // Ajoute la liste des produits au modèle avec le nom "produits"
        return "index"; // Retourne le nom de la vue HTML
    }
}
