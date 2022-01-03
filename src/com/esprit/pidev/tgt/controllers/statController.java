/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Produit;
import com.esprit.pidev.tgt.services.ProduitService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

/**
 * FXML Controller class
 *
 * @author Haykel M'kaddem
 */
public class statController implements Initializable {

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<PieChart.Data> pieChartData
= FXCollections.observableArrayList(
        
        
        new PieChart.Data("Piano", 46),
        new PieChart.Data("piano HammerClavier", 20),
        new PieChart.Data("Drum", 12));
pieChart.setData(pieChartData);
    }    
    
//        public void chart(int cv) throws SQLException{
//        
//         ProduitService uu = new ProduitService();
//        
//     int progress=0;
//     int somme=0;
//    // int progress=0;
//     // User_StoryCRUD us =new User_StoryCRUD();
//         List <Produit> listStory = uu.findAll();
//         
//         
//          ListIterator<Produit> litr = null;
//            
//            
//            litr=listStory.listIterator();
//            
//            
//            
//            
//            while(litr.hasNext()){
//                
//             somme =  5 + somme;
//            // progress= litr.next().getDoing() +progress;
//               // System.out.println(somme);
//         
//            } System.out.println(somme);
//            
//            
//            
//            
//            
//             List <Produit> listStoryy = uu.findAll();
//         
//         
//          ListIterator<Produit> litrr = null;
//            
//            
//            litrr=listStoryy.listIterator();
//            
//            
//            
//            
//            while(litrr.hasNext()){
//                
//            // somme =  litr.next().getTotal_estimation_userStory_jours() + somme;
//             progress= 5+progress;
//               // System.out.println(somme);
//         
//            } //System.out.println(somme);
//            System.out.println(progress);
//            
//            
//            
//            ObservableList<PieChart.Data> pieChartData =
//                FXCollections.observableArrayList(
//              //  new PieChart.Data("Grapefruit", 13),
//               // new PieChart.Data("Oranges", 25),
//                new PieChart.Data("Done", progress),
//                new PieChart.Data("ToDO", somme-progress));
//        pieChart.setData(pieChartData);
//            
//            
//          
//}
//        
//    @FXML
//    private void DisplayChart(ActionEvent event) {
//        
//         try{
//        Produit p = tableviewfeature.getSelectionModel().getSelectedItem();
//        
//            //System.out.println(p.getId_backlog());
//            int lh=p.getId_feature();
//       // afficheUsrStory(lh);
//        chart(lh);
//        }catch  (Exception e) {
//           
//         AlertMSg();   
//      System.out.println("Something wenthhhh wrong.");}
//        
//        
//    }
    
    
 
//public class PieChartSample extends Application {
// 
//    @Override public void start(Stage stage) {
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Imported Fruits");
//        stage.setWidth(500);
//        stage.setHeight(500);
// 
//        ObservableList<PieChart.Data> pieChartData =
//                FXCollections.observableArrayList(
//                new PieChart.Data("Grapefruit", 13),
//                new PieChart.Data("Oranges", 25),
//                new PieChart.Data("Plums", 10),
//                new PieChart.Data("Pears", 22),
//                new PieChart.Data("Apples", 30));
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Imported Fruits");
//
//        ((Group) scene.getRoot()).getChildren().add(chart);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    
//}
}
