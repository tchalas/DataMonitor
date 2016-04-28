
package GUI;

import WebService.monitorDataWS;
import WebService.DeviceInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

public final class AndroidGUI {
    int position,wirelessPosition;
    String devName;
    PreparedStatement stmtSelect;
    ResultSet rs;
    ResultSetMetaData rsmd;
    BoxLayout dataLayout;
    JButton back;
    boolean datawired=false;
    boolean APUP=false;
    
    Vector<String> row;
    Vector<Vector> data;

    Vector<String> columns; 
    
  //  final JFrame DeviceFrame;
    final JFrame addAndroidDevFrame;
    final JFrame AndroidData;

    

    
    JPanel newAndroidDev;
   // JPanel DevicePanel;
   
    JPanel AndroidDataPanel;
  
    JPanel DataTypePanelContainer;
    JPanel BackDataType;
    
    JButton devButton;

    JButton DataTypeBackButton;
    JButton back1, back2;
    
    JLabel noInterfaces;
   
    JLabel noDevices;
    
    FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
    int i, curpos;
    int kk=0;
    GridLayout grLayout = new GridLayout(2,2);
    Connection conn;
    private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
     
    public AndroidGUI(Connection conn){
        this.conn = conn;
        position = 0;
        
        Dimension dd;
        dd=new Dimension(80,100);
        UIManager.put("control", new Color(214,217,223));
        try{
            for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
                }
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){}
        JFrame.setDefaultLookAndFeelDecorated(true);     
   //     DeviceFrame = new JFrame("System Devices");
        addAndroidDevFrame = new JFrame("Devices:");
        addAndroidDevFrame.setSize(450,230);
        addAndroidDevFrame.setLocationRelativeTo(null);
   /*     DeviceFrame.setLocationRelativeTo(null);
        DeviceFrame.setSize(450,230);
        DevicePanel = new JPanel();
        DevicePanel.setLayout(new GridBagLayout());
        DevicePanel.setBorder(BorderFactory.createEtchedBorder());
        noDevices = new JLabel("No Android Devices Found! Please wait for a device to connect.");
        noDevices.setFont(new Font("Arial", Font.BOLD, 12));
        DevicePanel.add(noDevices, new GridBagConstraints(1, 1, 1, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
        DeviceFrame.setContentPane(DevicePanel);
        DeviceFrame.setVisible(true);*/
        newAndroidDev = new JPanel();
        JLabel devLabel = new JLabel("Choose the device");
        devLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newAndroidDev.setLayout(grLayout);
        AndroidData = new JFrame("Choose Data Type");
        
        AndroidData.setSize(460, 280);
        AndroidData.setLocationRelativeTo(null);
        AndroidDataPanel = new JPanel();
        DataTypePanelContainer = new JPanel();
        DataTypePanelContainer.setLayout(new BoxLayout(DataTypePanelContainer, BoxLayout.Y_AXIS));
        BackDataType= new JPanel();
        BackDataType.setLayout(fl);
        
        AndroidData.setContentPane(DataTypePanelContainer);
        
                AndroidDataPanel.setOpaque(true);
        dataLayout = new BoxLayout(AndroidDataPanel, BoxLayout.Y_AXIS);
        AndroidDataPanel.setLayout(dataLayout);
        
        //AndroidDataPanel.setLayout(fl);
        
        DataTypeBackButton =new JButton("Back");

        BackDataType.add(DataTypeBackButton);
        
        DataTypePanelContainer.add(AndroidDataPanel);
        DataTypePanelContainer.add(BackDataType);
   
        

     /*   DataTypeBackButton.addActionListener(new ActionListener(){           
        @Override
            public void actionPerformed(ActionEvent e){
                AndroidData.setVisible(false);
                addAndroidDevFrame.setVisible(true);
            }
        }
        );*/     
    }
    
    public void addDevice(final String name, final int i){
        devName = name;
        devButton = new JButton(devName);
        newAndroidDev.add(devButton);
        StartGUI.DeviceFrame.setVisible(false);
        addAndroidDevFrame.setVisible(true);
        addAndroidDevFrame.setContentPane(newAndroidDev);
        newAndroidDev.setVisible(true);
        devButton.addActionListener(new ActionListener(){    
        @Override
            public void actionPerformed(ActionEvent e){
            curpos=i;
            setData(curpos , name);
            //        addAndroidDevFrame.setVisible(false);
          //      AndroidData.setVisible(true);
            }
        }
        );
    }
    
    public void deleteDevice(int j){
       addAndroidDevFrame.getContentPane().remove(j);
       if (monitorDataWS.buffer.isEmpty()){
           addAndroidDevFrame.setVisible(false);
           StartGUI.DeviceFrame.setVisible(true);
       }
    }
    
    public void setData(final int pos , String name){
  
                    JTable t1;   
                    
                    try{
                        AndroidDataPanel.removeAll();
                       String select = "SELECT * FROM MobileData WHERE AndroidID = ? ";
                        stmtSelect = conn.prepareStatement(select);
                        

                        stmtSelect.setString(1, name);
                        rs = stmtSelect.executeQuery();
                        rsmd = rs.getMetaData();
                        int numOfColumns = rsmd.getColumnCount();
                        data = new Vector<Vector>();
                        columns = new Vector<String>();
                        columns.addElement("column1");
                        columns.addElement("column2");
                        t1 = new JTable(data,columns);

                        while(rs.next()){
                            for(int i = 0; i < numOfColumns; i++){
                                row = new Vector<String>();
                                row.insertElementAt(rsmd.getColumnName(i+1), 0);
                                row.insertElementAt(rs.getString(i+1), 1);
                                data.addElement(row);
                            }
                        }
                        AndroidDataPanel.add(t1);
//                        AndroidDataPanel.add(back1);
                        DataTypeBackButton.addActionListener(new ActionListener(){    
                        @Override
                        public void actionPerformed(ActionEvent e){
                            
                            AndroidData.setVisible(false);
                            addAndroidDevFrame.setVisible(true);
                            row.clear();
                            data.clear();
                            AndroidDataPanel.removeAll();
                        }
                    }                
                    );
                    addAndroidDevFrame.setVisible(false);
                    AndroidData.setVisible(true);
                    }catch(SQLException ex){}
                }
  
    }  
    
