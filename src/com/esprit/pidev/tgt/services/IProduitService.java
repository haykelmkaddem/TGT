/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;


import com.esprit.pidev.tgt.entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author goldzeo
 */
public interface IProduitService {
    public int save(Produit produit) throws SQLException;
    public Produit findByUsername(String username) throws SQLException; 
    public List<Produit> findAll() throws SQLException;
    public boolean update(Produit produit) throws SQLException;
    public void delete(Produit produit) throws SQLException;
    public void deleteAll() throws SQLException;
    public Produit findByIdP(int id) throws SQLException;
    public  List<Produit> afficherProduit();
    public  List<Produit> afficherProduitb();
    public  List<Produit> triParPrix();
    public  List<Produit> rechercheParNom(String marque);
    public  List<Produit> Rechercheproduit(String nomprod) throws SQLException;
}
