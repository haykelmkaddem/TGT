/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;



import com.esprit.pidev.tgt.entities.CommandeP;
import com.esprit.pidev.tgt.entities.ListeProduit;
import com.esprit.pidev.tgt.utils.DataBase;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ASUS
 */
public class CommandePService implements ICommandePService {
    private Connection con;
    private Statement ste;
    
    public  CommandePService()
    {
        con = DataBase.getInstance().getConnection();
        
    }

    @Override
    public void ajouterCommande(CommandeP c) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `commandep` (`Id_Commande`, `Date_Commande`, `Prix_Total`, `Id_Membre`) VALUES (NULL, '" + c.getDate_Commande() + "', '" + c.getPrix_Total() + "', '" + c.getId_Membre()+ "');";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public void ajouterCommande1(CommandeP c) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("INSERT INTO commandep ( `Date_Commande`, `Prix_Total`, `Id_Membre`) VALUES ( ?, ?, ?);");
        pre.setDate(1, c.getDate_Commande());
        pre.setDouble(2, c.getPrix_Total());
        pre.setInt(3, c.getId_Membre());
        pre.executeUpdate();
          }
    
    @Override
    public boolean SupprimerCommande(CommandeP c) throws SQLException {
           String req2="DELETE from commandep where Id_Commande=?";
           PreparedStatement ps = con.prepareStatement(req2);
           ps.setInt(1, c.getId_Commande());    
           if (ps.executeUpdate()==1)
            {
                return true;
            }
            else 
                return false;
    }

    @Override
    public boolean ModifierCommande(CommandeP c) throws SQLException {
 String req = "update Commandep set Prix_Total=?,Date_Commande=? where Id_Commande =?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setDouble(1, c.getPrix_Total());
            ps.setDate(2, c.getDate_Commande());
            ps.setInt(3,c.getId_Commande());
            if (ps.executeUpdate()==1)
            {
                return true;
            }
            else 
                return false;
    }

    @Override
    public List<CommandeP> AfficherCommande() throws SQLException {
    List<CommandeP> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select c.*,m.Nom_Membre,m.Prenom_Membre from commandep c ,membre m where c.Id_Membre= m.Id_Membre");
     while (rs.next()) {                
               int Id_Commande=rs.getInt("Id_Commande");
               Date Date_Commande=rs.getDate("Date_Commande");
               String Nom=rs.getString("m.Nom_Membre");
               String Prenom=rs.getString("m.Prenom_Membre");
               Double Prix_Total=rs.getDouble("Prix_Total");
               int Id_Membre=rs.getInt("Id_Membre");
//               System.out.println("la Commande est "+Id_Commande+" est Date = "+Date_Commande+" Age = "+
//                        res.getString("age"));
               CommandeP c=new CommandeP(Id_Commande,Nom,Prenom, Date_Commande, Prix_Total, Id_Membre);
               arr.add(c);
     }
    return arr;
    }
    @Override
    public List<CommandeP> TrierCommande() throws SQLException {
    List<CommandeP> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select c.*,m.Nom_Membre,m.Prenom_Membre from commandep c ,membre m where c.Id_Membre= m.Id_Membre order by c.Date_Commande;");
     while (rs.next()) {                
               int Id_Commande=rs.getInt("Id_Commande");
               String Nom=rs.getString("Nom_Membre");
               String Prenom=rs.getString("Prenom_Membre");
               Date Date_Commande=rs.getDate("Date_Commande");
               Double Prix_Total=rs.getDouble("Prix_Total");
               int Id_Membre=rs.getInt("Id_Membre");
//               System.out.println("la Commande est "+Id_Commande+" est Date = "+Date_Commande+" Age = "+
//                        res.getString("age"));
               CommandeP c=new CommandeP(Id_Commande,Nom,Prenom, Date_Commande, Prix_Total, Id_Membre);
               arr.add(c);
     }
    return arr;
    }
    
    @Override
    public List<CommandeP> RechercheCommandeParIdMembre(int id_Membre) throws SQLException {
    List<CommandeP> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commandep where Id_Membre='"+id_Membre+"'");
     while (rs.next()) {                
               int Id_Commande=rs.getInt("Id_Commande");
               String Nom=rs.getString("Nom_Membre");
               String Prenom=rs.getString("Prenom_Membre");
               Date Date_Commande=rs.getDate("Date_Commande");
               Double Prix_Total=rs.getDouble("Prix_Total");
               int Id_Membre=rs.getInt("Id_Membre");
//               System.out.println("la Commande est "+Id_Commande+" est Date = "+Date_Commande+" Age = "+
//                        res.getString("age"));
               CommandeP c=new CommandeP(Id_Commande,Nom,Prenom, Date_Commande, Prix_Total, Id_Membre);
               arr.add(c);
     }
    return arr;
    }
    
    @Override
    public List<CommandeP> RechercheCommandeParNomMembre(String Nom_Membre) throws SQLException {
    List<CommandeP> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select c.*,m.Nom_Membre,m.Prenom_Membre from commandep c ,membre m where c.Id_Membre= m.Id_Membre and m.Nom_Membre LIKE '"+Nom_Membre+"%'");
     while (rs.next()) {                
               int Id_Commande=rs.getInt("Id_Commande");
               String Nom=rs.getString("Nom_Membre");
               String Prenom=rs.getString("Prenom_Membre"); 
               Date Date_Commande=rs.getDate("Date_Commande");
               Double Prix_Total=rs.getDouble("Prix_Total");
               int Id_Membre=rs.getInt("Id_Membre");
//               System.out.println("la Commande est "+Id_Commande+" est Date = "+Date_Commande+" Age = "+
//                        res.getString("age"));
               CommandeP c=new CommandeP(Id_Commande,Nom,Prenom, Date_Commande, Prix_Total, Id_Membre);
               arr.add(c);
     }
    return arr;
    }
    
