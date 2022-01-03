/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.CategorieProduit;
import com.esprit.pidev.tgt.services.CategorieProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class CategorieFormulaireController implements Initializable {
    
        private Statement statement;
    private CategorieProduitService categorieService = new CategorieProduitService();
    private CategorieProduit categorieProduit;
    private BackOfficeEcommerceController backOfficeEcommerceController;

    @FXML
    private JFXTextField nomCategorie;
    @FXML
    private JFXButton ajoutCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutCategorie(ActionEvent event) {
        if(this.categorieProduit==null) this.categorieProduit= new CategorieProduit(0, null);
        this.categorieProduit.setNomcategorie(this.nomCategorie.getText());
         try {
            if(this.categorieProduit.getId() == 0){
            categorieService.save(categorieProduit);
            }else{
            categorieService.update(categorieProduit);
            backOfficeEcommerceController.loadData();
            }
            closeStage();
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeStage() {
        ((Stage) nomCategorie.getScene().getWindow()).close();
    }
    
    
    public void setcandidat(CategorieProduit categorieProduit){
    this.categorieProduit=categorieProduit;
    initInput();
    }
    
    void initInput (){
    this.nomCategorie.setText(categorieProduit.getNomcategorie());
    }

    void initfields(CategorieProduit selectedCategorieProduit, BackOfficeEcommerceController backOfficeEcommerceController) {
       this.categorieProduit=selectedCategorieProduit;
       this.backOfficeEcommerceController= backOfficeEcommerceController;
       initInput();
    }
    
}
