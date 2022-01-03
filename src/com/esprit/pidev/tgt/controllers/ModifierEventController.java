/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.services.EventsService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class ModifierEventController implements Initializable {

    @FXML
    private TextField TitreEvents;
    @FXML
    private TextArea DescriptionEvents;
    @FXML
    private ComboBox<String> ThemeEvents;
    @FXML
    private TextField AdresseEvents;
    @FXML
    private JFXTimePicker HeureEvents;
    @FXML
    private TextField PrixEvents;
    @FXML
    private Button AjouterEvents;
    @FXML
    private JFXDatePicker DateEvents;
    @FXML
    private ImageView ImageEvents;
    
    public static Events event = null;
    EventsService es = new EventsService();
    private Statement statement;
    ObservableList <String> listeThemeEvents = FXCollections.observableArrayList("Culture","Economie","Politique","Scientifique","Social","Sport");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ThemeEvents.setItems(listeThemeEvents);
        TitreEvents.setText(event.getTitreEvents());
        DescriptionEvents.setText(event.getDescriptionEvents());
        AdresseEvents.setText(event.getDescriptionEvents());
        HeureEvents.setValue(LocalTime.of(event.getDateEvents().getHour(), event.getDateEvents().getMinute()));
        PrixEvents.setText(Integer.toString(event.getPrixEvents()));
        DateEvents.setValue(event.getDateEvents().toLocalDate());
        ImageEvents.setImage(new Image(event.getImageEvents()));
        ImageEvents.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            FileChooser fc = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image imageF = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageEvents.setImage(imageF);
        } catch (IOException ex) {
            System.out.println("add image");
        }
        });
    }    
    
          public static String saveToFileImageNormal(Image image) throws SQLException, IOException {
        
        String ext = "jpg";
        File dir = new File("C:/wamp64/www/TGTjava-travail indiv/javaTGT/src/resources/images");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
        return outputFile.toURI().toString();
    }
         
//          public void ModifierEvents(Events e, Stage stage, Label titre, Label theme, Label day, Label year, Label month,  Label time, Label adresse, ImageView imageView , Label desc) {
//        AjouterEvents.addEventHandler(ActionEvent.ACTION, (event) -> {
//            Image img = ImageEvents.getImage();
//            String imgFile = null;
//            try {
//                imgFile = saveToFileImageNormal(img);
//            } catch (SQLException ex) {
//                AlertMaker.showErrorMessage(ex);
//            } catch (IOException ex) {
//                AlertMaker.showErrorMessage(ex);
//            }
//            es.modifierEvents(new Events(e.getId(), TitreEvents.getText(), DescriptionEvents.getText(), DateEvents.valueOf(DateEvents.getValue()), AdresseEvents.getText(), imgFile, ThemeEvents.getSelectionModel().getSelectedItem()));
//            AlertMaker.showSimpleAlert("Evenements Modifié", "Informations Modifiés avec Succés");
//            stage.close();
//            titre.setText(this.TitreEvents.getText());
//            desc.setText(DescriptionEvents.getText());
//            day.setText(Integer.toString(DateEvents.getValue().getDayOfMonth()));
//            year.setText(Integer.toString(DateEvents.getValue().getYear()));
//            String s = DateEvents.getValue().getMonth().getDisplayName(TextStyle.SHORT, Locale.FRANCE);
//            month.setText(s.toUpperCase());
//            adresse.setText(this.AdresseEvents.getText());
//            theme.setText(this.ThemeEvents.getSelectionModel().getSelectedItem());
//            imageView.setImage(new Image(imgFile));
//        });
//    }
    
}
