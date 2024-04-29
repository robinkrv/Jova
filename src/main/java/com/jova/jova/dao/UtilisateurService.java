package com.jova.jova.dao;

import com.jova.jova.dao.impl.UtilisateurRepository;
import com.jova.jova.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // Inscription d'un nouvel utilisateur
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByUsernameOrMail(utilisateur.getUsername(), utilisateur.getMail())) {
            System.out.println("Vous êtes déjà inscrits sur Jova");
            return null;
        } else {
            return utilisateurRepository.save(utilisateur);
        }
    }

    // Connexion d'un utilisateur existant
    public Optional<Utilisateur> connecterUtilisateur(String nomUtilisateur, String motDePasse) {
        return utilisateurRepository.findByUsernameAndPassword(nomUtilisateur, motDePasse);
    }

    // Récupération des informations d'un utilisateur par son identifiant
    public Optional<Utilisateur> getUtilisateurById(Long idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur);
    }

    // Mise à jour des informations d'un utilisateur
    public Utilisateur mettreAJourUtilisateur(Utilisateur utilisateur) {
        // Vérifier si l'utilisateur existe déjà
        if (utilisateurRepository.existsById(utilisateur.getId())) {
            // Mettre à jour les informations de l'utilisateur dans la base de données
            return utilisateurRepository.save(utilisateur);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
            // Par exemple, lever une exception ou retourner un résultat indiquant que l'utilisateur n'existe pas
            // Dans cet exemple, je renvoie null
            return null;
        }
    }

    // Suppression d'un utilisateur
    public void supprimerUtilisateur(Long idUtilisateur) {
        utilisateurRepository.deleteById(idUtilisateur);
    }

    // Vérification de l'existence d'un utilisateur
    public boolean existeUtilisateur(String nomUtilisateur, String mail) {
        return utilisateurRepository.existsByUsernameOrMail(nomUtilisateur, mail);
    }

    // Récupération de tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}

