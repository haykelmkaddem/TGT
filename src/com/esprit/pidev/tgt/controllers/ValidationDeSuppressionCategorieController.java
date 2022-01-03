/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.CategorieProduit;
import com.esprit.pidev.tgt.services.CategorieProduitService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class ValidationDeSuppressionCategorieController implements Initializable {
    
    private Statement statement;
    private CategorieProduitService categorieProduitService = new CategorieProduitService();
    private CategorieProduit categorieProduit;
    private BackOfficeEcommerceController backOfficeEcommerceController;

    @FXML
    private Label attention;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmation(ActionEvent event) throws SQLException {
        categorieProduitService.delete(categorieProduit);
        backOfficeEcommerceController.loadData();
        closeStage();
    }
    
    private void closeStage() {
        ((Stage) attention.getScene().getWindow()).close();
    }
    

    @FXML
    private void abendonner(ActionEvent event) {
        closeStage();
    }
    
    public void initfields(CategorieProduit categorieProduit, BackOfficeEcommerceController backOfficeEcommerceController){
    this.categorieProduit=categorieProduit;
     this.backOfficeEcommerceController= backOfficeEcommerceController;
    }
    
}
