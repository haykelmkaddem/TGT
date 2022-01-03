/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.entities.Organisations;
import com.esprit.pidev.tgt.services.CastingService;
import com.esprit.pidev.tgt.services.EventsService;
import com.esprit.pidev.tgt.services.ICastingService;
import com.esprit.pidev.tgt.services.IEventsService;
import com.esprit.pidev.tgt.services.IOrganisationsService;
import com.esprit.pidev.tgt.services.OrganisationsService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import java.sql.SQLException;

        

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class BackOfficeOrganisationsController implements Initializable {
       ObservableList<Organisations> organisationstList = FXCollections.observableArrayList();
       IOrganisationsService organisationservice = new OrganisationsService();
       private OrganisationsService organisationsService = new OrganisationsService();
               
        ObservableList<Casting> castingList = FXCollections.observableArrayList();
        ICastingService castingservice = new CastingService();
        private CastingService castingService = new CastingService();
               
        ObservableList<Events> eventsList = FXCollections.observableArrayList();
        IEventsService eventsservice = new EventsService();
        private EventsService eventsService = new EventsService();
        
        
        EventsService es =new EventsService();
        OrganisationsService os =new OrganisationsService();
        CastingService cs = new CastingService();
 
    @FXML
    private Button gestionOrganisation;
    @FXML
    private Tab afficheOrganisation;
    private TextField recherche;
    @FXML
    private TableView<Organisations> tableViewOrganisations;
    @FXML
    private TableColumn<Organisations, String> NomOrganisation;
    @FXML
    private TableColumn<Organisations, String> Apropos;
    @FXML
    private TableColumn<Organisations, String> AdresseOrganisation;
    @FXML
    private TableColumn<Organisations, Integer> tel_organisation;
    @FXML
    private TableColumn<Organisations, String> Email_Org;
    @FXML
    private TableColumn<Organisations, String> LoginOrganisation;
    @FXML
    private TableColumn<Organisations, String> PasswordOrganisation;
    @FXML
    private Button ajouterOrganisations;
    @FXML
    private Button supprimerOrganisations;
    @FXML
    private TableView<Casting> tableViewCasting;
    @FXML
    private TableColumn<Casting, String> TitreCasting;
    @FXML
    private TableColumn<Casting, String> DescriptionCasting;
    @FXML
    private TableColumn<Casting, String> DateCasting;
    @FXML
    private TableColumn<Casting, String> DateLP;
    @FXML
    private TableColumn<Casting, String> AdresseCasting;
    @FXML
    private TableColumn<Casting, String> ImageCasting;
    @FXML
    private TableColumn<Casting, String> ThemeCasting;
    @FXML
    private Button ajouterCasting;
    @FXML
    private Button supprimerCasting;
    @FXML
    private Tab AfficheCasting;
    @FXML
    private TableColumn<Events, String> TitreEvents;
    @FXML
    private TableColumn<Events, String> DescriptionEvents;
    @FXML
    private TableColumn<Events, String> ThemeEvents;
    @FXML
    private TableColumn<Events, String> AdresseEvents;
    @FXML
    private TableColumn<Events, String> DateEvents;
    @FXML
    private TableColumn<Events, String> HeureEvents;
    @FXML
    private TableColumn<Events, String> PrixEvents;
    @FXML
    private TableColumn<Events, String> ImageEvents;
    @FXML
    private Button AjouterEvents;
    @FXML
    private Button supprimerEvents;
    @FXML
    private Tab afficheEvents;
    @FXML
    private TableView<Events> tableViewEvents;
    @FXML
    private Button Qr;
    @FXML
    private ImageView logout;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button condidature;
    @FXML
    private Button utilisateur;
    @FXML
    private Button publication;
    @FXML
    private Button produit;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       initCol();
       initColCasting();
       initColEvents();
       //*****************************Organisations*****************************************//
       tableViewOrganisations.setEditable(true);
       NomOrganisation.setCellFactory(TextFieldTableCell.forTableColumn());
       NomOrganisation.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setNomOrganisation(event.getNewValue());
           organisationsService.modifierOrganisations(event.getRowValue());      
       });
        Apropos.setCellFactory(TextFieldTableCell.forTableColumn());
       Apropos.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setApropos(event.getNewValue());
           organisationsService.modifierOrganisations(event.getRowValue());      
       });
          AdresseOrganisation.setCellFactory(TextFieldTableCell.forTableColumn());
       AdresseOrganisation.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setAdresseOrganisation(event.getNewValue());
           organisationsService.modifierOrganisations(event.getRowValue());      
       });
          Email_Org.setCellFactory(TextFieldTableCell.forTableColumn());
       Email_Org.setOnEditCommit((event) -> {
           Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(event.getNewValue());
        if(!(m.find() && m.group().equals(event.getNewValue()))){
            AlertMaker.showErrorMessage("Email Invalide", "Veuillez saisir un mail sous le format : xxx@xxx.xxx");
        }
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setEmail_Org(event.getNewValue());    
           organisationsService.modifierOrganisations(event.getRowValue());    
           
       });
          tel_organisation.setCellFactory(TextFieldTableCell.<Organisations, Integer>forTableColumn(new IntegerStringConverter()));
       tel_organisation.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setTel_organisation(event.getNewValue());
           organisationsService.modifierOrganisations(event.getRowValue());      
       });
          LoginOrganisation.setCellFactory(TextFieldTableCell.forTableColumn());
       LoginOrganisation.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setLoginOrganisation(event.getNewValue());
           organisationsService.modifierOrganisations(event.getRowValue());      
       });
          PasswordOrganisation.setCellFactory(TextFieldTableCell.forTableColumn());
          PasswordOrganisation.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setPasswordOrganisation(event.getNewValue());
           organisationsService.modifierOrganisations(event.getRowValue());      
       });
       
          //******************************Casting**************************************//
          tableViewCasting.setEditable(true);
          TitreCasting.setCellFactory(TextFieldTableCell.forTableColumn());
          TitreCasting.setOnEditCommit((event) -> {
          event.getTableView().getItems().get(event.getTablePosition().getRow()).setTitreCasting(event.getNewValue());
              System.out.println(event.getRowValue());
          castingService.modifierCasting(event.getRowValue());      
       });
        DescriptionCasting.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionCasting.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setDescriptionCasting(event.getNewValue());
           castingService.modifierCasting(event.getRowValue());      
       });
