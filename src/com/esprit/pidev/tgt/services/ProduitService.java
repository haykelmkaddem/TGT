/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Produit;
import com.esprit.pidev.tgt.entities.CategorieProduit;
import com.esprit.pidev.tgt.utils.DataBase;
import com.esprit.pidev.tgt.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author goldzeo
 */
public class ProduitService  implements IProduitService{
     private ICategorieProduitService categorieProduitService = new CategorieProduitService();
     private Statement statement;
     
     Connection  myConnex;
      Statement ste;
        
//    public ProduitService() {
//        try {
//            this.statement = DataSource.getInstance().getConnection().createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public ProduitService() {
          try {
              myConnex  = DataBase.
                      getInstance()
                      .getConnection();
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

   @Override
    public int save(Produit produit) throws SQLException {
        String req = "INSERT INTO `produit` (`id`, `categorie_id`, `nom`, `description`, `prix`, `quantite`, `image`) VALUES (NULL, '1', '"+produit.getNom()+"', '"+produit.getDescription()+"', '"+produit.getPrix()+"', '"+produit.getQuantite()+"', 'piano.jpg')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }
    
     @Override
    public Produit findByIdP(int id) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM `produit` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }

    @Override
    public Produit findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Produit> findAll() throws SQLException {
        List<Produit> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `produit`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    private Produit extract(ResultSet result)throws SQLException{
        
            int id = result.getInt("id");
            CategorieProduit categorie_id = categorieProduitService.findById(result.getInt("id"));
            String nom = result.getString("nom");
            String description= result.getString("description");
            Float prix= result.getFloat("prix");
            int quantite= result.getInt("quantite");
            String image= result.getString("image");
            
        return new Produit(id, categorie_id , nom, description , prix, quantite, image);
    }

    
    
    @Override
    public boolean update(Produit produit) throws SQLException {
        String reqUpdate="UPDATE `produit` SET `id`='"+produit.getId()+"',`categorie_id`='1',`nom`='"+produit.getNom()+"',`description`='"+produit.getDescription()+"',`prix`='"+produit.getPrix()+"',`quantite`='"+produit.getQuantite()+"',`image`='piano.jpg' WHERE `id` ='"+produit.getId()+"'";
         return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Produit produit) throws SQLException {
          statement.executeUpdate("DELETE FROM `produit` WHERE id =" + produit.getId()); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
        @Override
    public  List<Produit> afficherProduitb() {
        List<Produit> list = new ArrayList<>();
        List<CategorieProduit>cate= new ArrayList<CategorieProduit>();
       try {
           
           ste=myConnex.createStatement();
              String req3 =
                      "select * from produit ";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  int id=res.getInt("id");
                  int categorie_id=res.getInt("categorie_id");
                  String nom=res.getString("nom");
                  String description=res.getString("description");
                  float prix=res.getFloat("prix");
                  int quantite=res.getInt("quantite");
                  String image=res.getString("image");

                  PreparedStatement pre_cat=myConnex.prepareStatement("select * from categorie where id=? ");
               pre_cat.setInt(1,categorie_id);
               ResultSet rscat=pre_cat.executeQuery();
               while(rscat.next())
               {
                   int idc=rscat.getInt("id");
                   String cat=rscat.getString("nomcategorie");
                   CategorieProduit categ= new CategorieProduit(idc,cat);
                   cate.add(categ);
               }
               
               
               Produit p=new Produit(id,cate.get(cate.size()-1),nom,description,prix,quantite,image);
               
               list.add(p);
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
    
    
    
    
    @Override
    public  List<Produit> afficherProduit() {
        List<Produit> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from produit ";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Produit(
                          res.getInt("id"),
                          res.getString("nom"),
                          res.getFloat("prix"),
                          res.getInt("quantite"),
                          res.getString("description"),
                          res.getString("image")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
    
    @Override
    public  List<Produit> rechercheParNom(String nom) {
        List<Produit> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from produit where nom = '" + nom + "'";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Produit(
                          res.getInt("id"),
                          res.getString("nom"),
                          res.getFloat("prix"),
                          res.getInt("quantite"),
                          res.getString("description"),
                          res.getString("image")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
    
    
    @Override
    public  List<Produit> triParPrix() {
        List<Produit> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from produit ORDER BY prix ";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Produit(
                          res.getInt("id"),
                          res.getString("nom"),
                          res.getFloat("prix"),
                          res.getInt("quantite"),
                          res.getString("description"),
                          res.getString("image")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
    

    public List<Produit> Rechercheproduit(int id_Membre) throws SQLException {
    List<Produit> arr=new ArrayList<>();
    ste=myConnex.createStatement();
    ResultSet rs=ste.executeQuery("select * from commandep where Id_Membre='"+id_Membre+"'");
     while (rs.next()) {                
               int id=rs.getInt("Id_Commande");
               String nom=rs.getString("nom");
               String description=rs.getString("description");
               Float prix=rs.getFloat("prix");
               int quantite=rs.getInt("quantite");
               String image=rs.getString("image");
//               CategorieProduit categorie_id = rs.getCategorieProduit("image");
//               System.out.println("la Commande est "+Id_Commande+" est Date = "+Date_Commande+" Age = "+
//                        res.getString("age"));
               Produit c=new Produit( id,  nom,  description,  prix, quantite, image);
               arr.add(c);
     }
    return arr;
    }

    @Override
    public List<Produit> Rechercheproduit(String nomprod) throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=myConnex.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit where nom='"+nomprod+"'");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String description=rs.getString("description");
               Float prix=rs.getFloat("prix");
               int quantite=rs.getInt("quantite");
               String image=rs.getString("image");
               int categorie_id = rs.getInt("categorie_id");
//               System.out.println("la Commande est "+Id_Commande+" est Date = "+Date_Commande+" Age = "+
//                        res.getString("age"));
               Produit c=new Produit( id,  nom,  description,  prix, quantite, image);
               arr.add(c);
     }
    return arr;
    }

}
