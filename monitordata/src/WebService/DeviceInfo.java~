package WebService;

import MD.AccessPoint;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DeviceInfo {
    private String deviceName;
    public List<String> InterfaceList = new ArrayList<String>();       //lista me ta interfaces tis siskeuis
    public List<String>WirelessList = new ArrayList<String>();
    List<String> AccessPointList = new ArrayList<String>();     //lista me tis mac address twn access points pou anagnwrizei i siskeui
    List<String> RSSList = new ArrayList<String>();
    static public Vector<AccessPoint> apBufferVector;
    private long deviceTimeAddedUpdated;                        
    
    DeviceInfo(String deviceName, long timeAdded, Vector<AccessPoint> apBufferVector){
        this.deviceName = deviceName;
        this.deviceTimeAddedUpdated = timeAdded;
        DeviceInfo.apBufferVector=apBufferVector;
    }   
    
    public String getDeviceName(){
        return deviceName;
    }
    
    public long getTimeAdded(){
        return deviceTimeAddedUpdated;
    }

    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }
    
    public void setTimeStamp(long time){
        this.deviceTimeAddedUpdated = time;
    }
}

