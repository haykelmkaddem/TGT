/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;


import com.esprit.pidev.tgt.entities.Paiement;
import java.awt.AWTException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author ASUS
 */
public interface IPaiementService {
    public void ajouterPaiement(Paiement p) throws SQLException;
    public boolean SupprimerPaiement( Paiement p) throws SQLException;
    public boolean ModifierPaiement( Paiement p) throws SQLException;
    public List<Paiement> AfficherPaiement() throws SQLException;
    public List<Paiement> AfficherPaiement1() throws SQLException;
    public List<Paiement> TrierPaiement() throws SQLException;
    public List<Paiement> RecherchePaiementParNomMembre(String Nom_Membre) throws SQLException;
    public void PaiementEffectue() throws AWTException;
}
