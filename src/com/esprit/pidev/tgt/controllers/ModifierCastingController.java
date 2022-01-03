/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import static com.esprit.pidev.tgt.controllers.CastingFormulaireController.saveToFileImageNormal;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.services.CastingService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class ModifierCastingController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker DateCasting;
    @FXML
    private DatePicker DateLimite;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<String> theme;
    @FXML
    private Button ajouterCasting;
    @FXML
    private ImageView image;

    public static Casting casting = null;
    CastingService cs = new CastingService();
    private Statement statement;
    ObservableList<String> listeTheme = FXCollections.observableArrayList("Mode & Pub", "Theatre & Humour", "Cinéma & Fiction", "Musique & Dance", "Télévision & Radio", "Peinture & Art plastique", "Sports & Arts de cirque", "Audiovisuel");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theme.setItems(listeTheme);
        titre.setText(casting.getTitreCasting());
        description.setText(casting.getDescriptionCasting());
        DateCasting.setValue(casting.getDateCasting().toLocalDate());
        DateLimite.setValue(casting.getDateLP().toLocalDate());
        adresse.setText(casting.getAdresseCasting());
        image.setImage(new Image(casting.getImageCasting()));
        theme.setValue(casting.getThemeCasting());
        image.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
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
        });
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

    public void ModifierCasting(Casting c, Stage stage, Label titre, Label desc, Label day, Label year, Label month, Label adresse, Label theme, ImageView imageView) {
        ajouterCasting.addEventHandler(ActionEvent.ACTION, (event) -> {
            Image img = image.getImage();
            String imgFile = null;
            try {
                imgFile = saveToFileImageNormal(img);
            } catch (SQLException ex) {
                AlertMaker.showErrorMessage(ex);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
            }
            cs.modifierCasting(new Casting(c.getId(), this.titre.getText(), description.getText(), Date.valueOf(DateCasting.getValue()), Date.valueOf(DateLimite.getValue()), this.adresse.getText(), imgFile, this.theme.getSelectionModel().getSelectedItem()));
            AlertMaker.showSimpleAlert("Casting Modifié", "Informations Modifiés avec Succés");
            stage.close();
            titre.setText(this.titre.getText());
            desc.setText(description.getText());
            day.setText(Integer.toString(DateCasting.getValue().getDayOfMonth()));
            year.setText(Integer.toString(DateCasting.getValue().getYear()));
            String s = DateCasting.getValue().getMonth().getDisplayName(TextStyle.SHORT, Locale.FRANCE);
            month.setText(s.toUpperCase());
            adresse.setText(this.adresse.getText());
            theme.setText(this.theme.getSelectionModel().getSelectedItem());
            imageView.setImage(new Image(imgFile));
        });
    }
}
