/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;


import com.esprit.pidev.tgt.entities.Paiement;
import com.esprit.pidev.tgt.utils.DataBase;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PaiementService implements IPaiementService{
    private Connection con;
    private Statement ste;
    
    public PaiementService() {
        con = DataBase.getInstance().getConnection();
    }

    
    @Override
    public void ajouterPaiement(Paiement p) throws SQLException {
PreparedStatement pre=con.prepareStatement("INSERT INTO Paiement "
        +"( `Id_Membre`, `Type_Paiement`, `Num_Carte`,`Date_Expiration`,`Code_Sec`,`Pays`) "
        + "VALUES ( ?, ?, ?, ?, ?, ?);");
        pre.setInt(1, p.getId_Membre());
        pre.setString(2, p.getType_Paiement());
        pre.setInt(3, p.getNum_Carte());
        pre.setDate(4, p.getDate_Expiration());
        pre.setInt(5, p.getCode_Sec());
        pre.setString(6, p.getPays());
        
        pre.executeUpdate();    }

    @Override
    public boolean SupprimerPaiement(Paiement p) throws SQLException {
        String req2="DELETE from paiement where Id_Paiement=?";
           PreparedStatement ps = con.prepareStatement(req2);
           ps.setInt(1, p.getId_Paiement());    
           if (ps.executeUpdate()==1)
            {
                return true;
            }
            else 
                return false;    }

    @Override
    public boolean ModifierPaiement(Paiement p) throws SQLException {
String req = "update Paiement set Type_Paiement=?,Num_Carte=?,Date_Expiration=?,Code_sec=?,Pays=?"
        + " where Id_Paiement =?";
            PreparedStatement ps = con.prepareStatement(req);
        
        ps.setString(1, p.getType_Paiement());
        ps.setInt(2, p.getNum_Carte());
        ps.setDate(3, p.getDate_Expiration());
        ps.setInt(4, p.getCode_Sec());
        ps.setString(5, p.getPays());
        ps.setInt(6, p.getId_Paiement());
            if (ps.executeUpdate()==1)
            {
                return true;
            }
            else 
                return false;    }

    @Override
    public List<Paiement> AfficherPaiement() throws SQLException {
    List<Paiement> arr = new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT m.Nom_Membre,m.Prenom_Membre,p.Num_Carte,p.Date_Expiration,p.Code_Sec,p.Type_Paiement,p.Pays,m.Email_Membre"
            + " from paiement p , membre m where m.Id_Membre = p.Id_Membre");
     while (rs.next()) {   
        //int Id_Paiement = rs.getInt("Id_Paiement");
        //int Id_Membre =rs.getInt("Id_Membre");
        String Nom=rs.getString("Nom_Membre");
        String Prenom=rs.getString("Prenom_Membre"); 
        String Type_Paiement = rs.getString("Type_Paiement");
        String Email=rs.getString("Email_Membre"); 
        int Num_Carte = rs.getInt("Num_Carte");
        Date Date_Expiration = rs.getDate("Date_Expiration");
        int Code_Sec = rs.getInt("Code_Sec");
        String Pays = rs.getString("Pays");
        Paiement p=new Paiement(Nom,Prenom,Type_Paiement,Email,Num_Carte,Date_Expiration,Code_Sec,Pays);
               arr.add(p);
     }
    return arr;
    } 
    
    
    @Override
    public List<Paiement> AfficherPaiement1() throws SQLException{
        List<Paiement> arr = new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT p.*,m.Nom_Membre,m.Prenom_Membre,m.Email_Membre"
            + " from paiement p , membre m where m.Id_Membre = p.Id_Membre");
     while (rs.next()) {   
        int Id_Paiement = rs.getInt("Id_Paiement");
        int Id_Membre =rs.getInt("Id_Membre");
        String Nom=rs.getString("m.Nom_Membre");
        String Prenom=rs.getString("m.Prenom_Membre"); 
        String Type_Paiement = rs.getString("p.Type_Paiement");
        String Email=rs.getString("m.Email_Membre"); 
        int Num_Carte = rs.getInt("p.Num_Carte");
        Date Date_Expiration = rs.getDate("p.Date_Expiration");
        int Code_Sec = rs.getInt("p.Code_Sec");
        String Pays = rs.getString("p.Pays");
        Paiement p=new Paiement(Id_Paiement,Id_Membre,Nom,Prenom,Type_Paiement,Email,Num_Carte,Date_Expiration,Code_Sec,Pays);
               arr.add(p);
     }
    return arr;
    }
    
    @Override
    public List<Paiement> RecherchePaiementParNomMembre(String Nom_Membre) throws SQLException {
    List<Paiement> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT p.* , m.Nom_Membre,m.Prenom_Membre,m.Email_Membre "
            + "from paiement p , membre m "
            + "where m.Id_Membre = p.Id_Membre and m.Nom_Membre LIKE '"+Nom_Membre+"%'");
     while (rs.next()) {                
               int Id_Paiement = rs.getInt("Id_Paiement");
        int Id_Membre =rs.getInt("Id_Membre");
        String Nom=rs.getString("Nom_Membre");
        String Prenom=rs.getString("Prenom_Membre"); 
        String Type_Paiement = rs.getString("Type_Paiement");
        String Email=rs.getString("Email_Membre"); 
        int Num_Carte = rs.getInt("Num_Carte");
        Date Date_Expiration = rs.getDate("Date_Expiration");
        int Code_Sec = rs.getInt("Code_Sec");
        String Pays = rs.getString("Pays");
        Paiement p=new Paiement(Id_Paiement,Id_Membre,Nom,Prenom,Type_Paiement,Email,Num_Carte,Date_Expiration,Code_Sec,Pays);
               arr.add(p);
     }
    return arr;
    }

    @Override
    public List<Paiement> TrierPaiement() throws SQLException {

    List<Paiement> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT p.* , m.Nom_Membre,m.Prenom_Membre,m.Email_Membre"
            + " from paiement p , membre m where m.Id_Membre = p.Id_Membre order by m.Prenom_Membre");
     while (rs.next()) {                
        int Id_Paiement = rs.getInt("Id_Paiement");
        int Id_Membre =rs.getInt("Id_Membre");
        String Nom=rs.getString("Nom_Membre");
        String Prenom=rs.getString("Prenom_Membre"); 
        String Type_Paiement = rs.getString("Type_Paiement");
        String Email=rs.getString("Email_Membre"); 
        int Num_Carte = rs.getInt("Num_Carte");
        Date Date_Expiration = rs.getDate("Date_Expiration");
        int Code_Sec = rs.getInt("Code_Sec");
        String Pays = rs.getString("Pays");
        Paiement p=new Paiement(Id_Paiement,Id_Membre,Nom,Prenom,Type_Paiement,Email,Num_Carte,Date_Expiration,Code_Sec,Pays);
               arr.add(p);
     }
    return arr;
    }

    @Override
    public void PaiementEffectue() throws AWTException {
            //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Notification");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Notification");
        tray.add(trayIcon);

        trayIcon.displayMessage("Paiement Effectué", "Paiement", TrayIcon.MessageType.INFO); 
        
        
        }
    
}
