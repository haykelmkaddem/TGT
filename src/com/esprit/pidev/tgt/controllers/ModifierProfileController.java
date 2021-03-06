/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.enumeration.Genre;
import com.esprit.pidev.tgt.services.UtilisateurService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.ConectedUser;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Seif Henchir
 */
public class ModifierProfileController implements Initializable {
 private Statement statement;
    private UtilisateurService utilisateurService = new UtilisateurService();
    private Utilisateur utilisateur;
    private BackOficeProfileController BackOficeProfileController;
    private MainController mainController;
    
    @FXML
    private Button modifier;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField numTel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier_profile(ActionEvent event) throws SQLException  {
        String nom = this.nom.getText();
        String prenom = this.prenom.getText();
        String email = this.email.getText();
        String numTel = this.numTel.getText();
        this.utilisateur.setNom(nom);
        this.utilisateur.setPrenom(prenom);
        this.utilisateur.setEmail(email);
        this.utilisateur.setNumTel(numTel);
        
        try{
            this.utilisateur.getId();
            boolean a=utilisateurService.update(utilisateur);
            if(BackOficeProfileController!=null){
            BackOficeProfileController.loadData();
            AlertMaker.showSimpleAlert("succes ", " la modification  a enregister");
            }
            else if(mainController!=null){
              ConectedUser.setUtilisateur(utilisateur);
             mainController.loadData();
             AlertMaker.showSimpleAlert("succes ", " la modification  a enregister");
            }
            closeStage();
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void initfields(Utilisateur selectedUtilisateur, BackOficeProfileController BackOficeProfileController) {
       this.utilisateur=selectedUtilisateur;
       this.BackOficeProfileController= BackOficeProfileController;
       initInput();
    }
     
     void initfields(Utilisateur selectedUtilisateur, MainController mainController) {
       this.utilisateur=selectedUtilisateur;
       this.mainController= mainController;
       initInput();
    }
     
     private void closeStage() {
        ((Stage) nom.getScene().getWindow()).close();
    }
 public void setcandidat(Utilisateur utilisateur){
    this.utilisateur=utilisateur;
    initInput();
    }
    
    void initInput (){
    this.nom.setText(utilisateur.getNom());
    this.prenom.setText(utilisateur.getPrenom());
    this.email.setText(utilisateur.getEmail());
    this.numTel.setText(utilisateur.getNumTel()+"");
    }

    void initfields(Compte selectedCompte, BackOficeProfileController aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
