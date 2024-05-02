package com.jova.jova.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utilisateur",uniqueConstraints = @UniqueConstraint(columnNames = {"mail","username"}))
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    @NotNull
    @Size(max = 50)
    private String nom;

    @Column(name = "prénom")
    @NotNull
    @Size(max = 50)
    private String prenom;

    @Column(name = "mail")
    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Adresse e-mail invalide")
    private String mail;

    @Column(name = "adresse")
    @NotNull
    @Size(max = 150)
    private String adresse;

    @Column(name = "username")
    @NotNull
    @Size(max = 30, message = "Le nom d'utilisateur ne peut comporter plus de 30 caractères")
    private String username;

    @Column(name = "password")
    @NotNull
    //@Size(min = 1, max = 200, message = "Le mot de passe doit comporter entre 6 et 50 caractères")
    private String password;

    @Column(name = "date_creation_compte")
    private Date dateCreationCompte;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Panier panier;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Commande> commandes;

    public Utilisateur(String nom, String prenom, String mail, String username, String adresse, String password, Date dateCreationCompte, Panier panier, List<Commande> commandes) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.username = username;
        this.adresse = adresse;
        this.password = password;
        this.dateCreationCompte = dateCreationCompte;
        this.panier = panier;
        this.commandes = commandes;
    }

    public Utilisateur() {

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreationCompte() {
        return dateCreationCompte;
    }

    public void setDateCreationCompte(Date dateCreationCompte) {
        this.dateCreationCompte = dateCreationCompte;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}

