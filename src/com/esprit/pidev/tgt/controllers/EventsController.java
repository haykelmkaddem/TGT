/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.services.EventsService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
public class EventsController implements Initializable {

    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btnlist;
    @FXML
    private VBox pnl_scroll;
    
    EventsService es = new EventsService();
    @FXML
    private JFXButton btnTri;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterEvenement(ActionEvent event) {
         pnl_scroll.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/EventsFormulaire.fxml"));
            pnl_scroll.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }

    @FXML
    private void AfficherEvenement(ActionEvent event) throws SQLException {
          pnl_scroll.getChildren().clear();
        //Create Node Tables
        Node[] nodes = new Node[30];
        //Declare a counter and the list
        int i =0;
        List<Events> list = es.findAll();
        for (Events events : list) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/Item.fxml"));
                ItemController ic = new ItemController();
                loader.setController(ic);
                nodes[i] = loader.load();
                pnl_scroll.getChildren().add(nodes[i]);
                i++;
                ic.showEvents(events,nodes,i,list.size());
               
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                AlertMaker.showErrorMessage(ex);
            }
        }
        if (pnl_scroll.getChildren().isEmpty()) {
            AlertMaker.showSimpleAlert("Pas de publications", "Veuillez ajouter des publications");
        }
    }

    @FXML
    private void TrierEvenement(ActionEvent event) {
        pnl_scroll.getChildren().clear();
        //Create Node Tables
        Node[] nodes = new Node[30];
        //Declare a counter and the list
        int i =0;
        List<Events> list = es.trieParDate();
        for (Events events : list) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/Item.fxml"));
                ItemController ic = new ItemController();
                loader.setController(ic);
                nodes[i] = loader.load();
                pnl_scroll.getChildren().add(nodes[i]);
                i++;
                ic.showEvents(events,nodes,i,list.size());
               
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                AlertMaker.showErrorMessage(ex);
            }
        }
        if (pnl_scroll.getChildren().isEmpty()) {
            AlertMaker.showSimpleAlert("Pas de publications", "Veuillez ajouter des publications");
        }
    }

    }
    

