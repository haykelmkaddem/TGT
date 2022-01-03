/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import java.sql.Date;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class CommandeP {
    private int Id_Commande;
    private String Nom;
    private String Prenom;
    private Date Date_Commande;
    private Double Prix_Total;
    private int Id_Membre;

    
    

    public CommandeP() {
        
    }
    
    public CommandeP(int Id_Commande,Date Date_Commande, Double Prix_Total , int Id_Membre) {
        this.Id_Commande=Id_Commande;
        this.Date_Commande = Date_Commande;
        this.Prix_Total = Prix_Total;
        this.Id_Membre = Id_Membre;
    }

    public CommandeP(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

    public CommandeP(int Id_Commande,Date Date_Commande ,Double Prix_Total) {
        this.Id_Commande = Id_Commande;
        this.Date_Commande = Date_Commande;
        this.Prix_Total = Prix_Total;
    }
    
    public CommandeP(Date Date_Commande, Double Prix_Total , int Id_Membre) {
        
        this.Date_Commande = Date_Commande;
        this.Prix_Total = Prix_Total;
        this.Id_Membre = Id_Membre;
    }

    public CommandeP(int Id_Commande, String Nom, String Prenom, Date Date_Commande, Double Prix_Total, int Id_Membre) {
        this.Id_Commande = Id_Commande;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Date_Commande = Date_Commande;
        this.Prix_Total = Prix_Total;
        this.Id_Membre = Id_Membre;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public int getId_Commande() {
        return Id_Commande;
    }

    public Date getDate_Commande() {
        return Date_Commande;
    }

    public Double getPrix_Total() {
        return Prix_Total;
    }

    public void setId_Commande(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

    public void setDate_Commande(Date Date_Commande) {
        this.Date_Commande = Date_Commande;
    }

    @Override
    public String toString() {
        return "Commande{" + "Id_Commande=" + Id_Commande + ", Date_Commande=" + Date_Commande + ", Prix_Total=" + Prix_Total + ", Id_Membre=" + Id_Membre + '}';
    }

    public void setPrix_Total(Double Prix_Total) {
        this.Prix_Total = Prix_Total;
    }
    
    public void setId_Membre(int Id_Membre) {
        this.Id_Membre = Id_Membre;
    }

    public int getId_Membre() {
        return Id_Membre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.Id_Commande;
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
        final CommandeP other = (CommandeP) obj;
        if (this.Id_Commande != other.Id_Commande) {
            return false;
        }
        return true;
    }
    
    
}
