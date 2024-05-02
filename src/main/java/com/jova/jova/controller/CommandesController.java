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

/**
 * Contrôleur gérant les opérations liées aux commandes.
 */
@Controller
public class CommandesController {

    private final CommandeService commandeService;
    private final UtilisateurService utilisateurService;
    private final DetailsCommandeRepository detailsCommandeRepository;
    private final PanierService panierService;
    private final CommandeRepository commandeRepository;

    /**
     * Constructeur du contrôleur des commandes.
     *
     * @param commandeService           Service de gestion des commandes.
     * @param utilisateurService        Service de gestion des utilisateurs.
     * @param detailsCommandeRepository Référence vers le dépôt des détails de commande.
     * @param panierService             Service de gestion du panier.
     * @param commandeRepository        Référence vers le dépôt des commandes.
     */
    @Autowired
    public CommandesController(CommandeService commandeService, UtilisateurService utilisateurService, DetailsCommandeRepository detailsCommandeRepository, PanierService panierService, CommandeRepository commandeRepository) {
        this.commandeService = commandeService;
        this.utilisateurService = utilisateurService;
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.panierService = panierService;
        this.commandeRepository = commandeRepository;
    }

    /**
     * Affiche la page de paiement.
     *
     * @param session La session HTTP.
     * @param model   Le modèle utilisé pour passer des données à la vue.
     * @return Le nom de la vue associée à la page de paiement.
     */
    @GetMapping("/paiement")
    public String afficherPagePaiement(HttpSession session, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurConnecte(session);
        if (utilisateur != null) {
            model.addAttribute("utilisateur", utilisateur);
            return "paiement";
        } else {
            return "redirect:/connexion";
        }
    }

    /**
     * Effectue le paiement d'une commande.
     *
     * @param session La session HTTP.
     * @return Le chemin de redirection après le paiement.
     */
    @PostMapping("/payer")
    public String effectuerPaiement(HttpSession session) {

        Utilisateur utilisateur = utilisateurService.getUtilisateurConnecte(session);
        if (utilisateur != null) {

            List<DetailsCommande> detailsCommandeList = panierService.getDetailsCommandeFromPanier(utilisateur);

            Commande commande = commandeService.creerCommande(utilisateur, detailsCommandeList);
            commandeRepository.save(commande);
            return "redirect:/validation-commande";
        } else {

            return "redirect:/connexion";
        }
    }

    /**
     * Affiche la page de validation de commande.
     *
     * @return Le nom de la vue associée à la page de validation de commande.
     */
    @GetMapping("/validation-commande")
    public String afficherPageValidationCommande() {
        return "validation-commande";
    }

}
