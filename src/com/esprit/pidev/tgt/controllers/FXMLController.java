/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author marsaoui
 */
public class FXMLController implements Initializable {

    @FXML
    private Tab acceuil;
    @FXML
    private Tab publications;
    @FXML
    private Tab Organisation;
    @FXML
    private JFXButton ajouter_un_condidat;
    @FXML
    private Tab gestion_entretients;
    @FXML
    private JFXButton Liste_des_condidats;
    @FXML
    private JFXButton Liste_des_entretients;
    @FXML
    private JFXButton condidats_accepter;
    @FXML
    private Tab formatons;
    @FXML
    private Tab article;
    @FXML
    private Tab profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