//         DateCasting.setCellFactory(TextFieldTableCell.<Casting,Date >forTableColumn(new DateStringConverter));
//       DateCasting.setOnEditCommit((event) -> {
//           event.getTableView().getItems().get(event.getTablePosition().getRow()).setDateCasting(event.getNewValue());
//           castingService.modifierCasting(event.getRowValue());      
//       });
//         DateLP.setCellFactory(TextFieldTableCell.forTableColumn());
//       DateLP.setOnEditCommit((event) -> {
//           event.getTableView().getItems().get(event.getTablePosition().getRow()).setDateLP(event.getNewValue());
//           castingService.modifierCasting(event.getRowValue());      
//       });
         AdresseCasting.setCellFactory(TextFieldTableCell.forTableColumn());
       AdresseCasting.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setAdresseCasting(event.getNewValue());
           castingService.modifierCasting(event.getRowValue());      
       });
         ThemeCasting.setCellFactory(TextFieldTableCell.forTableColumn());
       ThemeCasting.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setThemeCasting(event.getNewValue());
           castingService.modifierCasting(event.getRowValue());      
       });
       //**********************************Evenement******************************************//
         tableViewEvents.setEditable(true);
       TitreEvents.setCellFactory(TextFieldTableCell.forTableColumn());
       TitreEvents.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setTitreEvents(event.getNewValue());
           eventsService.modifierEvents(event.getRowValue());      
       });
        DescriptionEvents.setCellFactory(TextFieldTableCell.forTableColumn());
       DescriptionEvents.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setDescriptionEvents(event.getNewValue());
           eventsService.modifierEvents(event.getRowValue());      
       });
        ThemeEvents.setCellFactory(TextFieldTableCell.forTableColumn());
        ThemeEvents.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setThemeEvents(event.getNewValue());
           eventsService.modifierEvents(event.getRowValue());      
       });
        AdresseEvents.setCellFactory(TextFieldTableCell.forTableColumn());
       AdresseEvents.setOnEditCommit((event) -> {
           event.getTableView().getItems().get(event.getTablePosition().getRow()).setAdresseEvents(event.getNewValue());
           eventsService.modifierEvents(event.getRowValue());      
       });
