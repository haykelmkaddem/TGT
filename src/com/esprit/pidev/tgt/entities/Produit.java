/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import java.util.Objects;

/**
 *
 * @author Haykel M'kaddem
 */
public class Produit {
    
    private int id;
    private CategorieProduit categorie_id;
    private String nom;
    private String description;
    private Float prix;
    private int quantite;
    private String image;
    private String nomcategorie;

    public Produit(int id, CategorieProduit categorie_id, String nom, String description, Float prix, int quantite, String image) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.nomcategorie= this.categorie_id.getNomcategorie();
    }

    public Produit(int id, String nom, String description, Float prix, int quantite, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.nomcategorie = nomcategorie;
    }
    
    
    public Produit(int id,String nom,Float prix, int quantite, String description,  String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategorieProduit getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(CategorieProduit categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie_id=" + categorie_id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", quantite=" + quantite + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.categorie_id);
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.prix);
        hash = 53 * hash + this.quantite;
        hash = 53 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.categorie_id, other.categorie_id)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
