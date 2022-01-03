/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.utils.*;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;



import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author islem
 */
public class EventsService implements IEventsService {

  
   private Statement statement;
    public EventsService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
        }
    }
    
     @Override
    public int save(Events events) throws SQLException {
        String req = "INSERT INTO `events` (`id`,`TitreEvents`, `DescriptionEvents`, `ThemeEvents`,`AdresseEvents`,`DateEvents`,`prixEvents`,`ImageEvents`) VALUES (NULL, '"+events.getTitreEvents()+"', '"+events.getDescriptionEvents()+"','"+events.getThemeEvents()+"', '"+events.getAdresseEvents()+"', '"+events.getDateEvents()+"', '"+events.getPrixEvents()+"', '"+events.getImageEvents()+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }
    
    @Override
    public Events findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Events findById(int id) throws SQLException{
       
        ResultSet result = statement.executeQuery("SELECT * FROM `events` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }
    
    @Override
    public List<Events> findAll() throws SQLException {
        List<Events> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `events`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    private Events extract(ResultSet result)throws SQLException{
           int id = result.getInt("id");
           String TitreEvents = result.getString("TitreEvents");
                      String DescriptionEvents = result.getString("DescriptionEvents");
                      String ThemeEvents = result.getString("ThemeEvents");
                      String AdresseEvents = result.getString("AdresseEvents");
                      LocalDateTime DateEvents= result.getTimestamp("DateEvents").toLocalDateTime();
                      int PrixEvents = result.getInt("PrixEvents");
                      String ImageEvents = result.getString("ImageEvents");
                      
           
            return new Events(id, TitreEvents,DescriptionEvents,ThemeEvents,AdresseEvents,DateEvents,PrixEvents,ImageEvents);
    }
    
  @Override
    public void modifierEvents(Events E) {
        try {
            String requete = "UPDATE casting SET TitreEvents=?,DescriptionEvents=?,ThemeEvents=?,AdresseEvents=?,prixEvents=?,ImageEvents=? WHERE id=?";
           PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1,E.getTitreEvents());
           preparedStatement.setString(2,E.getDescriptionEvents());
           preparedStatement.setString(3,E.getThemeEvents());           
            preparedStatement.setString(4,E.getAdresseEvents());
           preparedStatement.setInt(5,E.getPrixEvents());
           preparedStatement.setString(6,E.getImageEvents());
            preparedStatement.setInt(7, E.getId());
            preparedStatement.executeUpdate();
            System.out.println("Events modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      @Override
    public void supprimerEvents(Events E) throws SQLException  {
                       statement.executeUpdate("DELETE FROM `events` WHERE id =" + E.getId()); 

    }
    
    @Override
    public List<Events> Recherche(String val) throws SQLException {
        List<Events> list = new ArrayList<>();
       String requete = "SELECT * FROM `events` WHERE TitreEvents like ?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1,"%"+val+"%");
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
            System.out.println("wsetl service");
            System.out.println(val);
            
        while (result.next()) {
            System.out.println("while");
            list.add(extract(result));
        }
        return list;
    }
    
    public List<Events> trieParDate(){
        List<Events> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM events "
                    + "ORDER BY DateEvents DESC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(extract(rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    
 
    
    
    
}
