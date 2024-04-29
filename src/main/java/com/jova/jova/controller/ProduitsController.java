package com.jova.jova.controller;

import com.jova.jova.dao.ProduitService;
import com.jova.jova.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProduitsController {

    private final ProduitService produitService;

    @Autowired
    public ProduitsController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/catalogue-cafes")
    public String showCafesPage(Model model) {
        List<Produit> produitsCafes = produitService.getProduitsByType("cafe");
        model.addAttribute("produitsCafes", produitsCafes);
        return "catalogue-cafes"; // Assuming you have a Thymeleaf template named "catalogue-cafes.html"
    }

    @GetMapping("/catalogue-chocolats")
    public String showChocolatsPage(Model model) {
        List<Produit> produitsChocolats = produitService.getProduitsByType("chocolat");
        model.addAttribute("produitsChocolats", produitsChocolats);
        return "catalogue-chocolats";
    }

    @GetMapping("/goodies")
    public String showGoodiesPage(Model model) {
        List<Produit> produitsGoodies = produitService.getProduitsByType("goodie");
        model.addAttribute("produitsGoodies", produitsGoodies);
        return "goodies"; // Assuming you have a Thymeleaf template named "goodies.html"
    }
}
