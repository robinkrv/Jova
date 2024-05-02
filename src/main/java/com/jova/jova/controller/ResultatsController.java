package com.jova.jova.controller;

import com.jova.jova.dao.ProduitService;
import com.jova.jova.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ResultatsController {

    private final ProduitService produitService;

    @Autowired
    public ResultatsController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/resultats")
    public String showResultatsPage(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {

            List<Produit> produitsResultats = produitService.rechercherProduits(query);
            if (produitsResultats.isEmpty()) {

                model.addAttribute("aucunProduitTrouve", true);
            } else {
                model.addAttribute("produitsResultats", produitsResultats);
            }
        }

        return "resultats";
    }
}

