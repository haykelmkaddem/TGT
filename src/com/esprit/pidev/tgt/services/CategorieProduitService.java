/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.CategorieProduit;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Entretien;
import com.esprit.pidev.tgt.enumeration.Role;
import com.esprit.pidev.tgt.utils.DataBase;
import com.esprit.pidev.tgt.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author goldzeo
 */
public class CategorieProduitService  implements ICategorieProduitService{
    
    private Statement statement;
    
         Connection  myConnex;
      Statement ste;
    public CategorieProduitService() {
        try {
            
            myConnex = DataBase.getInstance().getConnection();
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int save(CategorieProduit categorieproduit) throws SQLException {
        String req = "INSERT INTO `categorie` (`id`, `nomcategorie`) VALUES (NULL, '"+categorieproduit.getNomcategorie()+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }
    
     @Override
    public CategorieProduit findById(int id) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM `categorie` WHERE id=" + id);
        return result.next()? extract(result) : null;
    }

    @Override
    public CategorieProduit findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<CategorieProduit> findAll() throws SQLException {
        List<CategorieProduit> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `categorie`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    private CategorieProduit extract(ResultSet result)throws SQLException{
        
            int id = result.getInt("id");
            String nomcategorie = result.getString("nomcategorie");
            
        return new CategorieProduit(id, nomcategorie);
    }



    @Override
    public boolean update(CategorieProduit categorieproduit) throws SQLException {
        String reqUpdate="UPDATE `categorie` SET `id`='"+categorieproduit.getId()+"',`nomcategorie`='"+categorieproduit.getNomcategorie()+"' WHERE `id` ='"+categorieproduit.getId()+"'";
         return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CategorieProduit categorieproduit) throws SQLException {
          statement.executeUpdate("DELETE FROM `categorie` WHERE id =" + categorieproduit.getId()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<CategorieProduit> Recherchecat(String nomcat) throws SQLException {
        List<CategorieProduit> arr1=new ArrayList<>();
    statement=myConnex.createStatement();
    ResultSet rs=statement.executeQuery("select * from categorie where nomcategorie='"+nomcat+"'");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String nomc=rs.getString("nomcategorie");
               CategorieProduit c=new CategorieProduit( id,nomc);
               arr1.add(c);
     }
    return arr1;
    }
    
}
