/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.services.OrganisationsService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class OrganisationsController implements Initializable {

    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btnlist;
    @FXML
    private VBox pnl_scroll;
    
    OrganisationsService os = new OrganisationsService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterOrganisations(ActionEvent event) {
          pnl_scroll.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/OrganisationsFormulaire.fxml"));
            pnl_scroll.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }

    @FXML
    private void AfficherOrganisations(ActionEvent event) {
           pnl_scroll.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/ListeOrganisations.fxml"));
            pnl_scroll.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
        
    }
    
}