//    
//    @Override
//    public void FacturePdf(int id_membre) throws SQLException,FileNotFoundException,DocumentException,IOException 
//    {
//        Document doc = new Document();
//        
//       
//        ste=con.createStatement();
//        ResultSet rs=ste.executeQuery("select c.Id_Commande,m.Nom_Membre,m.Prenom_Membre,c.Prix_Total,c.Date_Commande from commande c , membre m where c.Id_Membre= m.Id_Membre and m.Id_Membre='"+id_membre+"'");
//        PdfWriter.getInstance(doc, new FileOutputStream("G:\\Project\\Velo.tn\\Facture.pdf"));
//        
//        doc.open();
//        doc.add(new Paragraph("   "));
//        doc.add(new Paragraph("  Facture  "));
//        doc.add(new Paragraph("   "));
//        
//        PdfPTable table = new PdfPTable(5);
//        table.setWidthPercentage(100);
//        PdfPCell cell;
//        
//        cell = new PdfPCell(new Phrase("id Commande",FontFactory.getFont("Comic Sans MS",12)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBackgroundColor(BaseColor.GRAY);
//        table.addCell(cell);
//        
//        cell = new PdfPCell(new Phrase("Nom",FontFactory.getFont("Comic Sans MS",12)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBackgroundColor(BaseColor.GRAY);
//        table.addCell(cell);
//        
//        cell = new PdfPCell(new Phrase("Prenom",FontFactory.getFont("Comic Sans MS",12)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBackgroundColor(BaseColor.GRAY);
//        table.addCell(cell);
//        
//        
//        cell = new PdfPCell(new Phrase("Date du Commande",FontFactory.getFont("Comic Sans MS",12)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBackgroundColor(BaseColor.GRAY);
//        table.addCell(cell);
//        
//        
//        cell = new PdfPCell(new Phrase("Prix Total",FontFactory.getFont("Comic Sans MS",12)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBackgroundColor(BaseColor.GRAY);
//        table.addCell(cell);
//        
//        
//        
//        
//        
//        
//        
//     while (rs.next()) {                
//               int Id_Commande=rs.getInt("Id_Commande");
//               String Nom_Membre=rs.getString("Nom_Membre");
//               String Prenom_Membre=rs.getString("Prenom_Membre");
//               Double Prix_Total=rs.getDouble("Prix_Total");
//               Date Date_Commande=rs.getDate("Date_Commande");
//               
//                 
//               
//               //Conversion to String
//               String id_commande = Integer.toString(Id_Commande);
//               String nom_membre  = Nom_Membre.toString();
//               String prenom_membre  = Prenom_Membre.toString();
//               DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//               String date_commande = df.format(Date_Commande);
//               String prix_total=Double.toString(Prix_Total);
//               
//               
//               
//               
//               cell = new PdfPCell(new Phrase(id_commande,FontFactory.getFont("Comic Sans MS",12)));
//               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//               cell.setBackgroundColor(BaseColor.GRAY);
//               table.addCell(cell);
//               
//               cell = new PdfPCell(new Phrase(nom_membre,FontFactory.getFont("Comic Sans MS",12)));
//               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//               cell.setBackgroundColor(BaseColor.GRAY);
//               table.addCell(cell);
//               
//               cell = new PdfPCell(new Phrase(prenom_membre,FontFactory.getFont("Comic Sans MS",12)));
//               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//               cell.setBackgroundColor(BaseColor.GRAY);
//               table.addCell(cell);
//        
//        
//               cell = new PdfPCell(new Phrase(date_commande,FontFactory.getFont("Comic Sans MS",12)));
//               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//               cell.setBackgroundColor(BaseColor.GRAY);
//               table.addCell(cell);
//        
//        
//               cell = new PdfPCell(new Phrase(prix_total,FontFactory.getFont("Comic Sans MS",12)));
//               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//               cell.setBackgroundColor(BaseColor.GRAY);
//               table.addCell(cell);
//        
//        
//               
//              
//        
//                        }
//            doc.add(table);
//            doc.close();
//            Desktop.getDesktop().open(new File ("G:\\Project\\Velo.tn\\Facture.pdf"));
//            }
    
    @Override
         public double CalculerTotalCommande(List<ListeProduit> L){
                    double total=0;
                    for(ListeProduit e: L)
                      {
                    total=total+(e.getPrix())*(e.getQuantite());          
                         }
                    
                    return total;
                }
        
    }
    
    
    

