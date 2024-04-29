package com.jova.jova.controller;

import com.jova.jova.dao.UtilisateurService;
import com.jova.jova.entity.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/inscription")
    public String inscriptionUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
        utilisateurService.inscrireUtilisateur(utilisateur);
        return "redirect:/";
    }
}
