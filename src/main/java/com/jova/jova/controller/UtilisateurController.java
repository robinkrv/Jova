package com.jova.jova.controller;

import com.jova.jova.dao.UtilisateurService;
import com.jova.jova.entity.Utilisateur;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/inscription")
    public String afficherPageInscription(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String inscriptionUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {
        Utilisateur newUser = utilisateurService.inscrireUtilisateur(utilisateur);
        if (newUser != null) {
            model.addAttribute("message", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
        } else {
            model.addAttribute("message", "L'inscription a échoué. Veuillez réessayer.");
        }
        return "redirect:/connexion";
    }





    @GetMapping("/connexion")
    public String afficherPageConnexion(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "connexion";
    }

    @PostMapping("/connexion")
    public String connecterUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, HttpSession session) {
        Optional<Utilisateur> utilisateurOpt = utilisateurService.connecterUtilisateur(utilisateur.getUsername(), utilisateur.getPassword());
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateurConnecte = utilisateurOpt.get();
            session.setAttribute("utilisateurConnecte", utilisateurConnecte);
            model.addAttribute("utilisateurConnecte", utilisateurConnecte);

            model.addAttribute("utilisateurConnecte", utilisateurConnecte.getUsername());
            model.addAttribute("message", "Vous êtes connecté !");
            return "redirect:/";
        } else {
            model.addAttribute("erreurMessage", "L'identifiant et le mot de passe ne correspondent pas.");
            return "connexion";
        }
    }
    @GetMapping("/deconnexion")
    public String deconnecterUtilisateur(HttpSession session) {
        utilisateurService.deconnecterUtilisateur(session);
        return "redirect:/";
    }
}
