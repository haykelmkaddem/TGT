/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Entretien;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class EntretienEnligneController implements Initializable {

    @FXML
    private JFXButton btnParticiper;
    @FXML
    private JFXButton btnlistCandidat;
    @FXML
    private JFXButton btnlistEntretient;
    @FXML
    private JFXButton btnlistContrat;
    @FXML
    private VBox pnl_scroll;
    CandidatService candidatservise = new CandidatService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjouterCondidature(ActionEvent event) {
         Rooting.navigate("candidature", "CondidatFormulaire");
    }

    @FXML
    private void AfficherCandidats(ActionEvent event) throws IOException, SQLException {
        pnl_scroll.getChildren().clear();
        //Create Node Tables
        Node[] nodes = new Node[30];
        //Declare a counter and the list
        int i =0;
        List<Candidat> list = candidatservise.findAll();
        for (Candidat candidat : list) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/ItemCondidat.fxml"));
                ItemCandidatController ic = new ItemCandidatController();
                loader.setController(ic);
                nodes[i] = loader.load();
                pnl_scroll.getChildren().add(nodes[i]);
                i++;
                ic.initCol(candidat,nodes,i,list.size());
               
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                AlertMaker.showErrorMessage(ex);
            }
        }
        if (pnl_scroll.getChildren().isEmpty()) {
            AlertMaker.showSimpleAlert("Pas de Candidature", "Veuillez ajouter des Candidature");
        }
    }

    @FXML
    private void AfficherEntretients(ActionEvent event) throws SQLException {
                pnl_scroll.getChildren().clear();
        //Create Node Tables
        Node[] nodes = new Node[30];
        //Declare a counter and the list
        int i =0;
        List<Candidat> list = candidatservise.findAll().stream().filter(cand->cand.getEntretient()!=null).collect(Collectors.toList());
        for (Candidat candidat : list) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/ItemEntretient.fxml"));
                ItemEntretientController ic = new ItemEntretientController();
                loader.setController(ic);
                nodes[i] = loader.load();
                pnl_scroll.getChildren().add(nodes[i]);
                i++;
                ic.initCol(candidat,nodes,i,list.size());
               
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                AlertMaker.showErrorMessage(ex);
            }
        }
        if (pnl_scroll.getChildren().isEmpty()) {
            AlertMaker.showSimpleAlert("Pas d'entretient", "Veuillez atribier des entretient");
        }
    }

    @FXML
    private void AfficherCantrats(ActionEvent event) {
       
          Rooting.navigate("contrat", "ListeContrat");
    
}}
