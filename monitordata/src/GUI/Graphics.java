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

public final class Graphics {
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
    Vector<String> wirelessrow;
    Vector<Vector> wirelessdata;
    Vector<String> columns; 
    
 //   final JFrame deviceFrame;
    final JFrame addDevFrame;
    final JFrame interfaceType;
    final JFrame wired;
    final JFrame wireless;
    final JFrame AccessPoint;
    final JFrame wiredDataFrame;
    final JFrame wirelessDataFrame;

    
    JPanel newDev;
 //   JPanel devicePanel;
    JPanel wiredDataPanel;
    JPanel wirelessDataPanel;
    JPanel interfaces;
    JPanel wiredPanel;
    JPanel wirelessPanel;
    JPanel accessPointPanel;
    JPanel wiredContainer;
    JPanel wirelessContainer;
    JPanel WiredBackPanel;
    JPanel WirelessBackPanel;
    JPanel InterFacesContainer;
    JPanel InterFacesBack;
    
    JButton devButton;
    JButton wiredButton;
    JButton wirelessButton;
    JButton Wired;
    JButton Wireless;
    JButton APs;
    JButton wiredBack;
    JButton wirelessBack;
    JButton interfacesBackButton;
    JButton back1, back2;
    
    DefaultTableModel wiredTable;
    
    JLabel noInterfaces;
    JLabel noWirelessInterfaces;
    JLabel noDevices;
    
    FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
    int i, curpos;
    int kk=0;
    GridLayout grLayout = new GridLayout(2,2);
    Connection conn;
    private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
     
