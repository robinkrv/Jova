package com.jova.jova.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produits_panier")
public class ProduitsPanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_panier")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    @Column(name = "quantite")
    @NotNull
    private int quantite;

    // Constructeurs

    public ProduitsPanier() {
    }

    public ProduitsPanier(Panier panier, Produit produit, int quantite) {
        this.panier = panier;
        this.produit = produit;
        this.quantite = quantite;
    }

    // Getters et Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

