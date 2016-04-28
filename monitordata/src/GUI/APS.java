/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import MD.AccessPoint;
import WebService.DeviceInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author stelios
 */
public class APS extends JFrame{
    FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
    static JPanel APPanel=new JPanel();
    JPanel APContainer=new JPanel();
    JPanel APBackPanel=new JPanel();
    Vector<String> ListContent=new Vector<String>();// = { "Belkin", "Thomson", "Cyta", "OTE", "HOL"};
    JList list;
    JScrollPane pane;
    JTable table; 
    JButton APBack=new JButton("Back");
    DefaultListSelectionModel mod;
    private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
       Vector<String> rowOne;   
       Vector<String> rowTwo; 
       Vector<String> rowThree; 
       Vector<String> rowFour; 
       Vector<Vector> rowData;
       Vector<String> columnNames;
    
    public APS(final JFrame Type){
        super("List Example");  
        this.setLocationRelativeTo(null);
        
        this.setSize(600,300);
        
        
        APPanel.setBorder(BorderFactory.createEtchedBorder());
        APPanel.setLayout(new GridBagLayout());
        JLabel APSlabel=new JLabel("Avaliable AP's");
        JLabel APData=new JLabel("Access Point Data");
        APPanel.add(APSlabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
        GridBagConstraints.CENTER, GridBagConstraints.NONE,
        EMPTY_INSETS, 0, 0));
        
        
        APPanel.add(APData, new GridBagConstraints(2, 0, 1, 1, 0, 0,
        GridBagConstraints.CENTER, GridBagConstraints.NONE,
        EMPTY_INSETS, 0, 0));
        
        APBackPanel.add(APBack);
        APBackPanel.setLayout(fl);
        APContainer.setLayout(new BoxLayout(APContainer, BoxLayout.Y_AXIS));
        mod= new DefaultListSelectionModel();
        mod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mod.setLeadAnchorNotificationEnabled(false);
        
       rowOne = new Vector<String>();   
       rowTwo = new Vector<String>(); 
       rowThree = new Vector<String>(); 
       rowFour = new Vector<String>(); 
       rowData = new Vector<Vector>();
       columnNames = new Vector<String>();
        
       
        columnNames.addElement("Column");
        columnNames.addElement("Column");

       rowOne.addElement("MAC");
       rowOne.addElement("");
       rowTwo.addElement("Status");
       rowTwo.addElement("");
       rowThree.addElement("Channel");
       rowThree.addElement("");
       rowFour.addElement("Signal Strenght");
       rowFour.addElement("");         
             
             
     rowData.addElement(rowOne);  
     rowData.addElement(rowTwo);
     rowData.addElement(rowThree);
     rowData.addElement(rowFour);
       
     table = new JTable(rowData, columnNames);  
     
     APContainer.add(APPanel);
     APContainer.add(APBackPanel);
     this.setContentPane(APContainer);
     
     
     APBack.addActionListener(   
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                          setVisible(false);
                         Type.setVisible(true);
                    }
                    }
                     );  ///end of action listener for back to wireless
      }
    
    
    public void setAP(final Vector<AccessPoint> APV)
    {
        int i;
        for(i=0;i<APV.size();i++)
        {
            ListContent.add(i, APV.elementAt(i).getESSID());            
        }
        
    list = new JList();
    list.setListData(ListContent);
    list.setSelectionModel(mod);
    list.setSelectedIndex(0);
    pane = new JScrollPane(list);
    
    APPanel.add(pane, new GridBagConstraints(0, 1, 1, 3, .30, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,EMPTY_INSETS, 0, 0));   
    APPanel.add(table, new GridBagConstraints(2, 1, 1, 5, .5, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));
 
    
    
      list.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent le) {
        int idx = list.getSelectedIndex();
        if (idx != -1){
            
            System.out.println("Current selection: " + ListContent.elementAt(idx));
            

         table.getModel().setValueAt(APV.elementAt(idx).getMacAddress(), 0, 1);
         table.getModel().setValueAt(APV.elementAt(idx).getStatus(), 1, 1);
         table.getModel().setValueAt(Integer.toString(APV.elementAt(idx).getChannel()), 2, 1);
         table.getModel().setValueAt(Integer.toString(APV.elementAt(idx).getSignalStrength()), 3, 1);           
         
        }        
        else{
          System.out.println("Please choose AP.");
        }
     }
    });
    
    
        
    }

    
    
}
