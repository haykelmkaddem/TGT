/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.ConectedUser;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton publications;
    @FXML
    private JFXButton organisations;
    @FXML
    private JFXButton evenements;
    @FXML
    private JFXButton casting;
    @FXML
    private JFXButton formations;
    @FXML
    private JFXButton articles;
     @FXML
    private JFXButton entretient;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    public AnchorPane main;
    @FXML
    private Button modifpro;
    @FXML
    private ImageView image;
    @FXML
    private Button changerpasse;
    @FXML
    private Label idnom;
    @FXML
    private Label idnumerotel;
    @FXML
    private Label idemail;
    @FXML
    private Label iddateden;
    @FXML
    private Label idgenre;
    @FXML
    private Label idprenom;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         initdata();
    }

    public void loadData(){
    initdata();
    }
    
    @FXML
    private void GestionPublications(ActionEvent event) {
        anchorPane.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/Publications.fxml"));
            anchorPane.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        Rooting.navigate("Connexion", "login");
        anchorPane.getScene().getWindow().hide();
    }

    @FXML
    private void GestionOrganisations(ActionEvent event) {
       anchorPane.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/Organisations.fxml"));
            anchorPane.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
        
    }

    @FXML
    private void GestionEvenement(ActionEvent event) {
          anchorPane.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/Events.fxml"));
            anchorPane.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }

    @FXML
    private void GestionCasting(ActionEvent event) {
            anchorPane.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/Casting.fxml"));
            anchorPane.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }
     @FXML
    private void GestionEntretient(ActionEvent event) {
            anchorPane.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/EntretientEnligne.fxml"));
            anchorPane.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }
private void initdata() {
        Utilisateur conectedUser = ConectedUser.getUtilisateur();
        idnom.setText(conectedUser.getNom());
        idnumerotel.setText(conectedUser.getNumTel());
        idemail.setText(conectedUser.getEmail());
        iddateden.setText(conectedUser.getDateNaissance().toString());
        idgenre.setText(conectedUser.getGenre().toString());
        idprenom.setText(conectedUser.getPrenom());
    }
    @FXML
    private void modifier_profile(ActionEvent event) {
          FXMLLoader loader = Rooting.navigate("titre", "modifierProfile");
          ModifierProfileController controller = (ModifierProfileController) loader.getController();
          controller.initfields(ConectedUser.getUtilisateur(),this);
    }

      public static String saveToFileImageNormal(Image image) throws SQLException, IOException {
        
        String ext = "jpg";
        File dir = new File("C:/wamp64/www/javaPiDEV/javaTGT/javaTGT/src/resources/images");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
        return outputFile.toURI().toString();
    }
    @FXML
    private void addImage(MouseEvent event) {
              FileChooser fc = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image imageF = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(imageF);
        } catch (IOException ex) {
            System.out.println("add image");
        }
    }

    @FXML
    private void changer_mot_passe(ActionEvent event) {
         Rooting.navigate("titre", "ChangerMdp");
    }

    @FXML
    private void GestionArticles(ActionEvent event) {
         anchorPane.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/FrontOfficeEcommerce.fxml"));
            anchorPane.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }
    
    

}