//        PrixEvents.setCellFactory(TextFieldTableCell.<Events , Integer>forTableColumn(new IntegerStringConverter()));
//       PrixEvents.setOnEditCommit((event) -> {
//           event.getTableView().getItems().get(event.getTablePosition().getRow()).setPrixEvents(event.getNewValue());
//           eventsService.modifierEvents(event.getRowValue());      
//       });
       
       
       
       loadData();
    }    
    
    private void initCol() {
                 NomOrganisation.setCellValueFactory(new PropertyValueFactory<>("NomOrganisation"));
                 Apropos.setCellValueFactory(new PropertyValueFactory<>("Apropos"));
                 AdresseOrganisation.setCellValueFactory(new PropertyValueFactory<>("AdresseOrganisation"));
                 tel_organisation.setCellValueFactory(new PropertyValueFactory<>("tel_organisation"));
                 Email_Org.setCellValueFactory(new PropertyValueFactory<>("Email_Org"));
                 LoginOrganisation.setCellValueFactory(new PropertyValueFactory<>("LoginOrganisation"));
                 PasswordOrganisation.setCellValueFactory(new PropertyValueFactory<>("PasswordOrganisation"));
    }
     private void initColCasting() {
                 TitreCasting.setCellValueFactory(new PropertyValueFactory<>("TitreCasting"));
                 DescriptionCasting.setCellValueFactory(new PropertyValueFactory<>("DescriptionCasting"));
                 DateCasting.setCellValueFactory(new PropertyValueFactory<>("DateCasting"));
                 DateLP.setCellValueFactory(new PropertyValueFactory<>("DateLP"));
                 AdresseCasting.setCellValueFactory(new PropertyValueFactory<>("AdresseCasting"));
                 ImageCasting.setCellValueFactory(new PropertyValueFactory<>("ImageCasting"));
                 ThemeCasting.setCellValueFactory(new PropertyValueFactory<>("ThemeCasting"));

    }
      private void initColEvents() {
                 TitreEvents.setCellValueFactory(new PropertyValueFactory<>("TitreEvents"));
                 DescriptionEvents.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvents"));
                 ThemeEvents.setCellValueFactory(new PropertyValueFactory<>("ThemeEvents"));
                 AdresseEvents.setCellValueFactory(new PropertyValueFactory<>("AdresseEvents"));
                 PrixEvents.setCellValueFactory(new PropertyValueFactory<>("PrixEvents"));
                 ImageEvents.setCellValueFactory(new PropertyValueFactory<>("ImageEvents"));
                 
                 DateEvents.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Events, String>, ObservableValue<String>>() {
                  @Override
                     public ObservableValue<String> call(TableColumn.CellDataFeatures<Events, String> param) {
                     String result = param.getValue().getDateEvents().toLocalDate().toString();
                     return new SimpleObjectProperty<String>(result);
    }
                       });
                  HeureEvents.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Events, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Events, String> param) {
               String result = param.getValue().getDateEvents().toLocalTime().toString();
                return new SimpleObjectProperty<String>(result);
            }
        });
      }
                 
                
    
    public void loadData() {
       

        try {
            organisationstList = FXCollections.observableArrayList(organisationservice.afficherOrganisations());
            castingList = FXCollections.observableArrayList(castingService.afficherCasting()); 
            eventsList = FXCollections.observableArrayList(eventsService.findAll()); 
           
            
        } catch (SQLException ex) {
            
        }
    
       
         
        tableViewCasting.setItems(castingList);
        tableViewOrganisations.setItems(organisationstList);
        tableViewEvents.setItems(eventsList);

    }
    

    @FXML
    private void showDetaille(ActionEvent event) {
    }

    @FXML
    private void ajouterOrganisations(ActionEvent event) {
               FXMLLoader loader = Rooting.navigate("Ajouter", "OrganisationsFormulaire");
               

    }

    private void modifierOrganisations(ActionEvent event) {
       Organisations selectedOrganisations  = tableViewOrganisations.getSelectionModel().getSelectedItem();
        if (selectedOrganisations==null){
            System.out.println("choisir une Organisation");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "OrganisationsFormulaire");
        OrganisationsFormulaireController controller = (OrganisationsFormulaireController) loader.getController();
         controller.initfields(selectedOrganisations,this);
        
        }
    }

    @FXML
    private void supprimerOrganisations(ActionEvent event) throws SQLException {
        Organisations selectedOrganisations  = tableViewOrganisations.getSelectionModel().getSelectedItem();
        if (selectedOrganisations==null){
            System.out.println("choisir une organisation");
            
        }else{
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionOr");
        ValidationDeSuppressionOrController controller = (ValidationDeSuppressionOrController) loader.getController();
        os.supprimerOrganisations(selectedOrganisations);
        organisationstList.remove(selectedOrganisations);
//        controller.initfieldsOrg(selectedOrganisations,this);
        
        }
      
     
    }
