/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;



import com.esprit.pidev.tgt.controllers.AfficherVenteUserController;
import com.esprit.pidev.tgt.entities.CategorieProduit;
import com.esprit.pidev.tgt.entities.Produit;
import com.esprit.pidev.tgt.services.IProduitService;
import com.esprit.pidev.tgt.services.ProduitService;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class FrontOfficeEcommerceController implements Initializable {
    
    
     ObservableList<CategorieProduit> categorieProduitList = FXCollections.observableArrayList();
     ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
     IProduitService Produitservice = new ProduitService();

    @FXML
    private Tab afficheProduits;
//    private TableView<Produit> tableViewProduits;
//    private TableColumn<Produit, String> ImageProduit;
//    private TableColumn<Produit, String> NomProduit;
//    private TableColumn<Produit, String> DescriptionProduit;
//    private TableColumn<Produit, String> PrixProduit;
//    private TableColumn<Produit, String> QuantiteProduit;
    @FXML
    private TextField txtNom;
    @FXML
    private Button Panier;
    @FXML
    private Button Commande;
    @FXML
    private TableView tblView;
    @FXML
    private Button stat;
    @FXML
    private AnchorPane ancorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        loadData();
//        initColProduit();

//      tblView.getItems().addAll("Produit");
afficher();
    }    
    
    private void afficher() {
        afficherProduit();
    }
    
        private void afficherProduit () {
        tblView.getItems().remove(tblView.getItems());
        TableColumn<Produit, Integer> id //
              = new TableColumn<Produit, Integer>("id");
        id.setVisible(false);
      TableColumn<Produit, String> nom //
              = new TableColumn<Produit, String>("Nom");
      TableColumn<Produit, String> description//
              = new TableColumn<Produit, String>("description");
 
      TableColumn<Produit, Double> prix//
              = new TableColumn<Produit, Double>("prix");

      TableColumn<Produit, Integer> quantite //
              = new TableColumn<Produit, Integer>("quantité");

      TableColumn<Produit, String> image //
              = new TableColumn<Produit, String>("image");
  id.setCellValueFactory(
        new PropertyValueFactory<Produit, Integer>("id"));
  nom.setCellValueFactory(
        new PropertyValueFactory<Produit, String>("nom"));
  prix.setCellValueFactory(
        new PropertyValueFactory<Produit, Double>("prix"));
  quantite.setCellValueFactory(
        new PropertyValueFactory<Produit, Integer>("quantite"));
  description.setCellValueFactory(
        new PropertyValueFactory<Produit, String>("description"));
  image.setCellValueFactory(
        new PropertyValueFactory<Produit, String>("image"));
  tblView.getColumns().clear();
 IProduitService accessService = new ProduitService();
 List list;
 if (txtNom.getText() != null && !txtNom.getText().isEmpty()) {
     list = accessService.rechercheParNom(txtNom.getText());
 } else
  list = accessService.triParPrix();
       ObservableList obs = FXCollections.observableArrayList(list.toArray());
       
      tblView.getColumns().addAll(id,nom, prix,quantite,description,image);
      //addButtonModifierToTable();
      //addButtonSupprimerToTable();
      addButtonViewToTable();
       
      tblView.setItems(obs);
    }


    @FXML
    private void rechercheProduits(ActionEvent event) {
    }

    @FXML
    private void ConsulterPanier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

//        FXMLLoader loader = Rooting.navigate("Panier", "Panier");
//        PanierController controller = (PanierController) loader.getController();
    }

    @FXML
    private void Commande(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCommande.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
    }

    
    private void addButtonViewToTable() {
        TableColumn<Produit, Void> colBtn = new TableColumn("Details");

        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn = new Button("Details");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Parent root;
                            
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherVenteUser.fxml"));
                                root = loader.load();
                                AfficherVenteUserController controller = loader.getController();
                                controller.remplir(tblView.getSelectionModel().getSelectedItem());
                                controller.getQuantite(tblView.getSelectionModel().getSelectedItem());
                                
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                                
                                
                            } catch (IOException ex) {
                                Logger.getLogger(FrontOfficeEcommerceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
//                        tblView.getItems().clear();
                     
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tblView.getColumns().add(colBtn);

    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
    }
    
}