    public Graphics(Connection conn){
        this.conn = conn;
        position = 0;
        wirelessPosition = 0;
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
   //     deviceFrame = new JFrame("System Devices");
        addDevFrame = new JFrame("Devices:");
        addDevFrame.setSize(450,230);
        addDevFrame.setLocationRelativeTo(null);
    /*    deviceFrame.setLocationRelativeTo(null);
        deviceFrame.setSize(450,230);
        devicePanel = new JPanel();
        devicePanel.setLayout(new GridBagLayout());
        devicePanel.setBorder(BorderFactory.createEtchedBorder());*/
        noDevices = new JLabel("No Devices Found! Please wait for a device to connect.");
        noDevices.setFont(new Font("Arial", Font.BOLD, 12));
     /*   devicePanel.add(noDevices, new GridBagConstraints(1, 1, 1, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
        deviceFrame.setContentPane(devicePanel);
        deviceFrame.setVisible(true);*/
        newDev = new JPanel();
        JLabel devLabel = new JLabel("Choose the device to show the interfaces");
        devLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newDev.setLayout(grLayout);
        interfaceType = new JFrame("Choose Interfaces");
        
        interfaceType.setSize(360, 180);
        interfaceType.setLocationRelativeTo(null);
        interfaces = new JPanel();
        InterFacesContainer = new JPanel();
        InterFacesContainer.setLayout(new BoxLayout(InterFacesContainer, BoxLayout.Y_AXIS));
        InterFacesBack= new JPanel();
        InterFacesBack.setLayout(fl);
        
        interfaceType.setContentPane(InterFacesContainer);
        interfaces.setLayout(fl);
        Wired = new JButton("Wired");
        Wireless = new JButton("Wireless");
        APs = new JButton("Access Points");
        interfacesBackButton =new JButton("Back");

        
        interfaces.add(Wired);
        interfaces.add(Wireless);
        interfaces.add(APs);
        InterFacesBack.add(interfacesBackButton);
        
        InterFacesContainer.add(interfaces);
        InterFacesContainer.add(InterFacesBack);

        
        wired = new JFrame("Wired Interfaces");
        wired.setSize(360, 180);
        wired.setLocationRelativeTo(null);
        
        wireless = new JFrame("Wireless Interfaces");
        wireless.setSize(360, 180);
        wireless.setLocationRelativeTo(null);
        
        AccessPoint = new JFrame("Wired Interfaces");
        AccessPoint.setSize(360, 180);
        AccessPoint.setLocationRelativeTo(null);
        
        wiredDataFrame = new JFrame("LIST A");
        wiredDataFrame.setSize(400, 350);
        wiredDataFrame.setLocationRelativeTo(null);
        wiredDataPanel = new JPanel();
        wiredDataFrame.setContentPane(wiredDataPanel);
        wiredDataPanel.setOpaque(true);
        dataLayout = new BoxLayout(wiredDataPanel, BoxLayout.Y_AXIS);
        wiredDataPanel.setLayout(dataLayout);
        back = new JButton("Back");
        wiredBack = new JButton("Back");
        wirelessBack=new JButton("Back");
        
        wiredPanel = new JPanel();
        WiredBackPanel=new JPanel();
        WiredBackPanel.setLayout(fl);
        wiredContainer=new JPanel();
        wiredContainer.setLayout(new BoxLayout(wiredContainer, BoxLayout.Y_AXIS));
        
        wirelessPanel = new JPanel();
        WirelessBackPanel=new JPanel();
        WirelessBackPanel.setLayout(fl);
        wirelessContainer=new JPanel();
        wirelessContainer.setLayout(new BoxLayout(wirelessContainer, BoxLayout.Y_AXIS));
        
        
        wiredPanel.setLayout(new GridBagLayout());
        wiredPanel.setOpaque(true);
        WiredBackPanel.add(wiredBack);

        
        wirelessPanel.setLayout(fl);
        wirelessPanel.setOpaque(true);
        WirelessBackPanel.add(wirelessBack);
        
        noInterfaces = new JLabel("No Interfaces Found!");
        noInterfaces.setFont(new Font("Arial", Font.BOLD, 12));
        wiredPanel.add(noInterfaces, new GridBagConstraints(0, 0, 0, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
        wiredContainer.add(wiredPanel);
        wiredContainer.add(WiredBackPanel);
        wired.setContentPane(wiredContainer);
        
        wirelessContainer.add(wirelessPanel);
        wirelessContainer.add(WirelessBackPanel);
        wireless.setContentPane(wirelessContainer);
        
        final APS show=new APS(interfaceType);
        noWirelessInterfaces = new JLabel("No Wireless Interfaces Found!");
        noWirelessInterfaces.setFont(new Font("Arial", Font.BOLD, 12));
        wirelessPanel.add(noWirelessInterfaces, new GridBagConstraints(0, 0, 0, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));

        wiredDataPanel = new JPanel();
        wiredDataFrame.setContentPane(wiredDataPanel);
        wiredDataPanel.setOpaque(true);
        dataLayout = new BoxLayout(wiredDataPanel, BoxLayout.Y_AXIS);
        wiredDataPanel.setLayout(dataLayout);
        back1 = new JButton("Back");
        back2 = new JButton("Back");
        
        wirelessDataFrame = new JFrame("LIST B");
        wirelessDataFrame.setSize(500, 450);
        wirelessDataFrame.setLocationRelativeTo(null);
        wirelessDataPanel = new JPanel();
        wirelessDataFrame.setContentPane(wirelessDataPanel);
        wirelessDataPanel.setOpaque(true);
        dataLayout = new BoxLayout(wirelessDataPanel, BoxLayout.Y_AXIS);
        wirelessDataPanel.setLayout(dataLayout);
        Wired.addActionListener(new ActionListener(){           //Gia to koumpi Wired
        @Override
            public void actionPerformed(ActionEvent e){
            wiredPanel.removeAll();
            setInterface(curpos);
                interfaceType.setVisible(false);
                wired.setVisible(true);
            }
        }
        );
        
        Wireless.addActionListener(new ActionListener(){           //Gia to koumpi Wired
        @Override
            public void actionPerformed(ActionEvent e){
            wirelessPanel.removeAll();
            setWireless(curpos);
                interfaceType.setVisible(false);
                wireless.setVisible(true);
            }
        }
        );
        
        APs.addActionListener(new ActionListener(){           //Gia to koumpi Wired
        @Override
            public void actionPerformed(ActionEvent e){
                interfaceType.setVisible(false);
                if(APUP==false){
                    show.setVisible(true);
                    show.setAP(DeviceInfo.apBufferVector);
                    APUP=true;
                }
                else{
                    show.setVisible(true);
                }
            }
        }
        );
        
        wiredBack.addActionListener(new ActionListener(){           //Gia to koumpi Wired
        @Override
            public void actionPerformed(ActionEvent e){
                wired.setVisible(false);
                interfaceType.setVisible(true);
            }
        }
        );
        
        wirelessBack.addActionListener(new ActionListener(){           //Gia to koumpi Wirelesss
        @Override
            public void actionPerformed(ActionEvent e){
                wireless.setVisible(false);
                interfaceType.setVisible(true);
            }
        }
        );        

        interfacesBackButton.addActionListener(new ActionListener(){           
        @Override
            public void actionPerformed(ActionEvent e){
                interfaceType.setVisible(false);
                addDevFrame.setVisible(true);
            }
        }
        );     
    }
    
    public void addDevice(String name, final int i){
        devName = name;
        devButton = new JButton(devName);
        newDev.add(devButton);
        StartGUI.DeviceFrame.setVisible(false);
        addDevFrame.setVisible(true);
        addDevFrame.setContentPane(newDev);
        newDev.setVisible(true);
        devButton.addActionListener(new ActionListener(){    
        @Override
            public void actionPerformed(ActionEvent e){
            curpos=i;
                addDevFrame.setVisible(false);
                interfaceType.setVisible(true);
            }
        }
        );
    }
    
    public void deleteDevice(int j){
       addDevFrame.getContentPane().remove(j);
       if (monitorDataWS.buffer.isEmpty()){
           addDevFrame.setVisible(false);
           StartGUI.DeviceFrame.setVisible(true);
       }
    }
    
    public void setInterface(final int pos){
        if(monitorDataWS.buffer.elementAt(pos).InterfaceList.isEmpty()){
            wiredPanel.remove(noInterfaces);
        }
        for(kk=0; kk < monitorDataWS.buffer.elementAt(pos).InterfaceList.size() ;kk++){
            final String  name1 = monitorDataWS.buffer.elementAt(pos).InterfaceList.get(kk);
            wiredButton = new JButton(name1);
            wiredPanel.add(wiredButton, new GridBagConstraints(0, position, 0, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
            position++;
            wiredButton.addActionListener(new ActionListener(){    
            @Override
                public void actionPerformed(ActionEvent e){
                    JTable t1;   
                    
                    try{
                        wiredDataPanel.removeAll();
                       String select = "SELECT * FROM list_A WHERE NAME = ? AND deviceName = ?";
                        stmtSelect = conn.prepareStatement(select);
                        stmtSelect.setString(1, name1);
                        stmtSelect.setString(2, monitorDataWS.buffer.elementAt(pos).getDeviceName());
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
                        wiredDataPanel.add(t1);
                        wiredDataPanel.add(back1);
                        back1.addActionListener(new ActionListener(){    
                        @Override
                        public void actionPerformed(ActionEvent e){
                            
                            wiredDataFrame.setVisible(false);
                            wired.setVisible(true);
                            row.clear();
                            data.clear();
                            wiredDataPanel.removeAll();
                        }
                    }                
                    );
                    wired.setVisible(false);
                    wiredDataFrame.setVisible(true);
                    }catch(SQLException ex){}
                }
            }
            );
        }
}
    
    public void setWireless(final int pos){
        
        if(monitorDataWS.buffer.elementAt(pos).WirelessList.isEmpty()){
            wirelessPanel.remove(noInterfaces);
        }
        
       for(kk=0; kk < monitorDataWS.buffer.elementAt(pos).WirelessList.size() ;kk++){
         final String  wirelessName = monitorDataWS.buffer.elementAt(pos).WirelessList.get(kk);
        wirelessButton = new JButton(wirelessName);
        wirelessPanel.add(wirelessButton, new GridBagConstraints(0, wirelessPosition, 0, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
        wirelessPosition++;
        wirelessButton.addActionListener(new ActionListener(){    
        @Override
        public void actionPerformed(ActionEvent e){
            JTable t2;
            JTable t3;
            try{
                wirelessDataPanel.removeAll();
                String select = "SELECT * FROM list_A WHERE NAME = ? ";
                stmtSelect = conn.prepareStatement(select);
                stmtSelect.setString(1, wirelessName);
                rs = stmtSelect.executeQuery();
                rsmd = rs.getMetaData();
                int numOfColumns = rsmd.getColumnCount();
                data = new Vector<Vector>();
                columns = new Vector<String>();
                columns.addElement("column1");
                columns.addElement("column2");
                t2 = new JTable(data,columns);

                while(rs.next()){
                    for(int i = 0; i < numOfColumns; i++){
                        row = new Vector<String>();
                        row.insertElementAt(rsmd.getColumnName(i+1), 0);
                        row.insertElementAt(rs.getString(i+1), 1);
                        data.addElement(row);
                    }
                }
                select = "SELECT * FROM list_B WHERE interfaceName = ?";
                stmtSelect = conn.prepareStatement(select);
                stmtSelect.setString(1, wirelessName);
                rs = stmtSelect.executeQuery();
                rsmd = rs.getMetaData();
                numOfColumns = rsmd.getColumnCount();
                wirelessdata = new Vector<Vector>();
                columns = new Vector<String>();
                columns.addElement("column1");
                columns.addElement("column2");
                t3 = new JTable(data,columns);

                while(rs.next()){
                    for(int i = 0; i < numOfColumns; i++){
                        wirelessrow = new Vector<String>();
                        wirelessrow.insertElementAt(rsmd.getColumnName(i+1), 0);
                        wirelessrow.insertElementAt(rs.getString(i+1), 1);
                        wirelessdata.addElement(wirelessrow);
                    }
                }
                wirelessDataPanel.add(t2);
                wirelessDataPanel.add(t3);
                wirelessDataPanel.add(back2);
                back2.addActionListener(new ActionListener(){    
                @Override
                public void actionPerformed(ActionEvent e){
                    wirelessDataFrame.setVisible(false);
                    wireless.setVisible(true);
                    row.clear();
                    data.clear();
                    wirelessrow.clear();
                    wirelessdata.clear();
                    wirelessDataPanel.removeAll();
                }
                }                
                );
            wireless.setVisible(false);
            wirelessDataFrame.setVisible(true);
            }catch(SQLException ex){}
        }
        }
        );
    }  
    }
}