package com.jova.jova.controller;

import com.jova.jova.dao.ProduitService;
import com.jova.jova.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        return "catalogue-cafes";
    }

    @GetMapping("/catalogue-chocolats")
    public String showChocolatsPage(Model model) {
        List<Produit> produitsChocolats = produitService.getProduitsByType("chocolat");
        model.addAttribute("produitsChocolats", produitsChocolats);
        return "catalogue-chocolats";
    }

    @GetMapping("/goodies")
    public String showGoodiesPage(Model model) {
        List<Produit> produitsGoodies = produitService.getProduitsByType("goodies");
        model.addAttribute("produitsGoodies", produitsGoodies);
        return "goodies";
    }



}