//******************************************* Back Casting ***************************************************//
    
  
      
    @FXML
    private void ajouterCasting(ActionEvent event) {
                       FXMLLoader loader = Rooting.navigate("Ajouter Casting", "CastingFormulaire");
    }

    private void modifierCasting(ActionEvent event) {
        Casting selectedCasting  = tableViewCasting.getSelectionModel().getSelectedItem();
        if (selectedCasting==null){
            System.out.println("choisir une casting");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "CastingFormulaire");
        CastingFormulaireController controller = (CastingFormulaireController) loader.getController();
         controller.initfields(selectedCasting,this);
        
        }
    }

    @FXML
    private void supprimerCasting(ActionEvent event) throws SQLException   {
         Casting selectedCasting  = tableViewCasting.getSelectionModel().getSelectedItem();
        if (selectedCasting==null){
            System.out.println("choisir un casting");
            
        }else{
        cs.supprimerCasting(selectedCasting);    
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionOr");
                

        ValidationDeSuppressionOrController controller = (ValidationDeSuppressionOrController) loader.getController();
//        controller.initfieldsCasting(selectedCasting,this);
        
        }

    }

    
    //******************************************* Back Events ***************************************************//
   
    @FXML
    private void AjouterEvents(ActionEvent event) {
        FXMLLoader loader = Rooting.navigate("Ajouter", "EventsFormulaire");
        loadData();
    }

    private void modifierEvents(ActionEvent event) {
        Events selectedEvents  = tableViewEvents.getSelectionModel().getSelectedItem();
        if (selectedEvents==null){
            System.out.println("choisir une evenements");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "EventsFormulaire");
        EventsFormulaireController controller = (EventsFormulaireController) loader.getController();
         controller.initfieldsEvents(selectedEvents,this);
    }
    }


    @FXML
    private void supprimerEvents(ActionEvent event) throws SQLException {
        Events selectedEvents  = tableViewEvents.getSelectionModel().getSelectedItem();
        if (selectedEvents==null){
            System.out.println("choisir un evenement");
            
        }else{
        es.supprimerEvents(selectedEvents);    
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppression");
                

        ValidationDeSuppressionOrController controller = (ValidationDeSuppressionOrController) loader.getController();
        controller.initfieldsEvents(selectedEvents,this);
        
        }

    }
 
    private void rechercheEvents(ActionEvent event) throws SQLException  {
         String val = this.recherche.getText();
       ObservableList<Events> events = FXCollections.observableArrayList( eventsService.Recherche(val));       
        tableViewEvents.setItems(events);
        tableViewEvents.refresh();
       // System.out.println("wselt controller zeda");
       // System.out.println(val);
        //System.out.println(cours);
    }

        @FXML
    private void utilisateur(ActionEvent event) {
         Rooting.navigate("condidature", "BackOfficeProfile");
          closeStage();
    }
    
    private void candidature(ActionEvent event) {
         Rooting.navigate("condidature", "BackOfice");
          closeStage();
    }

    @FXML
    private void organisation(ActionEvent event) {
        Rooting.navigate("organisation", "BackOfficeOrganisations");
          closeStage();
    }

    @FXML
    private void publication(ActionEvent event) {
        Rooting.navigate("publication", "BackOffice");
          closeStage();
    }

    @FXML
    private void produit(ActionEvent event) {
          Rooting.navigate("produit", "BackOfficeEcommerce");
          closeStage();
    }

    @FXML
    private void formation(ActionEvent event) {
    
    }
    
       
    private void closeStage() {
        ((Stage) ajouterCasting.getScene().getWindow()).hide();
    }

   
    @FXML
    private void code(ActionEvent event) throws IOException {


            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/com/esprit/pidev/tgt/views/QrCode.fxml"));
            Parent par = Loader.load();
            Events e = tableViewEvents.getSelectionModel().getSelectedItem();
            QrCodeController cont = Loader.getController();
            try {
                cont.ini(e);
          } 
            catch (SQLException ex) {
                System.out.println(ex);
            }
}

  @FXML
    private void logout(MouseEvent event) {
        Rooting.navigate("Connexion", "login");
        anchorPane.getScene().getWindow().hide();
    }

    @FXML
    private void condidature(ActionEvent event) {
    }
    
    
        }
    
