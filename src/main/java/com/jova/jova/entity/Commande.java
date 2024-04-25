package com.jova.jova.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande",uniqueConstraints = @UniqueConstraint(columnNames = {"numero_commande"}))
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @Column(name = "date_commande")
    private Date dateCommande;

    @Column(name = "statut")
    private String statut;

    @Column(name = "numero_commande")
    private long numeroCommande;

    // Constructeurs
    public Commande() {
    }

    public Commande(Utilisateur utilisateur, Date dateCommande, String statut, long numeroCommande) {
        this.utilisateur = utilisateur;
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.numeroCommande = numeroCommande;
    }

    // Getters et setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public long getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(long numeroCommande) {
        this.numeroCommande = numeroCommande;
    }
}

