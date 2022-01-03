/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.services.EntretientService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ItemEntretientController implements Initializable {

    @FXML
    private AnchorPane itemEntretient;
    @FXML
    private Label Lnomcondidat;
    @FXML
    private FontAwesomeIconView edit_icon;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private FontAwesomeIconView delete_icon;
    @FXML
    private JFXButton call_btn;
    @FXML
    private FontAwesomeIconView comments_icon;
    @FXML
    private Label position;
    @FXML
    private Label position1;
    @FXML
    private Label LtitreCasting;
    @FXML
    private Label position2;
    @FXML
    private Label position21;
    @FXML
    private Label Lstatus;
    @FXML
    private Label position211;
    @FXML
    private Label Lnote;
    @FXML
    private Rating note;
    @FXML
    private Label day;
    @FXML
    private Label month;
    @FXML
    private Label year;
    @FXML
    private Label time;
CandidatService candidatservice = new CandidatService();
EntretientService entretientservice = new EntretientService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: #cc1573");
            delete_icon.setStyle("-fx-fill: #cc1573");
        });
        call_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            call_btn.setStyle("-fx-text-fill: #cc1573");
            comments_icon.setStyle("-fx-fill: #cc1573");
        });

        btn_delete.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: BLACK");
            delete_icon.setStyle("-fx-fill: BLACK");
        });
        call_btn.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            call_btn.setStyle("-fx-text-fill: BLACK");
            comments_icon.setStyle("-fx-fill: BLACK");
        });
        
    }    
    
     public void initCol(Candidat candidat, Node[] nodes, int i, int last) {
        Lnomcondidat.setText(candidat.getNomC());
        LtitreCasting.setText(candidat.getCasting().getTitreCasting());
        time.setText(Integer.toString(candidat.getEntretient().getDateEnt().getHour()) + ":" + Integer.toString(candidat.getEntretient().getDateEnt().getMinute()));
        day.setText(Integer.toString(candidat.getEntretient().getDateEnt().getDayOfMonth()));
        year.setText(Integer.toString(candidat.getEntretient().getDateEnt().getYear()));
        String s = candidat.getEntretient().getDateEnt().getMonth().getDisplayName(TextStyle.SHORT, Locale.FRANCE);
        month.setText(s.toUpperCase());
        Lstatus.setText(candidat.getEntretient().getStatutEnt().toString());
        Lnote.setText(Float.toString(candidat.getEntretient().getNoteEnt()));
        
        btn_delete.addEventHandler(ActionEvent.ACTION, (event) -> {
            Optional<ButtonType> result = AlertMaker.showConfirmationAlert("Supprimer entretient", "Voulez-vous vraiment supprimer cette entretient ?");
            if (result.get() == ButtonType.OK) {
                try {
                  entretientservice.delete(candidat);
                } catch (SQLException ex) {
                   //Logger.getLogger(ItemCandidatController.class.getName()).log(Level.SEVERE, null, ex);
                }
                itemEntretient.getChildren().clear();
                // nodes[i + 1] = nodes[i];
            }

        });

      
        
         call_btn.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/EspaceEntretientfront.fxml"));

                EspaceEntretientController controller = new EspaceEntretientController();
                controller.candidat = candidat;
                loader.setController(controller);
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Make a call");
                stage.getIcons().add(new Image("file:favicon.png"));
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });

    }
    
}
