/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;


import com.esprit.pidev.tgt.entities.CategorieProduit;
import com.esprit.pidev.tgt.entities.Produit;
import com.esprit.pidev.tgt.services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class ProduitFormulaireController implements Initializable {
    private Statement statement;
    
    private ProduitService produitService = new ProduitService();
    private Produit produit;
    private BackOfficeEcommerceController backOfficeEcommerceController;

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXButton ajoutCondidat;
    @FXML
    private JFXComboBox<String> categorieproduit;
    ObservableList <String> listecategorieproduit = FXCollections.observableArrayList("Piano","Durm","Guitar");
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField quantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categorieproduit.setItems(listecategorieproduit);
    }    

    @FXML
    private void ajoutCondidat(ActionEvent event) {
        if(this.produit==null) this.produit= new Produit(0, null, null, null, null, 0, null);
        this.produit.setNom(this.nom.getText());
        this.produit.setDescription(this.description.getText()); 
        this.produit.setPrix(Float.valueOf(this.prix.getText()));
        this.produit.setQuantite(Integer.valueOf(this.quantite.getText()));
        this.produit.setImage("piano.jpg");

         try {
            if(this.produit.getId() == 0){
            produitService.save(produit);
            backOfficeEcommerceController.loadData();
            }else{
            produitService.update(produit);
            backOfficeEcommerceController.loadDatap();
            }
            closeStage();
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeStage() {
        ((Stage) prix.getScene().getWindow()).close();
    }
 public void setproduit(Produit produit){
    this.produit=produit;
    initInput();
    }
    
    void initInput (){
    this.nom.setText(produit.getNom());
    this.description.setText(produit.getDescription()+"");
    this.prix.setText(produit.getPrix()+"");
    this.quantite.setText(produit.getQuantite()+"");
    }

    void initfields(Produit selectedpProduit, BackOfficeEcommerceController backOfficeEcommerceController) {
       this.produit=selectedpProduit;
       this.backOfficeEcommerceController= backOfficeEcommerceController;
       initInput();
    }
    
}
