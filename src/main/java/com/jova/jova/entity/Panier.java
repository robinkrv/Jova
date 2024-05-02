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

    @Transient // Pour ne pas être persisté en base de données
    private double total;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProduitsPanier> produitsPanier;


    public Panier() {
    }

    public Panier(Utilisateur utilisateur, Date dateCreationPanier, List<ProduitsPanier> produitsPanier, double total) {
        this.utilisateur = utilisateur;
        this.dateCreationPanier = dateCreationPanier;
        this.produitsPanier = produitsPanier;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}


