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


    public Commande creerCommande(Utilisateur utilisateur, List<DetailsCommande> detailsCommandeList) {

        Commande commande = new Commande();
        commande.setUtilisateur(utilisateur);
        commande.setDateCommande(new Date());
        commande.setStatut("En attente");
        commande.setNumeroCommande(genererNumeroCommande());


        for (DetailsCommande detailCommande : detailsCommandeList) {
            detailCommande.setCommande(commande);
        }

        commande = commandeRepository.save(commande);

        ajouterDetailsCommande(commande, detailsCommandeList);
        panierService.viderPanier(utilisateur);
        return commande;
    }


    private long genererNumeroCommande() {
        long numeroCommande = (long) (Math.random() * 1000000000) + 100000000;

        while (commandeRepository.existsByNumeroCommande(numeroCommande)) {
            numeroCommande = (long) (Math.random() * 1000000000) + 100000000;
        }
        return numeroCommande;
    }



    public void mettreAJourStatutCommande(Commande commande, String nouveauStatut) {
        commande.setStatut(nouveauStatut);
        commandeRepository.save(commande);
    }


    public void ajouterDetailsCommande(Commande commande, List<DetailsCommande> detailsCommandeList) {

        for (DetailsCommande detailCommande : detailsCommandeList) {
            detailCommande.setCommande(commande);
        }

        detailsCommandeRepository.saveAll(detailsCommandeList);
    }

    public void supprimerCommande(Commande commande) {
        commandeRepository.delete(commande);
    }
}

