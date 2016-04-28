package WebService;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.xml.ws.Endpoint;
import MD.monitorData;
import GUI.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;


public class monitorDataMF {
    static Graphics grafiko;
    static Connection conn;
    static Thread t;
    
    public static void main(String[] args){
        Statement stmt = null;
        String dbname = null;
        String dbuser = null;
        String dbpass = null;
        String dbport = null;
        String dbIP = null;
        //pairnoume tis parametrous gia ti sindesi me ti vasi apo to properties file
        try{                
            Properties prop = new Properties();
            prop.load(new FileInputStream("mySQL.properties"));
            dbname = prop.getProperty("dbname");
            dbuser = prop.getProperty("dbuser");
            dbpass = prop.getProperty("dbpass");
            dbport = prop.getProperty("dbport");
            dbIP = prop.getProperty("dbIP");

            System.out.println("Connecting...");
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrl = "jdbc:mysql://" + dbIP + ":" + dbport + "/" + dbname;
            conn = DriverManager.getConnection(dbUrl, dbuser, dbpass);
            System.out.println("Connection Established!");
            
            String deleteListA = "DELETE FROM list_A;";     //Katharismos twn eggrafwn otan ginetai 
            stmt = conn.createStatement();                  //i sindesi ti prwti fora sti vasi
            stmt.executeUpdate(deleteListA);                //gia na min siniparxoun palies kai nees eggrafes 
            String deleteListB = "DELETE FROM list_B;";
            stmt = conn.createStatement();
            stmt.executeUpdate(deleteListB);
            String deleteListC = "DELETE FROM list_C;";
            stmt = conn.createStatement();
            stmt.executeUpdate(deleteListC);
            String deleteAP_RSS = "DELETE FROM AP_RSS;";
            stmt = conn.createStatement();
            stmt.executeUpdate(deleteAP_RSS);
            
            Object sync = new Object();
            
            Properties wsProp = new Properties();
            wsProp.load(new FileInputStream("wservice.properties"));
            String wsIP = wsProp.getProperty("IP");
            String wsPORT = wsProp.getProperty("PORT");
            String projectName = wsProp.getProperty("projectName");
            String wsName = wsProp.getProperty("wsName");
            String WSurl = "http://" + wsIP + ":" + wsPORT + "/" + projectName + "/" + wsName;
            System.out.println(WSurl);
            Endpoint.publish(WSurl, new monitorDataWS(conn,sync));
            
            CleaningThread cleanup = new CleaningThread(conn);
            t = new Thread(cleanup);
            t.start();
            
            grafiko = new Graphics(conn);
            
        }catch(ClassNotFoundException | SQLException | IOException e){e.printStackTrace();}
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override public void run(){
                try{
                    if(conn!= null){
                        conn.close();
                    }
                }catch(SQLException e){}
                t.interrupt();
                System.gc();
                System.out.println("To programma termatistike epitixws");
            }
        });
    }
}
