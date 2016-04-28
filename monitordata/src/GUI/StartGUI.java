/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author stelios
 */
public class StartGUI {
        Connection Conn;
        public AndroidGUI Android;
        
        public Graphics Mobile; 
        static JFrame DeviceFrame;
        JPanel DevicePanel;
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
        JLabel noDevices;
        private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
public StartGUI(Connection Conn)
{
    this.Conn = Conn;
    
            UIManager.put("control", new Color(214,217,223));
        try{
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
                }
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){}
        JFrame.setDefaultLookAndFeelDecorated(true);  
        
        DeviceFrame = new JFrame("System Devices");
        DeviceFrame.setLocationRelativeTo(null);
        DeviceFrame.setSize(450,230);
        DevicePanel = new JPanel();
        DevicePanel.setLayout(new GridBagLayout());
        DevicePanel.setBorder(BorderFactory.createEtchedBorder());
        noDevices = new JLabel("No Devices Found! Please wait for a device to connect.");
        noDevices.setFont(new Font("Arial", Font.BOLD, 12));
        DevicePanel.add(noDevices, new GridBagConstraints(1, 1, 1, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
        DeviceFrame.setContentPane(DevicePanel);
        DeviceFrame.setVisible(true);
        
    
}


public void SetAndroidGUI()
{
    Android = new AndroidGUI(Conn);
}

public void SetMobileGUI()
{
    Mobile = new Graphics(Conn);
}


}


