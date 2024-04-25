package com.jova.jova.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "date_creation")
    private Date dateCreationPanier;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<ProduitsPanier> produitsPanier;

    // Constructeurs, getters et setters

    public Panier() {
    }

    public Panier(Utilisateur utilisateur, Date dateCreationPanier, List<ProduitsPanier> produitsPanier) {
        this.utilisateur = utilisateur;
        this.dateCreationPanier = dateCreationPanier;
        this.produitsPanier = produitsPanier;
    }

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

    public Date getDateCreationPanier() {
        return dateCreationPanier;
    }

    public void setDateCreationPanier(Date dateCreationPanier) {
        this.dateCreationPanier = dateCreationPanier;
    }

    public List<ProduitsPanier> getProduitsPanier() {
        return produitsPanier;
    }

    public void setProduitsPanier(List<ProduitsPanier> produitsPanier) {
        this.produitsPanier = produitsPanier;
    }
}


