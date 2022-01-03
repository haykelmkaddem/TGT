/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Contrat;
import com.esprit.pidev.tgt.services.ContratService;
import com.esprit.pidev.tgt.services.IContratService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ListeContratController implements Initializable {

    ObservableList<Contrat> contratListe = FXCollections.observableArrayList();
    IContratService contratService = new ContratService();
    private ContratService contratsService = new ContratService();
    @FXML
    private TableView<Contrat> contratView;
    @FXML
    private TableColumn<Contrat, String> nomC;
    @FXML
    private TableColumn<Contrat, String> titreCasting;
    @FXML
    private TableColumn<Contrat, Float> salaire;
    @FXML
    private TableColumn<Contrat, String> typecontrat;
    @FXML
    private TableColumn<Contrat, Integer> duree;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        loadData();
    }

    private void initCol() {
        titreCasting.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contrat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Contrat, String> param) {
                String result = param.getValue().getId_casting() == null ? "" : param.getValue().getId_casting().getTitreCasting();
                return new SimpleObjectProperty<String>(result);
            }
        });
        nomC.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contrat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Contrat, String> param) {
                String result = param.getValue().getIdcandidat() == null ? "" : param.getValue().getIdcandidat().getNomC();
                return new SimpleObjectProperty<String>(result);
            }
        });
        salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typecontrat.setCellValueFactory(new PropertyValueFactory<>("typeContrat"));
        duree.setCellValueFactory(new PropertyValueFactory<>("dureeContrat"));
    }

    public void loadData() {

        try {
            contratListe = FXCollections.observableArrayList(contratService.findAll());
            System.out.println(contratListe);
        } catch (SQLException ex) {

        }

        contratView.setItems(contratListe);
    }

    @FXML
    private void showDetaille(ActionEvent event) {
    }

    @FXML
    private void voircontrat(ActionEvent event) {
        Contrat selectedContrat = contratView.getSelectionModel().getSelectedItem();
        if (selectedContrat == null) {
            System.out.println("choisir un candidat");

        } else {
            try {

                OutputStream file = new FileOutputStream(new File("C:/wamp64/www/TGTjava-travail indiv/javaTGT/src/resources/images/Contrat" + selectedContrat.getId() + ".pdf"));

                Document document = new Document();

                PdfWriter.getInstance(document, file);

                document.open();

                document.add(new Paragraph("Contrat: " + selectedContrat.getTypeContrat().toString()));

                document.add(new Paragraph(new Date().toString()));
                document.add(new Paragraph("titre du casting : " + selectedContrat.getId_casting().getTitreCasting().toString()));
                document.add(new Paragraph("nom du candidat: " + selectedContrat.getIdcandidat().getNomC().toString()));
                document.add(new Paragraph("cin du candidat: " + selectedContrat.getIdcandidat().getCinCondidat()));
                document.add(new Paragraph("tel: " + selectedContrat.getIdcandidat().getTel()));
                document.add(new Paragraph("ce contrat est de salaire de : " + selectedContrat.getSalaire()));
                document.add(new Paragraph("le duree de ce contrat est se  : " + selectedContrat.getDureeContrat()));
                document.add(new Paragraph("merci pour votre attension"));

                document.close();

                file.close();

            } catch (Exception e) {

                e.printStackTrace();

            }
        }
    }

}
