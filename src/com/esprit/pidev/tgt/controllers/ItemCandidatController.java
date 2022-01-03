/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ItemCandidatController implements Initializable {

    @FXML
    private AnchorPane itemcandidat;
    @FXML
    private Label Lnomcondidat;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private FontAwesomeIconView edit_icon;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private FontAwesomeIconView delete_icon;
    @FXML
    private JFXButton affercter_btn;
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
    private Label Lmailaddress;
    @FXML
    private Label position21;
    @FXML
    private Label Lcin;
    @FXML
    private Label position211;
    @FXML
    private Label Lnumtel;
    @FXML
    private Label position212;
    @FXML
    private Label Lcv;
    @FXML
    private Label position213;
    @FXML
    private JFXTextArea Lmotivation;
    CandidatService candidatservice = new CandidatService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_edit.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            btn_edit.setStyle("-fx-text-fill: #cc1573");
            edit_icon.setStyle("-fx-fill: #cc1573");
        });
        btn_delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: #cc1573");
            delete_icon.setStyle("-fx-fill: #cc1573");
        });
        affercter_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            affercter_btn.setStyle("-fx-text-fill: #cc1573");
            comments_icon.setStyle("-fx-fill: #cc1573");
        });
        btn_edit.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            btn_edit.setStyle("-fx-text-fill: BLACK");
            edit_icon.setStyle("-fx-fill: BLACK");
        });
        btn_delete.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: BLACK");
            delete_icon.setStyle("-fx-fill: BLACK");
        });
        affercter_btn.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            affercter_btn.setStyle("-fx-text-fill: BLACK");
            comments_icon.setStyle("-fx-fill: BLACK");
        });

    }

    public void initCol(Candidat candidat, Node[] nodes, int i, int last) {
        Lnomcondidat.setText(candidat.getNomC());
        LtitreCasting.setText(candidat.getCasting().getTitreCasting());
        Lmailaddress.setText(candidat.getMailaddress());
        Lcin.setText(Integer.toString(candidat.getCinCondidat()));
        Lnumtel.setText(Integer.toString(candidat.getTel()));
        Lcv.setText(candidat.getCv());
        Lmotivation.setText(candidat.getMotivation());
        btn_delete.addEventHandler(ActionEvent.ACTION, (event) -> {
            Optional<ButtonType> result = AlertMaker.showConfirmationAlert("Supprimer candidature", "Voulez-vous vraiment supprimer ce candidat ?");
            if (result.get() == ButtonType.OK) {
                try {
                    candidatservice.delete(candidat);
                } catch (SQLException ex) {
                    Logger.getLogger(ItemCandidatController.class.getName()).log(Level.SEVERE, null, ex);
                }
                itemcandidat.getChildren().clear();
                // nodes[i + 1] = nodes[i];
            }

        });

        btn_edit.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/CondidatFormulairefront.fxml"));

                CondidatFormulaireController controller = new CondidatFormulaireController();
                controller.candidat = candidat;
                loader.setController(controller);
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modifier Candidat");
                stage.getIcons().add(new Image("file:favicon.png"));
                stage.setScene(new Scene(parent));
                controller.setcandidat(candidat);
                stage.show();
               

            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });
        
         affercter_btn.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/EntretientFormulairefront.fxml"));

                EntretientFormulaireController controller = new EntretientFormulaireController();
                controller.candidat = candidat;
                loader.setController(controller);
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("affecter une date Entretient");
                stage.getIcons().add(new Image("file:favicon.png"));
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });

    }
}
