/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;



import com.esprit.pidev.tgt.entities.CommandeP;
import com.esprit.pidev.tgt.entities.ListeProduit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ICommandePService {
    public void ajouterCommande(CommandeP c) throws SQLException;
    public void ajouterCommande1(CommandeP c) throws SQLException;
    public boolean SupprimerCommande(CommandeP c) throws SQLException;
    public boolean ModifierCommande(CommandeP c) throws SQLException;
    public List<CommandeP> AfficherCommande() throws SQLException;
    public List<CommandeP> TrierCommande() throws SQLException;
    public List<CommandeP> RechercheCommandeParIdMembre(int Id_Membre) throws SQLException;
    public List<CommandeP> RechercheCommandeParNomMembre(String Nom_Membre) throws SQLException;
    public double CalculerTotalCommande(List<ListeProduit> L);
//    public void FacturePdf(int id_membre) throws SQLException,FileNotFoundException,DocumentException,IOException;
    
    
    
}
