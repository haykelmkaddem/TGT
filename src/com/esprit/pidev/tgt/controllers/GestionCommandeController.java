/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.CommandeP;
import com.esprit.pidev.tgt.services.CommandePService;
import com.esprit.pidev.tgt.utils.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class GestionCommandeController implements Initializable {
    
    CommandeP co;
    Connection con;

    @FXML
    private TableView<CommandeP> TableCommande;
    @FXML
    private TableColumn<CommandeP, String> Nom1;
    @FXML
    private TableColumn<CommandeP, String> Prenom1;
    @FXML
    private TableColumn<CommandeP, Double> Prix_Total1;
    @FXML
    private TableColumn<CommandeP, Date> Date_Commande1;
    @FXML
    private TextField search;
    @FXML
    private TextField messagesms;
    @FXML
    private Button TriC;
    @FXML
    private ImageView UpdateImage;
    @FXML
    private ImageView DeleteImage;
    @FXML
    private Button envoyer;
    @FXML
    private TextField messagemail;
    @FXML
    private Button envoyer1;
    @FXML
    private TextField mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try {
            // TODO
            Actualiser();
//            Image imgUpdate = new Image("/Icons/update-png-3.png");
//            UpdateImage.setImage(imgUpdate);
//            Image imgDelete = new Image("/Icons/delete-sign.png");
//            DeleteImage.setImage(imgDelete);
            
        } catch (SQLException ex) {
            Logger.getLogger(com.esprit.pidev.tgt.controllers.GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
        private void Actualiser() throws SQLException{
        
         try {
             CommandePService c= new CommandePService();
                ArrayList<CommandeP> l;
            l = (ArrayList<CommandeP>) c.AfficherCommande();
            ObservableList<CommandeP> ListCommande = FXCollections.observableArrayList(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Prenom"));
         Prix_Total1.setCellValueFactory(new PropertyValueFactory<CommandeP, Double>("Prix_Total"));
         Date_Commande1.setCellValueFactory(new PropertyValueFactory<CommandeP, Date>("Date_Commande"));
         
         
         
         TableCommande.getItems().clear();
         TableCommande.setItems(ListCommande);
        } catch (SQLException ex) {
//            Logger.getLogger(GestionPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
                co = TableCommande.getSelectionModel().getSelectedItem();
        if(co == null)
        {
            JOptionPane.showMessageDialog(null , "Nothing selected" );
            
        }
        else
        {
            
//            idCommande.setText(String.valueOf(co.getId_Commande()));
//            idCommande.setDisable(true);
//            
//            
//            
//            nomMembre.setText(co.getNom());
//            nomMembre.setDisable(true);
//            
//            prenomMembre.setText(co.getPrenom());
//            prenomMembre.setDisable(true);
//
//            
//            prixTotal.setText(String.valueOf(co.getPrix_Total()));
//
//            
//            dateCommande.setValue(co.getDate_Commande().toLocalDate());
                  
        }
    }

    @FXML
    private void SearchCommande(KeyEvent event) throws SQLException {
        String msg = search.getText().concat("%");
        //(!(msg.equals(""))){ 
      
        
        CommandePService c= new CommandePService();
                ArrayList<CommandeP> l;
            l = (ArrayList<CommandeP>) c.RechercheCommandeParNomMembre(msg);
            ObservableList<CommandeP> ListCommande = FXCollections.observableArrayList(l);
         Nom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Prenom"));
         Prix_Total1.setCellValueFactory(new PropertyValueFactory<CommandeP, Double>("Prix_Total"));
         Date_Commande1.setCellValueFactory(new PropertyValueFactory<CommandeP, Date>("Date_Commande"));
            
             TableCommande.getItems().clear();
             TableCommande.setItems(ListCommande);
    }

    @FXML
    private void TrierParDate(ActionEvent event) throws SQLException {
                CommandePService c= new CommandePService();
                ArrayList<CommandeP> l;
            l = (ArrayList<CommandeP>) c.TrierCommande();
            ObservableList<CommandeP> ListCommande = FXCollections.observableArrayList(l);
         Nom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<CommandeP, String>("Prenom"));
         Prix_Total1.setCellValueFactory(new PropertyValueFactory<CommandeP, Double>("Prix_Total"));
         Date_Commande1.setCellValueFactory(new PropertyValueFactory<CommandeP, Date>("Date_Commande"));
            
             TableCommande.getItems().clear();
             TableCommande.setItems(ListCommande);
    }

    @FXML
    private void EnvoyerSMS(ActionEvent event) throws IOException {
        String tt = messagesms.getText();
        String url = "https://rest.nexmo.com/sms/json?api_key=716bb4f6&api_secret=6sSCg9yLEDYwhue5&to=21628176222&from=Vonage%20%APIs&text="+tt;
        String charset = "UTF-8";
URLConnection connection = new URL(url).openConnection();
connection.setDoOutput(true); // Triggers POST.
connection.setRequestProperty("Accept-Charset", charset);
connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);


InputStream response = connection.getInputStream();

Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setContentText("SMS Envoyé Avec Succé");
                                a.show(); 
    }

    @FXML
    private void EnvoyerMail(ActionEvent event) {
       
         }
           
    }
    

