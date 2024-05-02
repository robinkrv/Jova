package com.jova.jova.dao;

import com.jova.jova.dao.impl.CommandeRepository;
import com.jova.jova.dao.impl.DetailsCommandeRepository;
import com.jova.jova.entity.Commande;
import com.jova.jova.entity.DetailsCommande;
import com.jova.jova.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final DetailsCommandeRepository detailsCommandeRepository;
    private final PanierService panierService;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository,DetailsCommandeRepository detailsCommandeRepository,PanierService panierService) {
        this.commandeRepository = commandeRepository;
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.panierService = panierService;

    }

    // Créer une nouvelle commande
    // Créer une nouvelle commande avec des détails de commande
    public Commande creerCommande(Utilisateur utilisateur, List<DetailsCommande> detailsCommandeList) {
        // Créer une nouvelle commande
        Commande commande = new Commande();
        commande.setUtilisateur(utilisateur);
        commande.setDateCommande(new Date());
        commande.setStatut("En attente"); // Par défaut, la commande est en attente
        commande.setNumeroCommande(genererNumeroCommande()); // Générer un numéro de commande unique

        // Associer les détails de commande à la commande
        for (DetailsCommande detailCommande : detailsCommandeList) {
            detailCommande.setCommande(commande);
        }

        // Enregistrer la commande dans la base de données
        commande = commandeRepository.save(commande);

        // Enregistrer les détails de commande dans la base de données
        ajouterDetailsCommande(commande, detailsCommandeList);
        panierService.viderPanier(utilisateur);
        return commande;
    }




    // Générer un numéro de commande unique
    private long genererNumeroCommande() {
        long numeroCommande = (long) (Math.random() * 1000000000) + 100000000;
        // Vérifier si le numéro de commande est déjà utilisé
        while (commandeRepository.existsByNumeroCommande(numeroCommande)) {
            numeroCommande = (long) (Math.random() * 1000000000) + 100000000;
        }
        return numeroCommande;
    }


    // Mettre à jour le statut d'une commande
    public void mettreAJourStatutCommande(Commande commande, String nouveauStatut) {
        commande.setStatut(nouveauStatut);
        commandeRepository.save(commande);
    }

    // Ajouter des détails de commande à une commande existante
    public void ajouterDetailsCommande(Commande commande, List<DetailsCommande> detailsCommandeList) {
        // Associer les nouveaux détails de commande à la commande existante
        for (DetailsCommande detailCommande : detailsCommandeList) {
            detailCommande.setCommande(commande);
        }
        // Enregistrer les nouveaux détails de commande dans la base de données
        detailsCommandeRepository.saveAll(detailsCommandeList);
    }

    // Supprimer une commande
    public void supprimerCommande(Commande commande) {
        commandeRepository.delete(commande);
    }
}

