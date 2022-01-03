/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.CategorieProduit;
import com.esprit.pidev.tgt.entities.CommandeP;
import com.esprit.pidev.tgt.entities.Paiement;
import com.esprit.pidev.tgt.entities.Produit;
import com.esprit.pidev.tgt.services.CategorieProduitService;
import com.esprit.pidev.tgt.services.CommandePService;
import com.esprit.pidev.tgt.services.ICategorieProduitService;
import com.esprit.pidev.tgt.services.IProduitService;
import com.esprit.pidev.tgt.services.PaiementService;
import com.esprit.pidev.tgt.services.ProduitService;
import com.esprit.pidev.tgt.utils.Rooting;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class BackOfficeEcommerceController implements Initializable {
    
     ObservableList<CategorieProduit> categorieProduitList = FXCollections.observableArrayList();
     ICategorieProduitService categorieProduitservice = new CategorieProduitService();
     ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
     IProduitService Produitservice = new ProduitService();
     

    @FXML
    private Button gestionOrganisation;
    @FXML
    private TableView<Produit> tableViewProduits;
    @FXML
    private TableColumn<Produit, String> ImageProduit;
    @FXML
    private TableColumn<Produit, String> NomProduit;
    @FXML
    private TableColumn<Produit, String> DescriptionProduit;
    @FXML
    private TableColumn<Produit, String> PrixProduit;
    @FXML
    private TableColumn<Produit, String> QuantiteProduit;
    @FXML
    private TableColumn<Produit, CategorieProduit> CategorieProduit;
    @FXML
    private TextField rechercheP;
    @FXML
    private TableView<CategorieProduit> tableViewCategorie;
    @FXML
    private TableColumn<CategorieProduit, String> NomCategorie;

    @FXML
    private Tab afficheProduits;
    @FXML
    private Tab AfficheCategorie;
    @FXML
    private Button supprimerCasting;
    @FXML
    private Tab afficheEvents;
    @FXML
    private TextField SearchC;
    @FXML
    private TableView<CommandeP> TableCommande;
    @FXML
    private TableColumn<CommandeP, String> Nom1;
    @FXML
    private TableColumn<CommandeP, String> Prenom1;
    @FXML
    private TableColumn<CommandeP, Double> Prix_Total1;
    @FXML
    private TableColumn<CommandeP, Date> Date_Commande1;
    @FXML
    private Tab afficheEvents1;
    @FXML
    private TableView<Paiement> TablePaiement;
     @FXML
    private TableColumn<Paiement, String> Nom2;
     @FXML
    private TableColumn<Paiement, String> Prenom2;
    @FXML
    private TableColumn<Paiement, Integer> NumCarte;
    @FXML
    private TableColumn<Paiement, Date> DateExpiration;
    @FXML
    private TableColumn<Paiement, Integer> CodeSec;
    @FXML
    private TableColumn<Paiement, String> TypePaiement;
    @FXML
    private TableColumn<Paiement, String> Pays;
    @FXML
    private TableColumn<Paiement, String> Email1;
    @FXML
    private ImageView logout;
    @FXML
    private Button condidature;
    @FXML
    private Button utilisateur;
    @FXML
    private Button Ecommerce;
    @FXML
    private Button formation;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
        initColCategorie();
        loadData();
        loadDatap();
        initColProduit();
        Actualiser();
        Actualiser1();
        
        PaiementService p= new PaiementService();
                ArrayList<Paiement> l;
            l = (ArrayList<Paiement>) p.AfficherPaiement1();
            ObservableList<Paiement> ListPaiement = FXCollections.observableArrayList(l);
            
         Nom2.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Nom"));
         Prenom2.setCellValueFactory(new PropertyValueFactory<Paiement, String>("prenom"));
         NumCarte.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Num_Carte"));
         DateExpiration.setCellValueFactory(new PropertyValueFactory<Paiement, Date>("Date_Expiration"));
         CodeSec.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Code_Sec"));
         TypePaiement.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Type_Paiement"));
         Pays.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Pays"));
         Email1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Email"));
         
         
         
         TablePaiement.setItems(ListPaiement);
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     private void initColCategorie() {
         
         NomCategorie.setCellValueFactory(new  PropertyValueFactory<>("nomcategorie"));
     }
     
     
         private void Actualiser() throws SQLException{
        
         try {
             CommandePService c= new CommandePService();
                ArrayList<CommandeP> l;
            l = (ArrayList<CommandeP>) c.AfficherCommande();
            ObservableList<CommandeP> ListCommande = FXCollections.observableArrayList(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Prenom"));
         Prix_Total1.setCellValueFactory(new PropertyValueFactory<CommandeP, Double>("Prix_Total"));
         Date_Commande1.setCellValueFactory(new PropertyValueFactory<CommandeP, Date>("Date_Commande"));
         
         
         
         TableCommande.getItems().clear();
         TableCommande.setItems(ListCommande);
        } catch (SQLException ex) {
            
        }
    }
         
         
         private void Actualiser1() throws SQLException{
         
         try {
             PaiementService p= new PaiementService();
                ArrayList<Paiement> l;
            l = (ArrayList<Paiement>) p.AfficherPaiement1();
            ObservableList<Paiement> ListPaiement = FXCollections.observableArrayList(l);
            
         Nom2.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Nom"));
         Prenom2.setCellValueFactory(new PropertyValueFactory<Paiement, String>("prenom"));
         NumCarte.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Num_Carte"));
         DateExpiration.setCellValueFactory(new PropertyValueFactory<Paiement, Date>("Date_Expiration"));
         CodeSec.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Code_Sec"));
         TypePaiement.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Type_Paiement"));
         Pays.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Pays"));
         Email1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Email"));
         
         
         
         TablePaiement.getItems().clear();
         TablePaiement.setItems(ListPaiement);
        } catch (SQLException ex) {
        }
    }
         
     private void initColProduit(){
         ImageProduit.setCellValueFactory(new PropertyValueFactory<>("image"));
         NomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
         DescriptionProduit.setCellValueFactory(new PropertyValueFactory<>("description"));
         PrixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
         QuantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
         CategorieProduit.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));

         
     }
     
     
      public void loadData() {
       

        try {
            
           categorieProduitList = FXCollections.observableArrayList(categorieProduitservice.findAll()); 
           System.out.println(categorieProduitList);
           
        } catch (SQLException ex) {
            
        }
    
        tableViewCategorie.setItems(categorieProduitList);
        

    }
      
      public void loadDatap() {
       

          ProduitList = FXCollections.observableArrayList(Produitservice.afficherProduitb());
          System.out.println(ProduitList);
    
       
        tableViewProduits.setItems(ProduitList);
        

    }

  
        @FXML
    private void utilisateur(ActionEvent event) {
         Rooting.navigate("utilisateur", "BackOfficeProfile");
          closeStage();
    }
    
    private void candidature(ActionEvent event) {
         Rooting.navigate("condidature", "BackOfice");
          closeStage();
    }

    @FXML
    private void organisation(ActionEvent event) {
        Rooting.navigate("organisation", "BackOfficeOrganisations");
          closeStage();
    }

    @FXML
    private void publication(ActionEvent event) {
        Rooting.navigate("publication", "BackOffice");
          closeStage();
    }

    private void produit(ActionEvent event) {
          Rooting.navigate("produit", "BackOfficeEcommerce");
          closeStage();
    }

    @FXML
    private void formation(ActionEvent event) {
    
    }
            private void closeStage() {
        ((Stage) supprimerCasting.getScene().getWindow()).hide();
    }

    @FXML
    private void rechercheProduits(ActionEvent event) {
    }

    @FXML
    private void ajouterProduits(ActionEvent event) {
        Produit selectedProduit  = tableViewProduits.getSelectionModel().getSelectedItem();
        FXMLLoader loader = Rooting.navigate("AjouterProduit", "ProduitFormulaire");
        ProduitFormulaireController controller = (ProduitFormulaireController) loader.getController();
    }

    @FXML
    private void modifierProduits(ActionEvent event) {
        
        Produit selectedProduit  = tableViewProduits.getSelectionModel().getSelectedItem();
        if (selectedProduit==null){
            System.out.println("choisir un produit");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modifierr", "ProduitFormulaire");
        ProduitFormulaireController controller = (ProduitFormulaireController) loader.getController();
         controller.initfields(selectedProduit,this);
        
        }
    }

    @FXML
    private void supprimerProduits(ActionEvent event) {
        
        Produit selectedProduit  = tableViewProduits.getSelectionModel().getSelectedItem();
        if (selectedProduit==null){
            System.out.println("choisir un produit");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionProduit");
        ValidationDeSuppressionProduitController controller = (ValidationDeSuppressionProduitController) loader.getController();
         controller.initfields(selectedProduit,this);
        
        }
    }

    @FXML
    private void ajouterCategories(ActionEvent event) {
        FXMLLoader loader = Rooting.navigate("Ajouter", "CategorieFormulaire");
        
    }

    @FXML
    private void modifierCategories(ActionEvent event) {
        CategorieProduit selectedCategorieProduit  = tableViewCategorie.getSelectionModel().getSelectedItem();
        if (selectedCategorieProduit==null){
            System.out.println("choisir une categorie");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "CategorieFormulaire");
        CategorieFormulaireController controller = (CategorieFormulaireController) loader.getController();
         controller.initfields(selectedCategorieProduit,this);
        
        }
    }

    @FXML
    private void supprimerCategories(ActionEvent event) {
        CategorieProduit selectedCategorieProduit  = tableViewCategorie.getSelectionModel().getSelectedItem();
        if (selectedCategorieProduit==null){
            System.out.println("choisir une categorie");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionCategorie");
        ValidationDeSuppressionCategorieController controller = (ValidationDeSuppressionCategorieController) loader.getController();
         controller.initfields(selectedCategorieProduit,this);
        
        }
    }

    @FXML
    private void showDetaille(ActionEvent event) {
    }


    
    
    @FXML
    private void SearchProduit(KeyEvent event) throws SQLException {
        String msg = this.rechercheP.getText().concat("%");
        //(!(msg.equals(""))){ 
      
        
        ProduitService c= new ProduitService();
                ArrayList<Produit> l;
            l = (ArrayList<Produit>) c.Rechercheproduit(msg);
            ObservableList<Produit> ListCommande = FXCollections.observableArrayList(l);
         ImageProduit.setCellValueFactory(new PropertyValueFactory<>("image"));
         NomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
         DescriptionProduit.setCellValueFactory(new PropertyValueFactory<>("description"));
         PrixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
         QuantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
         CategorieProduit.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
            
             tableViewProduits.getItems().clear();
             tableViewProduits.setItems(ListCommande);
    }


    @FXML
    private void SearchCat(KeyEvent event) throws SQLException {
       String msg = this.SearchC.getText().concat("o");

      
        
        CategorieProduitService c= new CategorieProduitService();
                ArrayList<CategorieProduit> l;
            l = (ArrayList<CategorieProduit>) c.Recherchecat(msg);
            ObservableList<CategorieProduit> ListC = FXCollections.observableArrayList(l);

            NomCategorie.setCellValueFactory(new  PropertyValueFactory<>("nomcategorie"));
            
             tableViewCategorie.getItems().clear();
             tableViewCategorie.setItems(ListC);
    }

   @FXML
    private void logout(MouseEvent event) {
        Rooting.navigate("Connexion", "login");
        gestionOrganisation.getScene().getWindow().hide();
    }

    @FXML
    private void condidature(ActionEvent event) {
    }

    @FXML
    private void Ecommerce(ActionEvent event) {
    }

    
}
