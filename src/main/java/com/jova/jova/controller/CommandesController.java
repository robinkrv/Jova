package com.jova.jova.controller;

import com.jova.jova.dao.CommandeService;
import com.jova.jova.dao.UtilisateurService;
import com.jova.jova.dao.PanierService;
import com.jova.jova.dao.impl.CommandeRepository;
import com.jova.jova.entity.Commande;
import com.jova.jova.entity.DetailsCommande;
import com.jova.jova.entity.Utilisateur;
import com.jova.jova.dao.impl.DetailsCommandeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CommandesController {

    private final CommandeService commandeService;
    private final UtilisateurService utilisateurService;
    private final DetailsCommandeRepository detailsCommandeRepository;
    private final PanierService panierService;
    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandesController(CommandeService commandeService, UtilisateurService utilisateurService, DetailsCommandeRepository detailsCommandeRepository, PanierService panierService, CommandeRepository commandeRepository) {
        this.commandeService = commandeService;
        this.utilisateurService = utilisateurService;
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.panierService = panierService;
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/paiement")
    public String afficherPagePaiement(HttpSession session, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurConnecte(session);
        model.addAttribute("utilisateur", utilisateur);
        return "paiement";
    }


    @PostMapping("/payer")
    public String effectuerPaiement(HttpSession session) {
        // Récupérer l'utilisateur connecté
        Utilisateur utilisateur = utilisateurService.getUtilisateurConnecte(session);
        // Récupérer les détails de commande depuis le panier de l'utilisateur
        List<DetailsCommande> detailsCommandeList = panierService.getDetailsCommandeFromPanier(utilisateur);
        // Créer une nouvelle commande pour cet utilisateur avec les détails de commande
        Commande commande = commandeService.creerCommande(utilisateur, detailsCommandeList);
        commandeRepository.save(commande);
        return "redirect:/validation-commande";
    }

}
