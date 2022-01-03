/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;


import com.esprit.pidev.tgt.entities.CommandeP;
import com.esprit.pidev.tgt.entities.ListeProduit;
import com.esprit.pidev.tgt.services.CommandePService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<ListeProduit> Products;
    @FXML
    private TableColumn<ListeProduit, String> Nom1;
    @FXML
    private TableColumn<ListeProduit, Double> Prix1;
    @FXML
    private TableColumn<ListeProduit, Integer> Quantite1;
    @FXML
    private Button ViderP;
    @FXML
    private TextField PrixTotal;
    @FXML
    private TableColumn<ListeProduit, Button> Update1;
    @FXML
    private TableColumn<ListeProduit, Button> Retirer1;
    @FXML
    private Button RetirerP;
    @FXML
    private Button UpdateQty;
    @FXML
    private TextField lib;
    @FXML
    private TextField NvQty;
    @FXML
    private TextField prix;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CommandePService c= new CommandePService();
                ArrayList<ListeProduit> l;
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            ObservableList<ListeProduit> ListCommande = FXCollections.observableArrayList(l);
            double total=c.CalculerTotalCommande(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<ListeProduit, String>("nom"));
         Prix1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Double>("prix"));
         Quantite1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Integer>("quantite"));
         Update1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Button>("update"));
         Retirer1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Button>("supprimer"));
         
         
       
         PrixTotal.setText(String.valueOf(total));
         PrixTotal.setDisable(true);
         
         
         Products.getItems().clear();
         Products.setItems(ListCommande);
    }    
    private void Actualiser()
    {
        CommandePService c= new CommandePService();
                ArrayList<ListeProduit> l;
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            ObservableList<ListeProduit> ListCommande = FXCollections.observableArrayList(l);
            double total=c.CalculerTotalCommande(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<ListeProduit, String>("nom"));
         Prix1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Double>("prix"));
         Quantite1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Integer>("quantite"));
         Update1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Button>("update"));
         Retirer1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Button>("supprimer"));
         
         
       
         PrixTotal.setText(String.valueOf(total));
         PrixTotal.setDisable(true);
         
         
         Products.getItems().clear();
         Products.setItems(ListCommande);
        
    }

    @FXML
    private void ViderPanier(ActionEvent event) {
         ArrayList<ListeProduit> l;
         CommandePService c= new CommandePService();
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            l.clear();
            ObservableList<ListeProduit> ListCommande = FXCollections.observableArrayList(l);
            double total=c.CalculerTotalCommande(l);
         Nom1.setCellValueFactory(new PropertyValueFactory<ListeProduit, String>("nom"));
         Prix1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Double>("prix"));
         Quantite1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Integer>("quantite"));
         Update1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Button>("update"));
         Retirer1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Button>("supprimer"));
         
        PrixTotal.setText(String.valueOf(total));
        PrixTotal.setDisable(true);
         
         
         Products.getItems().clear();
         Products.setItems(ListCommande);
    }

    @FXML
    private void AjouterCommande(ActionEvent event) throws SQLException, IOException {
        
        CommandePService c=new CommandePService();
        double prixTotal = Double.parseDouble(PrixTotal.getText());
        Date DateE = new Date(System.currentTimeMillis());
        int id=3;
        c.ajouterCommande(new CommandeP(DateE,prixTotal,id));
        
        //passer au paiement et facutre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPaiement.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void RetirerPanier(ActionEvent event) {
        ListeProduit lp;
        lp = Products.getSelectionModel().getSelectedItem();
        String lb=lp.getNom();
        Double prix=lp.getPrix();
        int qty=lp.getQuantite();
        ArrayList<ListeProduit> l;
         CommandePService c= new CommandePService();
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            l.remove(new ListeProduit(lb,prix,qty));
            Actualiser();
        
    }
    
    
    @FXML
    private void getSelected(MouseEvent event) {
        ListeProduit lp;
        lp = Products.getSelectionModel().getSelectedItem();
        if(lp == null)
        {
            JOptionPane.showMessageDialog(null , "Nothing selected" );
            
        }
        else
        {
            
            lib.setText(lp.getNom());
            lib.setDisable(true);
            prix.setText(String.valueOf(lp.getPrix()));
            prix.setVisible(false);
            
            
            
            NvQty.setText(String.valueOf(lp.getQuantite()));

            
           
                  
        }
    }

    @FXML
    private void UpdateQty(ActionEvent event) {
        
        ListeProduit lp;
        lp = Products.getSelectionModel().getSelectedItem();
        String lb="";
        Double prix1=0.0;
        int qty=0;
        int qty2=0;
        int pos=0;
        String lb2="";
        double prix2=0.0;
        lb=lp.getNom();
        
        prix1=lp.getPrix();
        
        qty=lp.getQuantite();
        
        qty2=Integer.parseInt(NvQty.getText());
        prix2=Double.parseDouble(prix.getText());
        lb2=lib.getText();
        ArrayList<ListeProduit> l;
         //CommandeService c= new CommandeService();
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            l.remove(new ListeProduit(lb,prix1,qty));
            
           // pos= l.indexOf(new ListeProduit(lb,prix1,qty));
           // System.out.println(pos);
            l.add(new ListeProduit(lb2,prix2,qty2));
           // l.set(pos, new ListeProduit(lb2,prix2,qty2));
           
         
            Actualiser();
    }
    
  
    
}
