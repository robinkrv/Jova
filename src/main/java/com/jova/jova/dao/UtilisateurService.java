package com.jova.jova.dao;

import com.jova.jova.dao.impl.UtilisateurRepository;
import com.jova.jova.entity.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations liées aux utilisateurs.
 */
@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    /**
     * Constructeur pour l'injection de dépendances.
     * @param utilisateurRepository Repository pour les opérations sur les utilisateurs.
     */
    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Inscrire un nouvel utilisateur.
     * @param utilisateur L'utilisateur à inscrire.
     * @return L'utilisateur inscrit ou null s'il est déjà inscrit.
     */
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByUsernameOrMail(utilisateur.getUsername(), utilisateur.getMail())) {
            System.out.println("Vous êtes déjà inscrits sur Jova");
            return null;
        } else {
            return utilisateurRepository.save(utilisateur);
        }
    }

    /**
     * Connecter un utilisateur avec un nom d'utilisateur et un mot de passe.
     * @param nomUtilisateur Le nom de l'utilisateur.
     * @param motDePasse Le mot de passe de l'utilisateur.
     * @return L'utilisateur connecté, s'il existe.
     */
    public Optional<Utilisateur> connecterUtilisateur(String nomUtilisateur, String motDePasse) {
        return utilisateurRepository.findByUsernameAndPassword(nomUtilisateur, motDePasse);
    }

    /**
     * Obtenir un utilisateur par son identifiant.
     * @param idUtilisateur L'identifiant de l'utilisateur.
     * @return L'utilisateur correspondant à l'identifiant.
     */
    public Optional<Utilisateur> getUtilisateurById(Long idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur);
    }

    /**
     * Mettre à jour les informations d'un utilisateur.
     * @param utilisateur L'utilisateur à mettre à jour.
     * @return L'utilisateur mis à jour ou null s'il n'existe pas.
     */
    public Utilisateur mettreAJourUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(utilisateur.getId())) {
            return utilisateurRepository.save(utilisateur);
        } else {
            return null;
        }
    }

    /**
     * Supprimer un utilisateur par son identifiant.
     * @param idUtilisateur L'identifiant de l'utilisateur à supprimer.
     */
    public void supprimerUtilisateur(Long idUtilisateur) {
        utilisateurRepository.deleteById(idUtilisateur);
    }

    /**
     * Vérifier si un utilisateur existe déjà par son nom d'utilisateur ou son adresse e-mail.
     * @param nomUtilisateur Le nom de l'utilisateur.
     * @param mail L'adresse e-mail de l'utilisateur.
     * @return true si l'utilisateur existe, sinon false.
     */
    public boolean existeUtilisateur(String nomUtilisateur, String mail) {
        return utilisateurRepository.existsByUsernameOrMail(nomUtilisateur, mail);
    }

    /**
     * Récupérer la liste de tous les utilisateurs.
     * @return La liste de tous les utilisateurs.
     */
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
    /**
     * Récupérer l'utilisateur connecté à partir de la session HTTP.
     * @param session La session HTTP.
     * @return L'utilisateur connecté, s'il existe, sinon null.
     */
    public Utilisateur getUtilisateurConnecte(HttpSession session) {

        Long idUtilisateur = (Long) session.getAttribute("idUtilisateurConnecte");
        if (idUtilisateur != null) {

            return utilisateurRepository.findById(idUtilisateur).orElse(null);
        }
        return null;
    }

    /**
     * Déconnecter un utilisateur en supprimant l'attribut de session "utilisateurConnecte".
     * @param session La session de l'utilisateur à déconnecter.
     */
    public void deconnecterUtilisateur(HttpSession session) {
        session.removeAttribute("utilisateurConnecte");
    }
}
