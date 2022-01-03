/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static com.sun.deploy.cache.Cache.copyFile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
 * @author goldzeo
 */
public class CondidatFormulaireController implements Initializable {
  
    private Statement statement;
    private CandidatService candidatService = new CandidatService();
    public  Candidat candidat;
    private BackOficeController backOficeController;
    
    @FXML
    private JFXTextField nomC;
    @FXML
    private JFXTextField cinCondidat;
    @FXML
    private JFXTextField mailaddress;
    @FXML
    private ImageView cv;
    @FXML
    private JFXTextArea motivation;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXButton ajoutCondidat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void ajoutCondidat(ActionEvent event) throws SQLException, IOException {
       boolean ok = true;
        if(this.candidat==null) this.candidat= new Candidat(0, new Casting(13), null, null, 0, null, null, null, 0);
        this.candidat.setNomC(this.nomC.getText());
       String cin = this.cinCondidat.getText();
        if(!cin.matches("^\\d{8}$")){
            ok=false;
            AlertMaker.showErrorMessage("invalid CIN", "8 numbers");
        }
        this.candidat.setCinCondidat(Integer.valueOf(cin));
       
        Image img = cv.getImage();
        String imgFile = saveToFileImageNormal(img);
      //  System.out.println(imgFile);
        this.candidat.setCv(imgFile); 
        String mail = this.mailaddress.getText();
        if(!mail.matches("[^@]+@[^\\.]+\\..+")){
            
             ok=false;
            AlertMaker.showErrorMessage("invalid mail", "suive ce pattern ([^@]+@[^\\.]+\\..+) stp");
        }
        
        this.candidat.setMailaddress(mail);
        this.candidat.setMotivation(this.motivation.getText());
        String tel = this.cinCondidat.getText();
        if(!tel.matches("^\\d{8}$")){
            ok=false;
            AlertMaker.showErrorMessage("invalid NUM tel", "8 numbers");
        }
        this.candidat.setTel(Integer.valueOf(tel));
        System.out.println("candidat "  + candidat.getId());
       if(ok){
         try {
            if(this.candidat.getId() == 0){
            candidatService.save(candidat);
            }else{
            candidatService.update(candidat);
            backOficeController.loadData();
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
         closeStage();}
    }

     private void closeStage() {
        ((Stage) cinCondidat.getScene().getWindow()).close();
        
    }
 public void setcandidat(Candidat candidat){
    this.candidat=candidat;
    initInput();
    }
    
    void initInput (){
    this.nomC.setText(candidat.getNomC());
    this.cinCondidat.setText(candidat.getCinCondidat()+"");
  //  this.cv.setText(candidat.getCv());
     this.mailaddress.setText(candidat.getMailaddress());
       this.motivation.setText(candidat.getMotivation());
      this.tel.setText(candidat.getTel()+"");
    }

    void initfields(Candidat selectedCandidat, BackOficeController backOficeController) {
       this.candidat=selectedCandidat;
       this.backOficeController= backOficeController;
       initInput();
    }
    
    public static String saveToFileImageNormal(Image image) throws SQLException, IOException {
        
        String ext = "pdf";
        File dir = new File("C:/wamp64/www/javaTGT/javaTGT/src/com/esprit/pidev/tgt/views/ressources");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "pdf", outputFile);
        return outputFile.toURI().toString();
    }

    @FXML
    private void uploadcv(MouseEvent event) {
        FileChooser fc = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("JPG files (.pdf)", "*.pdf");
       // FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterPDF);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image imageF = SwingFXUtils.toFXImage(bufferedImage, null);
            cv.setImage(imageF);
        } catch (IOException ex) {
            System.out.println("add cv");
        }
    }


}