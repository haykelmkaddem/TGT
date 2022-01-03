/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import javafx.scene.control.Button;

/**
 *
 * @author ASUS
 */
public class ListeProduit {
   private int id_produit;
   private String nom;
   private Double prix;
   private int quantite;
   private Button supprimer;
   private Button update;
   
   

    public ListeProduit() {
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    
    
    public Button getSupprimer() {
        return supprimer;
    }

    public Button getUpdate() {
        return update;
    }

    public ListeProduit(String nom, Double prix, int quantite, Button supprimer, Button update) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.supprimer = supprimer;
        this.update = update;
    }



    public ListeProduit(String nom, Double prix, int quantite) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }


    public ListeProduit(int id_produit, String nom, Double prix, int quantite) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    public ListeProduit(int id_produit, Double prix, int quantite) {
        this.id_produit = id_produit;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId_produit() {
        return id_produit;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_produit;
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
        final ListeProduit other = (ListeProduit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ListeProduit{" + "id_produit=" + id_produit + ", prix=" + prix + ", quantite=" + quantite + '}';
    }
   
    
    
}
