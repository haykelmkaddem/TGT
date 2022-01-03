/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import static com.esprit.pidev.tgt.controllers.AfficherVenteUserController.panier;
import com.esprit.pidev.tgt.entities.ListeProduit;
import com.esprit.pidev.tgt.entities.Produit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class AfficherVenteUserController implements Initializable {
    
    public static List<ListeProduit> panier = new ArrayList<ListeProduit>();

    @FXML
    private Label lblNom;
    @FXML
    private ImageView imgProduit;
    @FXML
    private Label lblPrix;
    @FXML
    private Label lblquantite;
    @FXML
    private Button AjouterP;
    @FXML
    private Button btnLouer;
    @FXML
    private TextField qty;
    @FXML
    private Label qtyy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
       public void remplir(Object obj) {
           if (obj instanceof Produit) {
            qtyy.setText(String.valueOf(((Produit) obj).getQuantite()));
            qtyy.setVisible(false);
            btnLouer.setVisible(false);
            
            lblNom.setText(((Produit) obj).getNom());
            lblPrix.setText(((Produit) obj).getPrix()+"");
            lblquantite.setText(String.valueOf(((Produit) obj).getQuantite()));


        }
        
   }
       
   public int getQuantite(Object obj)
   {   int qtyy=0;
        if (obj instanceof Produit)
        {
             qtyy =(((Produit) obj).getQuantite());
        }
        return qtyy;
        
   }

        @FXML
    public void AjouterPanier(ActionEvent event) {
        
        
        String libelle = lblNom.getText();
        Double prix = Double.parseDouble(lblPrix.getText());
        int quantite = Integer.parseInt(qty.getText());
        int qt=Integer.parseInt(qtyy.getText());
        if(qt>=quantite)
        {
        panier.add(new ListeProduit(libelle,prix,quantite));
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setContentText("Produit ajouté au panier");
                                a.show(); 
        }
        else {
             Alert a = new Alert(Alert.AlertType.ERROR);
                                 a.setContentText("Quantité indisponible");
                                a.show(); 
        }
       
        
    }
    
}
