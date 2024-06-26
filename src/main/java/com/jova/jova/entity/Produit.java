package com.jova.jova.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    @NotNull
    @Size(max = 100)
    private String nom;

    @Column(name = "prix")
    @NotNull
    private double prix;

    @Column(name = "description")
    @NotNull
    @Size(max = 150)
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "poids")
    private String poids;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<ProduitsPanier> produitsPanier;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<DetailsCommande> detailsCommande;

    public Produit(String nom, double prix, String description, String type, String poids, String imageUrl) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.type = type;
        this.poids = poids;
        this.imageUrl = imageUrl;
    }

    public Produit() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ProduitsPanier> getProduitsPanier() {
        return produitsPanier;
    }

    public void setProduitsPanier(List<ProduitsPanier> produitsPanier) {
        this.produitsPanier = produitsPanier;
    }

    public List<DetailsCommande> getDetailsCommande() {
        return detailsCommande;
    }

    public void setDetailsCommande(List<DetailsCommande> detailsCommande) {
        this.detailsCommande = detailsCommande;
    }
}
