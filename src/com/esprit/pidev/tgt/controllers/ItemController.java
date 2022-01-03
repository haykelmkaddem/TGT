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
import com.jfoenix.controls.JFXProgressBar;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class ItemController implements Initializable {

    @FXML
    private AnchorPane item;

    @FXML
    private Label prix;

    @FXML
    private Label titre;

    @FXML
    private Label theme;

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
    private JFXButton comments_btn;

    @FXML
    private FontAwesomeIconView comments_icon;

    @FXML
    private Label time;

    @FXML
    private Label adresse;

    @FXML
    private ImageView imageView;

    @FXML
    private Text desc;

    EventsService es = new EventsService();

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
        comments_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            comments_btn.setStyle("-fx-text-fill: #cc1573");
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
        comments_btn.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            comments_btn.setStyle("-fx-text-fill: BLACK");
            comments_icon.setStyle("-fx-fill: BLACK");
        });

    }

    public void showEvents(Events events, Node[] nodes, int i, int last) {
        titre.setText(events.getTitreEvents());
        time.setText(Integer.toString(events.getDateEvents().getHour()) + ":" + Integer.toString(events.getDateEvents().getMinute()));
        day.setText(Integer.toString(events.getDateEvents().getDayOfMonth()));
        year.setText(Integer.toString(events.getDateEvents().getYear()));
        String s = events.getDateEvents().getMonth().getDisplayName(TextStyle.SHORT, Locale.FRANCE);
        month.setText(s.toUpperCase());
        adresse.setText(events.getAdresseEvents());
        desc.setText(events.getDescriptionEvents());
        prix.setText(Integer.toString(events.getPrixEvents()) + " DT");
        Image image = new Image(events.getImageEvents());
        imageView.setImage(image);
        theme.setText(events.getThemeEvents());

        btn_delete.addEventHandler(ActionEvent.ACTION, (event) -> {
            Optional<ButtonType> result = AlertMaker.showConfirmationAlert("Supprimer Evenement ?", "Voulez-vous vraiment supprimer cet évenement ?");
            if (result.get() == ButtonType.OK) {
                try {
                    es.supprimerEvents(events);
                } catch (SQLException ex) {
                    AlertMaker.showErrorMessage(ex);
                }
                item.getChildren().clear();
                // nodes[i + 1] = nodes[i];
            }
        });
        btn_edit.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/EventsFormulaire.fxml"));
                Parent parent = loader.load();
                EventsFormulaireController controller = new EventsFormulaireController();
                EventsFormulaireController.e = events;
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modifier Evenement");
                stage.getIcons().add(new Image("file:favicon.png"));
                stage.setScene(new Scene(parent));
                stage.show();
                controller.setevents(events);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });
        comments_btn.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/com/esprit/pidev/tgt/views/QrCode.fxml"));
                Parent par = Loader.load();

                QrCodeController cont = Loader.getController();
                cont.ini(events);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            } catch (SQLException ex) {
                AlertMaker.showErrorMessage(ex);
            }
        });
    }

}
