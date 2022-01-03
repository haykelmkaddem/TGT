/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Casting;

import com.esprit.pidev.tgt.services.CastingService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class Item_1Controller implements Initializable {

    @FXML
    private AnchorPane item;
    @FXML
    private Label theme;
    @FXML
    private Label titre;
    @FXML
    private Label day;
    @FXML
    private Label month;
    @FXML
    private Label year;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private FontAwesomeIconView edit_icon;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private FontAwesomeIconView delete_icon;
  
    @FXML
    private Label adresse;
    @FXML
    private ImageView imageView;
    @FXML
    private Text desc;

    CastingService cs = new CastingService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btn_edit.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            btn_edit.setStyle("-fx-text-fill: #cc1573");
            edit_icon.setStyle("-fx-fill: #cc1573");
        });
        btn_delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: #cc1573");
            delete_icon.setStyle("-fx-fill: #cc1573");
        });
       
        btn_edit.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            btn_edit.setStyle("-fx-text-fill: BLACK");
            edit_icon.setStyle("-fx-fill: BLACK");
        });
        btn_delete.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            btn_delete.setStyle("-fx-text-fill: BLACK");
            delete_icon.setStyle("-fx-fill: BLACK");
        });
       
        // TODO
    }    
    
    
    public void showCasting(Casting casting) {
        titre.setText(casting.getTitreCasting());
        
        day.setText(Integer.toString(casting.getDateCasting().toLocalDate().getDayOfMonth()));
        year.setText(Integer.toString(casting.getDateCasting().toLocalDate().getYear()));
        String s = casting.getDateCasting().toLocalDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.FRANCE);
        month.setText(s.toUpperCase());
        adresse.setText(casting.getAdresseCasting());
        desc.setText(casting.getDescriptionCasting());
       
        Image image = new Image(casting.getImageCasting());
        imageView.setImage(image);
        theme.setText(casting.getThemeCasting());

        btn_delete.addEventHandler(ActionEvent.ACTION, (event) -> {
            Optional<ButtonType> result = AlertMaker.showConfirmationAlert("Supprimer Casting ?", "Voulez-vous vraiment supprimer cet Casting ?");
            if (result.get() == ButtonType.OK) {
                try {
                    cs.supprimerCasting(casting);
                } catch (SQLException ex) {
                    AlertMaker.showErrorMessage(ex);
                }
                item.getChildren().clear();
                // nodes[i + 1] = nodes[i];
            }
        });
        btn_edit.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/ModifierCasting.fxml"));
                ModifierCastingController.casting = casting;
                ModifierCastingController controller = new ModifierCastingController();
                loader.setController(controller);
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modifier Casting");
                stage.getIcons().add(new Image("file:favicon.png"));
                stage.setScene(new Scene(parent));
                stage.show();
                controller.ModifierCasting(casting, stage, titre, day, day, year, month, adresse, theme, imageView);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });
    
}}
