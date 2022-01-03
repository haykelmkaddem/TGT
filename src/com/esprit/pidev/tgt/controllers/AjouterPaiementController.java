/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;


import com.esprit.pidev.tgt.entities.Paiement;
import com.esprit.pidev.tgt.services.PaiementService;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
 * @author ASUS
 */
public class AjouterPaiementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Liste Des Pays 
    ObservableList<String> ListPays = FXCollections.observableArrayList("Tunisia","Algeria","Maroc","Egypte");
    
    @FXML
    private TextField IdMembre;
    @FXML
    private RadioButton MasterCard;
     @FXML
    private ToggleGroup TypePaiement;
    @FXML
    private RadioButton Visa;
    @FXML
    private RadioButton Edinar;
    @FXML
    private TextField NumCarte;
    @FXML
    private DatePicker DateExpiration;
    @FXML
    private TextField CodeSec;
    @FXML
    private ChoiceBox Pays;
    @FXML
    private Button Payer;
    @FXML
    private Button facture;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Liste Choix Pays 
        Pays.setValue("Tunisia");
        Pays.setItems(ListPays);
    }    
    
    @FXML
    private void AjouterPaiement(ActionEvent event) throws AWTException {
        
        
        
        if (!estUnEntier(NumCarte.getText())) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Erreur payement");
          alert.setHeaderText(null);
          alert.setContentText("numéro de carte invalide!!!");
          alert.show();
      }else if (NumCarte.getLength()!=10) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Erreur payement");
          alert.setHeaderText(null);
          alert.setContentText("numéro de carte invalide!!! longeur 10 !");
          alert.show();
      }else if (DateExpiration.getValue().isBefore(LocalDate.now())) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Erreur payement");
          alert.setHeaderText(null);
          alert.setContentText("DATE invalide!!! !");
          alert.show();
      }else if (CodeSec.getLength()!=3) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Erreur payement");
          alert.setHeaderText(null);
          alert.setContentText("code sécurité invalide!!! longeur 3 !");
          alert.show();
      } else {
       
        try {
        
        int idM = Integer.parseInt(IdMembre.getText());
   
        RadioButton selectedRadioButton = (RadioButton) TypePaiement.getSelectedToggle();
        String TypeP = selectedRadioButton.getText();
        
        
        int NumC = Integer.parseInt(NumCarte.getText());
        
        
        java.sql.Date DateE = java.sql.Date.valueOf(DateExpiration.getValue());
        
        
        int CodeS = Integer.parseInt(CodeSec.getText());
        
        
        String PaysSel = (String) Pays.getSelectionModel().getSelectedItem();
        
        
        
        PaiementService p = new PaiementService();
        Paiement p1 = new Paiement(0,idM,TypeP,NumC,DateE,CodeS,PaysSel);
            System.out.println(p1);
            p.ajouterPaiement(p1);
            p.PaiementEffectue();
            

        } catch (SQLException ex) {
            Logger.getLogger(AjouterPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
         final String username = "maillelesprit@gmail.com";
                final String password = "21264614";
                
                
                Properties props = new Properties();
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                
                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });
//                 try {
//              Statement stmp = con.createStatement();
//                     String req2 = "haykel.mkaddem1@esprit.tn";
//        ResultSet resultat = stmp.executeQuery(req2);
//         while(resultat.next()){
//           String to= resultat.getString("Email_Membre");
String to= "haykel.mkaddem1@esprit.tn";
           
           
            try {
                    
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("dkhiliwalid451@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    message.setSubject("Votre confirmation de commande du"+LocalDate.now());
                    message.setText("\n"+"*******commande passée avec succès*******");
                    Transport.send(message);
                    JOptionPane.showMessageDialog(null, "Email sent successfully!");
                } catch (MessagingException ex) {
                    JOptionPane.showMessageDialog(null, "Oops something went wrong" + "\n" + ex.getMessage());
                }
        }
    }

    @FXML
    private void Facture(ActionEvent event) throws SQLException, IOException {
//         int idM = Integer.parseInt(IdMembre.getText());
//         CommandeService c= new CommandeService();
//         c.FacturePdf(3);
         
    }
    
          public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
      public static boolean isValid(String email)
{
	if( email!=null && email.trim().length()>0 )
		return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
	return false;
}
    
}
