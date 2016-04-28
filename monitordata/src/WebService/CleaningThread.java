package WebService;

import GUI.Graphics;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class  CleaningThread implements Runnable {
    private Connection conn;
    private int T;
    private long currTime;
    
    CleaningThread(Connection conn){
        this.conn = conn;
        try{                
            Properties prop = new Properties();
            prop.load(new FileInputStream("mySQL.properties"));
            T = Integer.parseInt(prop.getProperty("timeT"));
        }catch(IOException e){}
    }
    
    @Override
    public void run(){
        PreparedStatement stmtDelete = null;
        while(!Thread.currentThread().isInterrupted()){
            for(int i = 0; i < monitorDataWS.buffer.size(); i++){
                if(((currTime = System.currentTimeMillis()) - (monitorDataWS.buffer.elementAt(i).getTimeAdded())) > T ){       
                    try{
                        String devName = monitorDataWS.buffer.elementAt(i).getDeviceName();     //ean o xronos apo tin teleutaia enimerwsi
                        
                        String cleanup = "DELETE FROM list_A WHERE deviceName = ?";             //tis trexon siskeuis einai megaliteros apo T
                        stmtDelete = conn.prepareStatement(cleanup);                            //tote tha afairethoun ola ta interfaces autis tis siskeuis                                
                        stmtDelete.setString(1, devName);                                       //apo ti vasi kai tin endiamesi mnimi.
                        stmtDelete.executeUpdate();

                        cleanup = "DELETE FROM list_B WHERE deviceName = ?";                          
                        stmtDelete = conn.prepareStatement(cleanup);                                                                  
                        stmtDelete.setString(1, devName);
                        stmtDelete.executeUpdate();

                        cleanup = "DELETE FROM list_C WHERE deviceName = ?";                                     
                        stmtDelete = conn.prepareStatement(cleanup);                                                                  
                        stmtDelete.setString(1, devName);                     
                        stmtDelete.executeUpdate();

                        cleanup = "DELETE FROM AP_RSS WHERE deviceName = ?";
                        stmtDelete = conn.prepareStatement(cleanup);                                                                  
                        stmtDelete.setString(1, devName);                     
                        stmtDelete.executeUpdate();

                        System.out.println("Records for device: " + devName + " deleted succesfully!");
                        monitorDataWS.buffer.remove(i);         //afaireitai episis kai apo tin endiamesi mnimi.
                        monitorDataMF.grafiko.deleteDevice(i);
                        
                    }catch(SQLException e){e.printStackTrace();}                                                                                                                                       
                    finally{
                        try{
                            System.out.println("Cleanup Complete");
                            stmtDelete.close();
                        }catch(SQLException e){e.printStackTrace();}
                    }
                }                                                                                                               
            }
            try{
                Thread.sleep(T);
            }catch(InterruptedException e){System.out.println("Cleanup Thread is stopped");}
        }
    }
}
