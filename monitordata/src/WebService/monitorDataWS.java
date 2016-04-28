package WebService;

import MD.monitorData;
import GUI.Graphics;
import TerminalData.TerminalData;
import java.sql.*;
import java.util.Vector;
import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class monitorDataWS {
    private Connection conn;
    private final Object sync;
    public static Vector<DeviceInfo> buffer;
    private boolean flag;
    private int position;
    private boolean exists;
    private boolean AP_exists;

    monitorDataWS(Connection conn, Object sync){
        this.conn = conn;
        this.sync = sync;
        buffer = new Vector<DeviceInfo>();
    }
    
    @WebMethod
    public void setMonitorData(String name, monitorData data){
        PreparedStatement stmtInsert;
        PreparedStatement stmtUpdate;
        PreparedStatement stmtDelete;
        
        synchronized(sync){
            flag = false;
            //elegxoume ean i siskeui vrisketai sto buffer(endiamesi mnimi)
            for(int i = 0; i < buffer.size(); i++){                
                if(buffer.elementAt(i).getDeviceName().equals(name)){       //i siskeui mazi me ta interfaces(oxi ola?) tis iparxei idi sti vasi afou iparxei kai sto buffer
                    flag = true;
                    position = i;
                }
            }
            if(flag == true){
                ////////////////////////////////////////////////////DELETES FROM DB AND BUFFER if the interface was not sent////////////////////////////////////////////
               //for(int x = 0; x < buffer.elementAt(position).InterfaceList.size(); x++){       //elegxoume ta interfaces tou buffer me auta pou lavame(gia tin trexon device), gia na vroume ean exei pesei kapoio
                   /* exists = false;                                                             //interface kai den exei stalei apo tin monitordata, gia na to afairesoume apo ti vasi    
                    //Wired interfaces removal
                    for(int y = 0; y < data.wiredVector.size(); y++){
                        if (data.wiredVector.elementAt(y).getInterfaceName().equals(buffer.elementAt(position).InterfaceList.get(x))){
                            exists = true;
                            break;
                        }
                    }
                    if(exists == false){
                        try{
                            String deleteRecord = "DELETE list_A WHERE NAME = ?";               //afaireitai to interface apo ti vasi
                            stmtDelete = conn.prepareStatement(deleteRecord);
                            stmtDelete.setString(1, buffer.elementAt(position).InterfaceList.get(x));
                            stmtDelete.executeUpdate();
                            System.out.println("Deleted record with name " + buffer.elementAt(position).InterfaceList.get(x) + "from list_A");
                            buffer.elementAt(position).InterfaceList.remove(x);     //kai afaireitai kai apo to buffer
                            stmtDelete.close();
                        }catch(SQLException e){}
                    }
                     */
                    //Wireless Interfaces removal
                  /* for(int d = 0; d < buffer.elementAt(position).WirelessList.size(); d++){
                        exists = false;
                        for(int y = 0; y < data.wirelessVector.size(); y++){
                            if(data.wirelessVector.elementAt(y).getInterfaceName().equals(buffer.elementAt(position).WirelessList.get(d))){
                                exists = true;
                                break;
                            }
                        }
                        if(exists == false){
                            try{
                                String deleteRecord = "DELETE list_A WHERE NAME = ?";               //afaireitai to interface apo ti vasi
                                stmtDelete = conn.prepareStatement(deleteRecord);
                                stmtDelete.setString(1, buffer.elementAt(position).WirelessList.get(d));
                                stmtDelete.executeUpdate();
                                System.out.println("Deleted record with name " + buffer.elementAt(position).WirelessList.get(d) + " from list_A");
                            
                                String deleteRecordB = "DELETE list_B WHERE interfaceName = ?";
                                stmtDelete = conn.prepareStatement(deleteRecordB);
                                stmtDelete.setString(1, buffer.elementAt(position).WirelessList.get(d));
                                stmtDelete.executeUpdate();
                                System.out.println("Deleted record with name " + buffer.elementAt(position).WirelessList.get(d) + " from list_B");
                                buffer.elementAt(position).WirelessList.remove(d);                 //kai afaireitai kai apo to buffer
                                stmtDelete.close();
                            }catch(SQLException e){}
                        }
                    }
               
                //access points removal
               /* for(int x = 0; x < buffer.elementAt(position).AccessPointList.size(); x++){
                    exists = false;
                    for(int y = 0; y < data.apVector.size(); y++){
                        if(data.apVector.elementAt(y).getMacAddress().equals(buffer.elementAt(position).AccessPointList.get(x))){
                            exists = true;
                            break;
                        }
                    }
                    if(exists == false){
                        try{
                            String deleteRecord = "DELETE list_C WHERE AP_mac_address = ?";               //afaireitai to interface apo ti vasi
                            stmtDelete = conn.prepareStatement(deleteRecord);
                            stmtDelete.setString(1, buffer.elementAt(position).AccessPointList.get(x));
                            stmtDelete.executeUpdate();
                            buffer.elementAt(position).AccessPointList.remove(x);                 //kai afaireitai kai apo to buffer
                        
                            String deleteRecordB = "DELETE AP_RSS WHERE AP_mac_address = ? AND deviceName = ?";
                            stmtDelete = conn.prepareStatement(deleteRecordB);
                            stmtDelete.setString(1, buffer.elementAt(position).AccessPointList.get(x));
                            stmtDelete.setString(2, buffer.elementAt(position).getDeviceName());
                            stmtDelete.executeUpdate();
                            buffer.elementAt(position).RSSList.remove(x);
                            System.out.println("Deleted Access Point record with mac: " + buffer.elementAt(position).AccessPointList.get(x) + " from list_C and AP_RSS");
                            stmtDelete.close();
                        }catch(SQLException e){}
                    }
                } */
                //////////////////////////////////UPDATES//////////////////////////////////////////////
                for(int j = 0; j < data.wiredVector.size(); j++){
                    try{
                        if(buffer.elementAt(position).InterfaceList.contains(data.wiredVector.elementAt(j).getInterfaceName())){
                            String update = "UPDATE list_A SET mac_add = ?, "
                                    + "ip_add = ?, "
                                    + "default_gateway = ?, "
                                    + "net_add = ?, "
                                    + "mask = ?, "
                                    + "broad_add = ?, "
                                    + "max_transfer_rate = ?, "
                                    + "curr_transfer_rate = ?, "
                                    + "curr_used_bandwidth = ?, "
                                    + "packet_error_rate = ?,"
                                    + "deviceName = ? "
                                    + "WHERE NAME = ?";
                            stmtUpdate = conn.prepareStatement(update);
                            stmtUpdate.setString(12, data.wiredVector.elementAt(j).getInterfaceName());
                            stmtUpdate.setString(1, data.wiredVector.elementAt(j).getMacAddress());
                            stmtUpdate.setString(2, data.wiredVector.elementAt(j).getIpAddress());
                            stmtUpdate.setString(3, data.wiredVector.elementAt(j).getDefaultGateway());
                            stmtUpdate.setString(4, data.wiredVector.elementAt(j).getNetAddress());
                            stmtUpdate.setString(5, data.wiredVector.elementAt(j).getMask());
                            stmtUpdate.setString(6, data.wiredVector.elementAt(j).getBroadcastAddress());
                            stmtUpdate.setInt(7, data.wiredVector.elementAt(j).getMaxTransferRate());
                            stmtUpdate.setDouble(8, data.wiredVector.elementAt(j).getCurrTranferRate());
                            stmtUpdate.setDouble(9, data.wiredVector.elementAt(j).getCurrUsedBandwidth());
                            stmtUpdate.setDouble(10, data.wiredVector.elementAt(j).getPacketErrorRate());
                            stmtUpdate.setString(11, name);
                            stmtUpdate.executeUpdate();
                            stmtUpdate.close();
                            System.out.println("UPDATE in List_A SUCCESFULL");
                        }
                        else{
                            String insertData = "INSERT INTO list_A (NAME, mac_add, ip_add, default_gateway,"
                            + " net_add, mask, broad_add, max_transfer_rate, curr_transfer_rate,"
                            + " curr_used_bandwidth, packet_error_rate, deviceName )"
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            stmtInsert = conn.prepareStatement(insertData);
                            stmtInsert.setString(1, data.wiredVector.elementAt(j).getInterfaceName());
                            stmtInsert.setString(2, data.wiredVector.elementAt(j).getMacAddress());
                            stmtInsert.setString(3, data.wiredVector.elementAt(j).getIpAddress());
                            stmtInsert.setString(4, data.wiredVector.elementAt(j).getDefaultGateway());
                            stmtInsert.setString(5, data.wiredVector.elementAt(j).getNetAddress());
                            stmtInsert.setString(6, data.wiredVector.elementAt(j).getMask());
                            stmtInsert.setString(7, data.wiredVector.elementAt(j).getBroadcastAddress());
                            stmtInsert.setInt(8, data.wiredVector.elementAt(j).getMaxTransferRate());
                            stmtInsert.setDouble(9, data.wiredVector.elementAt(j).getCurrTranferRate());
                            stmtInsert.setDouble(10, data.wiredVector.elementAt(j).getCurrUsedBandwidth());
                            stmtInsert.setDouble(11, data.wiredVector.elementAt(j).getPacketErrorRate());
                            stmtInsert.setString(12, name);
                            stmtInsert.executeUpdate();
                            stmtInsert.close();
                            buffer.elementAt(position).InterfaceList.add(data.wiredVector.elementAt(j).getInterfaceName()); //ananewsi tou buffer
                            System.out.println("INSERTION in List_A SUCCESFULL");
                        }
                    }catch(SQLException e){}
                }
                for(int j = 0; j < data.wirelessVector.size(); j++){
                    try{
                        if(buffer.elementAt(position).WirelessList.contains(data.wirelessVector.elementAt(j).getInterfaceName())){
                            String updateListA = "UPDATE list_A SET mac_add = ?, "
                                    + "ip_add = ?, "
                                    + "default_gateway = ?, "
                                    + "net_add = ?, "
                                    + "mask = ?, "
                                    + "broad_add = ?, "
                                    + "max_transfer_rate = ?, "
                                    + "curr_transfer_rate = ?, "
                                    + "curr_used_bandwidth = ?, "
                                    + "packet_error_rate = ?, "
                                    + "deviceName = ? "
                                    + "WHERE NAME = ?";
                            stmtUpdate = conn.prepareStatement(updateListA);
                            stmtUpdate.setString(12, data.wirelessVector.elementAt(j).getInterfaceName());
                            stmtUpdate.setString(1, data.wirelessVector.elementAt(j).getMacAddress());
                            stmtUpdate.setString(2, data.wirelessVector.elementAt(j).getIpAddress());
                            stmtUpdate.setString(3, data.wirelessVector.elementAt(j).getDefaultGateway());
                            stmtUpdate.setString(4, data.wirelessVector.elementAt(j).getNetAddress());
                            stmtUpdate.setString(5, data.wirelessVector.elementAt(j).getMask());
                            stmtUpdate.setString(6, data.wirelessVector.elementAt(j).getBroadcastAddress());
                            stmtUpdate.setInt(7, data.wirelessVector.elementAt(j).getMaxTransferRate());
                            stmtUpdate.setDouble(8, data.wirelessVector.elementAt(j).getCurrTranferRate());
                            stmtUpdate.setDouble(9, data.wirelessVector.elementAt(j).getCurrUsedBandwidth());
                            stmtUpdate.setDouble(10, data.wirelessVector.elementAt(j).getPacketErrorRate());
                            stmtUpdate.setString(11, name);
                            stmtUpdate.executeUpdate();
                            stmtUpdate.close();
                            System.out.println("UPDATE in List_A SUCCESFULL for record: " + data.wirelessVector.elementAt(j).getInterfaceName());

                            String updateListB = "UPDATE list_B SET ESSID = ?, "
                                + "mac_base = ?, "
                                + "channel = ?, "    
                                + "access_point_status = ?, "
                                + "connection_quality = ?, "
                                + "received_signal_strength = ?, "
                                + "transmitted_signal = ?, "
                                + "noise = ?, "
                                + "rejected_packets = ?, "
                                + "deviceName = ? "
                                + "WHERE interfaceName = ?";
                            stmtUpdate = conn.prepareStatement(updateListB);
                            stmtUpdate.setString(11, data.wirelessVector.elementAt(j).getInterfaceName());
                            stmtUpdate.setString(1, data.wirelessVector.elementAt(j).getEssid());
                            stmtUpdate.setString(2, data.wirelessVector.elementAt(j).getWMac());
                            stmtUpdate.setInt(3, data.wirelessVector.elementAt(j).getChannel());
                            stmtUpdate.setString(4, data.wirelessVector.elementAt(j).getAccessPointStatus());
                            stmtUpdate.setString(5, data.wirelessVector.elementAt(j).getConnectionQuality());
                            stmtUpdate.setInt(6, data.wirelessVector.elementAt(j).getReceivedSignalStrenght());
                            stmtUpdate.setString(7, data.wirelessVector.elementAt(j).getTransmittedSignal());
                            stmtUpdate.setInt(8, data.wirelessVector.elementAt(j).getNoise());
                            stmtUpdate.setInt(9, data.wirelessVector.elementAt(j).getDescartedPackages());
                            stmtUpdate.setString(10, name);
                            stmtUpdate.executeUpdate();
                                                      
                            stmtUpdate.close();
                            System.out.println("UPDATE OF LIST B SUCCESSFULL for record: " + data.wirelessVector.elementAt(j).getInterfaceName());
                        }
                        else{
                            String insertDataListA = "INSERT INTO list_A (NAME, mac_add, ip_add, default_gateway,"
                            + " net_add, mask, broad_add, max_transfer_rate, curr_transfer_rate,"
                            + " curr_used_bandwidth, packet_error_rate, deviceName)"
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            stmtInsert = conn.prepareStatement(insertDataListA);
                            stmtInsert.setString(1, data.wirelessVector.elementAt(j).getInterfaceName());
                            stmtInsert.setString(2, data.wirelessVector.elementAt(j).getMacAddress());
                            stmtInsert.setString(3, data.wirelessVector.elementAt(j).getIpAddress());
                            stmtInsert.setString(4, data.wirelessVector.elementAt(j).getDefaultGateway());
                            stmtInsert.setString(5, data.wirelessVector.elementAt(j).getNetAddress());
                            stmtInsert.setString(6, data.wirelessVector.elementAt(j).getMask());
                            stmtInsert.setString(7, data.wirelessVector.elementAt(j).getBroadcastAddress());
                            stmtInsert.setInt(8, data.wirelessVector.elementAt(j).getMaxTransferRate());
                            stmtInsert.setDouble(9, data.wirelessVector.elementAt(j).getCurrTranferRate());
                            stmtInsert.setDouble(10, data.wirelessVector.elementAt(j).getCurrUsedBandwidth());
                            stmtInsert.setDouble(11, data.wirelessVector.elementAt(j).getPacketErrorRate());
                            stmtInsert.setString(12, name);
                            stmtInsert.executeUpdate();
                            stmtInsert.close();
                            
                            System.out.println("INSERTION in List_A SUCCESFULL");

                            String insertDataListB = "INSERT INTO list_B (ESSID, mac_base, channel, "
                                + "access_point_status, connection_quality, received_signal_strength, "
                                + "transmitted_signal, noise, rejected_packets, deviceName, interfaceName)"
                                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            stmtInsert = conn.prepareStatement(insertDataListB);
                            stmtInsert.setString(1, data.wirelessVector.elementAt(j).getEssid());
                            stmtInsert.setString(2, data.wirelessVector.elementAt(j).getWMac());
                            stmtInsert.setInt(3, data.wirelessVector.elementAt(j).getChannel());
                            stmtInsert.setString(4, data.wirelessVector.elementAt(j).getAccessPointStatus());
                            stmtInsert.setString(5, data.wirelessVector.elementAt(j).getConnectionQuality());
                            stmtInsert.setInt(6, data.wirelessVector.elementAt(j).getReceivedSignalStrenght());
                            stmtInsert.setString(7, data.wirelessVector.elementAt(j).getTransmittedSignal());
                            stmtInsert.setInt(8, data.wirelessVector.elementAt(j).getNoise());
                            stmtInsert.setInt(9, data.wirelessVector.elementAt(j).getDescartedPackages());
                            stmtInsert.setString(10, name);
                            stmtInsert.setString(11, data.wirelessVector.elementAt(j).getInterfaceName());
                            stmtInsert.executeUpdate();
                            stmtInsert.close();
                            System.out.println("INSERTION in List_B SUCCESFULL");
                            buffer.elementAt(position).WirelessList.add(data.wirelessVector.elementAt(j).getInterfaceName()); //ananewsi tou buffer
                        }
                    }catch(SQLException e){}
                }
                for(int k = 0; k < data.apVector.size(); k++){
                    try{
                        if(!buffer.elementAt(position).RSSList.contains(data.apVector.elementAt(k).getMacAddress())){
                            String insertAP_RSS = "INSERT INTO AP_RSS (Signal_Strength, deviceName, AP_mac_add) "       
                                    + "VALUES(?,?,?)";                                                                  
                            stmtInsert = conn.prepareStatement(insertAP_RSS);
                            stmtInsert.setInt(1, data.apVector.elementAt(k).getSignalStrength());
                            stmtInsert.setString(2, name);
                            stmtInsert.setString(3, data.apVector.elementAt(k).getMacAddress());
                            stmtInsert.executeUpdate();
                            buffer.elementAt(position).RSSList.add(data.apVector.elementAt(k).getMacAddress());
                        }
                        else{
                            String UpdateAP_RSS = "UPDATE AP_RSS SET Signal_Strength = ?, "
                                            + "deviceName = ?, "
                                            + "WHERE AP_mac_add = ?";
                            stmtUpdate = conn.prepareStatement(UpdateAP_RSS);
                            stmtUpdate.setString(3, data.apVector.elementAt(k).getMacAddress());
                            stmtUpdate.setInt(1, data.apVector.elementAt(k).getSignalStrength());
                            stmtUpdate.setString(2, name);
                        }
                        AP_exists = false;
                        for(int x = 0; x < buffer.size(); x++){
                            if(buffer.elementAt(x).AccessPointList.contains(data.apVector.elementAt(k).getMacAddress())){      
                                AP_exists = true;
                                break;
                            }
                        }
                        if(AP_exists == false){
                            String insertAPs = "INSERT INTO list_C (AP_mac_address, AP_ESSID, AP_Channel, AP_Status, deviceName)"
                                                + "VALUES(?, ?, ?, ?, ?)";
                                stmtInsert = conn.prepareStatement(insertAPs);
                                stmtInsert.setString(1, data.apVector.elementAt(k).getMacAddress());
                                stmtInsert.setString(2, data.apVector.elementAt(k).getESSID());
                                stmtInsert.setInt(3, data.apVector.elementAt(k).getChannel());
                                stmtInsert.setString(4, data.apVector.elementAt(k).getStatus());
                                stmtInsert.setString(5, name);
                                stmtInsert.executeUpdate();
                                stmtInsert.close();
                                System.out.println("INSERTION IN LIST_C SUCCESSFULL");
                                buffer.elementAt(position).AccessPointList.add(data.apVector.elementAt(k).getMacAddress());
                        }
                        else{
                            String UpdateAPs = "UPDATE list_C SET AP_ESSID = ?, "
                                        + "AP_Channel = ?, "
                                        + "AP_Status = ?, "
                                        + "deviceName = ?"
                                        + "WHERE AP_mac_address = ?";
                            stmtUpdate = conn.prepareStatement(UpdateAPs);
                            stmtUpdate.setString(5, data.apVector.elementAt(k).getMacAddress());
                            stmtUpdate.setString(1, data.apVector.elementAt(k).getESSID());
                            stmtUpdate.setInt(2, data.apVector.elementAt(k).getChannel());
                            stmtUpdate.setString(3, data.apVector.elementAt(k).getStatus());
                            stmtUpdate.setString(4, name);
                            stmtUpdate.executeUpdate();
                            System.out.println("UPDATE OF LIST_C SUCCESSFULL");
                            stmtUpdate.close();
                        }
                        String UpdateAP_RSS = "UPDATE AP_RSS SET Signal_Strength = ?, "
                                        + "deviceName = ?, "
                                        + "WHERE AP_mac_add = ?";
                        stmtUpdate = conn.prepareStatement(UpdateAP_RSS);
                        stmtUpdate.setString(3, data.apVector.elementAt(k).getMacAddress());
                        stmtUpdate.setInt(1, data.apVector.elementAt(k).getSignalStrength());
                        stmtUpdate.setString(2, name);
                        System.out.println("UPDATE OF RSS LIST SUCCESFULL");
                    }catch(SQLException e){}
                }
                buffer.elementAt(position).setTimeStamp(System.currentTimeMillis());        //Ananewsi tou timestamp meta to update.
            }
            //////////////////////////////////////////////INSERTS/////////////////////////////////////////////////////////////////////
            else{                                               //ean den iparxei i siskeui mesa sto buffer tote tha kataxwrithoun ola ta interfaces tis siskeuis sti vasi,
                DeviceInfo newDevice = new DeviceInfo(name,System.currentTimeMillis(), data.apVector);    //kai tha ananewthei to timestamp kai to onoma tis siskeuis sto buffer.
                
                for(int i = 0; i<data.wiredVector.size(); i++){  
                    try{
                        String insertData = "INSERT INTO list_A (NAME, mac_add, ip_add, default_gateway,"
                                + " net_add, mask, broad_add, max_transfer_rate, curr_transfer_rate,"
                                + " curr_used_bandwidth, packet_error_rate, deviceName)"
                                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        stmtInsert = conn.prepareStatement(insertData);
                        stmtInsert.setString(1, data.wiredVector.elementAt(i).getInterfaceName());
                        stmtInsert.setString(2, data.wiredVector.elementAt(i).getMacAddress());
                        stmtInsert.setString(3, data.wiredVector.elementAt(i).getIpAddress());
                        stmtInsert.setString(4, data.wiredVector.elementAt(i).getDefaultGateway());
                        stmtInsert.setString(5, data.wiredVector.elementAt(i).getNetAddress());
                        stmtInsert.setString(6, data.wiredVector.elementAt(i).getMask());
                        stmtInsert.setString(7, data.wiredVector.elementAt(i).getBroadcastAddress());
                        stmtInsert.setInt(8, data.wiredVector.elementAt(i).getMaxTransferRate());
                        stmtInsert.setDouble(9, data.wiredVector.elementAt(i).getCurrTranferRate());
                        stmtInsert.setDouble(10, data.wiredVector.elementAt(i).getCurrUsedBandwidth());
                        stmtInsert.setDouble(11, data.wiredVector.elementAt(i).getPacketErrorRate());
                        stmtInsert.setString(12, name);
                        stmtInsert.executeUpdate();
                        stmtInsert.close();
                        System.out.println("INSERTION in List_A SUCCESFULL");
                    }catch(SQLException e){}
                    newDevice.InterfaceList.add(data.wiredVector.elementAt(i).getInterfaceName());      //ananewsi tou buffer
                }
                for(int i = 0; i < data.wirelessVector.size(); i++){
                    try{
                        String insertDataListA = "INSERT INTO list_A (NAME, mac_add, ip_add, default_gateway,"
                                + " net_add, mask, broad_add, max_transfer_rate, curr_transfer_rate,"
                                + " curr_used_bandwidth, packet_error_rate, deviceName)"
                                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        stmtInsert = conn.prepareStatement(insertDataListA);
                        stmtInsert.setString(1, data.wirelessVector.elementAt(i).getInterfaceName());
                        stmtInsert.setString(2, data.wirelessVector.elementAt(i).getMacAddress());
                        stmtInsert.setString(3, data.wirelessVector.elementAt(i).getIpAddress());
                        stmtInsert.setString(4, data.wirelessVector.elementAt(i).getDefaultGateway());
                        stmtInsert.setString(5, data.wirelessVector.elementAt(i).getNetAddress());
                        stmtInsert.setString(6, data.wirelessVector.elementAt(i).getMask());
                        stmtInsert.setString(7, data.wirelessVector.elementAt(i).getBroadcastAddress());
                        stmtInsert.setInt(8, data.wirelessVector.elementAt(i).getMaxTransferRate());
                        stmtInsert.setDouble(9, data.wirelessVector.elementAt(i).getCurrTranferRate());
                        stmtInsert.setDouble(10, data.wirelessVector.elementAt(i).getCurrUsedBandwidth());
                        stmtInsert.setDouble(11, data.wirelessVector.elementAt(i).getPacketErrorRate());
                        stmtInsert.setString(12, name);
                        stmtInsert.executeUpdate();
                        System.out.println("INSERTION in List_A for a wireless Interface SUCCESFULL");

                        String insertDataListB = "INSERT INTO list_B (ESSID, mac_base, channel, "
                                + "access_point_status, connection_quality, received_signal_strength, "
                                + "transmitted_signal, noise, rejected_packets, deviceName, interfaceName)"
                                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        stmtInsert = conn.prepareStatement(insertDataListB);
                        stmtInsert.setString(1, data.wirelessVector.elementAt(i).getEssid());
                        stmtInsert.setString(2, data.wirelessVector.elementAt(i).getWMac());
                        stmtInsert.setInt(3, data.wirelessVector.elementAt(i).getChannel());
                        stmtInsert.setString(4, data.wirelessVector.elementAt(i).getAccessPointStatus());
                        stmtInsert.setString(5, data.wirelessVector.elementAt(i).getConnectionQuality());
                        stmtInsert.setInt(6, data.wirelessVector.elementAt(i).getReceivedSignalStrenght());
                        stmtInsert.setString(7, data.wirelessVector.elementAt(i).getTransmittedSignal());
                        stmtInsert.setInt(8, data.wirelessVector.elementAt(i).getNoise());
                        stmtInsert.setInt(9, data.wirelessVector.elementAt(i).getDescartedPackages());
                        stmtInsert.setString(10, name);
                        stmtInsert.setString(11, data.wirelessVector.elementAt(i).getInterfaceName());
                        stmtInsert.executeUpdate();
                        System.out.println("INSERTION in List_B SUCCESFULL");
                        stmtInsert.close();
                        }catch(SQLException e){}
                    newDevice.WirelessList.add(data.wirelessVector.elementAt(i).getInterfaceName());       //ananewsi tou buffer meta tin eisagwgi sti vasi
                }
                for(int i = 0; i < data.apVector.size(); i++){
                    exists = false;         //tha elegksoume ean ta AP pou pane na eisaxthoun twra, exoun eisaxthei apo kapoia alli device proigoumenos
                    try{
                        for(int k = 0; k < buffer. size(); k++){
                            if(buffer.elementAt(k).AccessPointList.contains(data.apVector.elementAt(i).getMacAddress())){      
                                exists = true;
                                break;
                            }
                        }
                        if(exists == false){
                            String insertAPs = "INSERT INTO list_C (AP_mac_address, AP_ESSID, AP_Channel, AP_Status, deviceName)" //tote kai mono tote apothikeuetai sti vasi.
                                        + "VALUES(?, ?, ?, ?, ?)";
                            stmtInsert = conn.prepareStatement(insertAPs);
                            stmtInsert.setString(1, data.apVector.elementAt(i).getMacAddress());
                            stmtInsert.setString(2, data.apVector.elementAt(i).getESSID());
                            stmtInsert.setInt(3, data.apVector.elementAt(i).getChannel());
                            stmtInsert.setString(4, data.apVector.elementAt(i).getStatus());
                            stmtInsert.setString(5, name);
                            stmtInsert.executeUpdate();
                            stmtInsert.close();
                            System.out.println("INSERTION in List_C SUCCESFULL");
                        }
                        String insertAP_RSS = "INSERT INTO AP_RSS (Signal_Strength, deviceName, AP_mac_add) "       //sto sigkekrimeno table mpenei panta eite to exei vrei alli siskeui
                                + "VALUES(?,?,?)";                                                                  //eite oxi, giati endexetai na diaferoun sto signal strength pou antilamvanontai
                        stmtInsert = conn.prepareStatement(insertAP_RSS);
                        stmtInsert.setInt(1, data.apVector.elementAt(i).getSignalStrength());
                        stmtInsert.setString(2, name);
                        stmtInsert.setString(3, data.apVector.elementAt(i).getMacAddress());
                        stmtInsert.executeUpdate();
                    }catch(SQLException e){}
                    newDevice.RSSList.add(data.apVector.elementAt(i).getMacAddress());
                    newDevice.AccessPointList.add(data.apVector.elementAt(i).getMacAddress());      //ananewsi tou buffer meta tin eisagwgi sti vasi
                }
                newDevice.setTimeStamp(System.currentTimeMillis());                             //Setarisma tou xronou eisagwgis tis device stin endiamesi mnimi
                monitorDataMF.grafiko.addDevice(name, buffer.size());
                buffer.add(newDevice);      //vazoume ti nea siskeui stin endiamesi mnimi
                
            }
        }
    }
    
    @WebMethod
    public void setTerminalData(String device, TerminalData data){}
}
