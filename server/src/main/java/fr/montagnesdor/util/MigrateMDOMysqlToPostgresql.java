/*
 * Créé le 6 avr. 2005
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package fr.montagnesdor.util;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * @author hans
 *
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class MigrateMDOMysqlToPostgresql
{
	private static ResourceBundle resource = ResourceBundle.getBundle(MigrateMDOMysqlToPostgresql.class.getName());
	
	public static void main(String[] args)
    {
	    System.out.println("START");

	    FileOutputStream fos = null;
	    PrintWriter pw = null;
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try
        {
	        fos = new FileOutputStream(resource.getString("sql.file"));
	        pw = new PrintWriter(fos);
	        String insertedLine = "";
	        System.out.println(resource.getString("jdbc.driver"));
            Class.forName(resource.getString("jdbc.driver")).newInstance();
            String url = resource.getString("url");
            System.out.println(url+" "+resource.getString("user.name")+" "+resource.getString("user.password"));
            conn = DriverManager.getConnection(url, resource.getString("user.name"), resource.getString("user.password"));

            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("SELECT codeProduit, designation, prix FROM produits");
            while ( rs.next() ) 
            {
                insertedLine = "INSERT INTO t_product (pdt_id, pdt_price) VALUES ('"+rs.getString("codeProduit")+"', "+ rs.getFloat("prix")+");";
                System.out.println(insertedLine);
                pw.println(insertedLine);
                
                StringBuffer labelBuffer = new StringBuffer(rs.getString("designation"));
                StringTokenizer labelStk = new StringTokenizer(labelBuffer.toString(), " ");
                labelBuffer = new StringBuffer("");
                while(labelStk.hasMoreTokens())
                {
                    labelBuffer.append(labelStk.nextToken()).append(" ");
                }
                String label = labelBuffer.toString().trim();
                label = label.replaceAll("'", "''");
                insertedLine = "INSERT INTO t_product_language (pdt_id, pdl_label) VALUES ('"+rs.getString("codeProduit")+"', '"+ label +"');";
                System.out.println(insertedLine);
                pw.println(insertedLine);
            }
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");

            rs = stmt.executeQuery("SELECT idType, libelle FROM typeproduit ORDER BY idType");
            HashMap typeproduitToCategory = new HashMap(30);
            int i = 1;
            while ( rs.next() ) 
            {
                typeproduitToCategory.put(rs.getString("idType"), i+"");
                insertedLine = "INSERT INTO t_category (cat_id) VALUES ("+i+");";
                System.out.println(insertedLine);
                pw.println(insertedLine);
                insertedLine = "INSERT INTO t_category_language (cat_id, ctl_label) VALUES ("+i+", '"+ rs.getString("libelle")+"');";
                System.out.println(insertedLine);
                pw.println(insertedLine);
                i++;
            }
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");

            rs = stmt.executeQuery("SELECT codeProduit, idTypeProduit, quantite FROM produitpartype");
            i = 1;
            while ( rs.next() ) 
            {
                insertedLine = "INSERT INTO t_category_relation (ctr_id, pdt_id, cat_id, ctr_quantity) VALUES ("+i+", '"+rs.getString("codeProduit")+"', "+(String)typeproduitToCategory.get(rs.getString("idTypeProduit"))+", "+rs.getFloat("quantite")+");";
                System.out.println(insertedLine);
                pw.println(insertedLine);
                i++;
            }
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");
            
            rs = stmt.executeQuery("SELECT dayofmonth(dateStats) AS day, month(dateStats)-1 AS month, year(dateStats) AS year, codeProduit, quantite FROM statsconsoproduits WHERE quantite<>0 ORDER BY idStats");
            i = 1;
            while ( rs.next() ) 
            {
                insertedLine = "INSERT INTO t_stats_consumption_product (scp_id, scp_updated_day, scp_updated_month, scp_updated_year, pdt_id, scp_quantity) VALUES ("+i+", "+ rs.getInt("day")+", "+ rs.getInt("month")+", "+rs.getInt("year")+", '"+rs.getString("codeProduit")+"', " +rs.getFloat("quantite")+");";
                System.out.println(insertedLine);
                pw.println(insertedLine);
                i++;
            }
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");
            System.out.println("");
            pw.println("");
            
            int j=1;
            i = 1;
            rs = stmt.executeQuery("SELECT dateRecette, dateImpression, dateCloture, espece, ticket, cheque, carte, impaye, montant, typetable FROM recettesjournalieres ORDER BY dateRecette");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateRecette = null;
            Date dateImpression = null;
            Date dateCloture = null;
            String dateRecetteStr = null;
            String dateImpressionStr = null;
            String dateClotureStr = null;
            boolean isTakeaway = false;
            while ( rs.next() ) 
            {
                dateRecette = rs.getDate("dateRecette");
                try
                {
                    dateRecetteStr = "TO_DATE('"+sdf.format(dateRecette)+"', 'dd/MM/yyyy')";
                }
                catch(Exception e)
                {
                    dateRecetteStr = null;
                }
                dateImpression = rs.getDate("dateImpression");
                try
                {
                    dateImpressionStr = "TO_DATE('"+sdf.format(dateImpression)+"', 'dd/MM/yyyy')";
                }
                catch(Exception e)
                {
                    dateImpressionStr = null;
                }
                dateCloture = rs.getDate("dateCloture");
                try
                {
                    dateClotureStr = "TO_DATE('"+sdf.format(dateCloture)+"', 'dd/MM/yyyy')";
                }
                catch(Exception e)
                {
                    dateClotureStr = null;
                }
                if(rs.getInt("typetable")==1)
                    isTakeaway = true;
                else
                    isTakeaway = false;
                insertedLine = "INSERT INTO t_day_revenue (drv_id, drv_revenue_date, drv_print_date, drv_closing_date, drv_cash, drv_ticket, drv_cheque, drv_card, drv_unpaid, drv_amount, drv_takeaway) VALUES ("+i+", "+ dateRecetteStr +", "+ dateImpressionStr +", "+ dateClotureStr +", "+ rs.getFloat("espece") +", " +rs.getFloat("ticket") +", " +rs.getFloat("cheque") +", " +rs.getFloat("carte") +", " +rs.getFloat("impaye") +", " +rs.getFloat("montant")+", "+isTakeaway+");";
                System.out.println(insertedLine);
                pw.println(insertedLine);
                float amount = rs.getFloat("montant");
                float value = 0;
                if(isTakeaway)
                {
                    value = amount*5.5f/(100+5.5f);
                    insertedLine = "INSERT INTO t_vat_revenue (vtr_id, drv_id, vat_id, vtr_amount, vtr_value) VALUES ("+j+", "+i+", 1, "+0+", "+0+");";
                    System.out.println(insertedLine);
                    pw.println(insertedLine);
	                j++;
	                insertedLine = "INSERT INTO t_vat_revenue (vtr_id, drv_id, vat_id, vtr_amount, vtr_value) VALUES ("+j+", "+i+", 2, "+amount+", "+value+");";
	                System.out.println(insertedLine);
	                pw.println(insertedLine);
	                j++;
                }
                else
                {
                    value = amount*19.6f/(100+19.6f);
                    insertedLine = "INSERT INTO t_vat_revenue (vtr_id, drv_id, vat_id, vtr_amount, vtr_value) VALUES ("+j+", "+i+", 1, "+amount+", "+value+");";
                    System.out.println(insertedLine);
                    pw.println(insertedLine);
	                j++;
	                insertedLine = "INSERT INTO t_vat_revenue (vtr_id, drv_id, vat_id, vtr_amount, vtr_value) VALUES ("+j+", "+i+", 2, "+0+", "+0+");";
	                System.out.println(insertedLine);
	                pw.println(insertedLine);
	                j++;
                }
                i++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{rs.close();}catch(Exception e){}
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
            try{pw.close();}catch(Exception e){}
            try{fos.close();}catch(Exception e){}
        }
        
	    System.out.println("END");
    }
}
