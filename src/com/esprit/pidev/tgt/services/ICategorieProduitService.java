/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.CategorieProduit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author goldzeo
 */
public interface ICategorieProduitService {
    public int save(CategorieProduit categorieproduit) throws SQLException;
    public CategorieProduit findByUsername(String username) throws SQLException; 
    public List<CategorieProduit> findAll() throws SQLException;
    public boolean update(CategorieProduit categorieproduit) throws SQLException;
    public void delete(CategorieProduit categorieproduit) throws SQLException;
    public void deleteAll() throws SQLException;
    public CategorieProduit findById(int id) throws SQLException;
    public  List<CategorieProduit> Recherchecat(String nomcat) throws SQLException;
}
