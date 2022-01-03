/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Paiement {
    private int Id_Paiement;
    private int Id_Membre;
    private String Nom;
    private String prenom;
    private String Type_Paiement;
    private String Email;
    private int Num_Carte;
    private Date Date_Expiration;
    private int Code_Sec;
    private String Pays;

    public Paiement() {
    }

    public Paiement(int Id_Paiement, int Id_Membre, String Nom, String prenom, String Type_Paiement, String Email, int Num_Carte, Date Date_Expiration, int Code_Sec, String Pays) {
        this.Id_Paiement = Id_Paiement;
        this.Id_Membre = Id_Membre;
        this.Nom = Nom;
        this.prenom = prenom;
        this.Type_Paiement = Type_Paiement;
        this.Email = Email;
        this.Num_Carte = Num_Carte;
        this.Date_Expiration = Date_Expiration;
        this.Code_Sec = Code_Sec;
        this.Pays = Pays;
    }
    
    

    public Paiement(int Id_Paiement, int Id_Membre, String Type_Paiement, int Num_Carte, Date Date_Expiration, int Code_Sec, String Pays) {
        this.Id_Paiement = Id_Paiement;
        this.Id_Membre = Id_Membre;
        this.Type_Paiement = Type_Paiement;
        this.Num_Carte = Num_Carte;
        this.Date_Expiration = Date_Expiration;
        this.Code_Sec = Code_Sec;
        this.Pays = Pays;
    }
    

    public Paiement(int Id_Paiement) {
        this.Id_Paiement = Id_Paiement;
    }

    public Paiement(int Id_Paiement, String Type_Paiement, int Num_Carte, Date Date_Expiration, int Code_Sec, String Pays) {
        this.Id_Paiement = Id_Paiement;
        this.Type_Paiement = Type_Paiement;
        this.Num_Carte = Num_Carte;
        this.Date_Expiration = Date_Expiration;
        this.Code_Sec = Code_Sec;
        this.Pays = Pays;
    }

    public Paiement(String Nom, String prenom, String Type_Paiement, String Email, int Num_Carte, Date Date_Expiration, int Code_Sec, String Pays) {
        this.Nom = Nom;
        this.prenom = prenom;
        this.Type_Paiement = Type_Paiement;
        this.Email = Email;
        this.Num_Carte = Num_Carte;
        this.Date_Expiration = Date_Expiration;
        this.Code_Sec = Code_Sec;
        this.Pays = Pays;
    }

    public int getId_Paiement() {
        return Id_Paiement;
    }

    public int getId_Membre() {
        return Id_Membre;
    }

    public String getType_Paiement() {
        return Type_Paiement;
    }

    public int getNum_Carte() {
        return Num_Carte;
    }

    public Date getDate_Expiration() {
        return Date_Expiration;
    }

    public int getCode_Sec() {
        return Code_Sec;
    }

    public String getPays() {
        return Pays;
    }

    public void setId_Paiement(int Id_Paiement) {
        this.Id_Paiement = Id_Paiement;
    }

    public void setId_Membre(int Id_Membre) {
        this.Id_Membre = Id_Membre;
    }

    public void setType_Paiement(String Type_Paiement) {
        this.Type_Paiement = Type_Paiement;
    }

    public void setNum_Carte(int Num_Carte) {
        this.Num_Carte = Num_Carte;
    }

    public void setDate_Expiration(Date Date_Expiration) {
        this.Date_Expiration = Date_Expiration;
    }

    public void setCode_Sec(int Code_Sec) {
        this.Code_Sec = Code_Sec;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.Id_Paiement;
        hash = 67 * hash + this.Id_Membre;
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
        final Paiement other = (Paiement) obj;
        if (this.Id_Paiement != other.Id_Paiement) {
            return false;
        }
        if (this.Id_Membre != other.Id_Membre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paiement{" + "Id_Paiement=" + Id_Paiement + ", Id_Membre=" + Id_Membre + ", Nom=" + Nom + ", prenom=" + prenom + ", Type_Paiement=" + Type_Paiement + ", Email=" + Email + ", Num_Carte=" + Num_Carte + ", Date_Expiration=" + Date_Expiration + ", Code_Sec=" + Code_Sec + ", Pays=" + Pays + '}';
    }

    

    
    
    
    
    
   
    
    
    
}
