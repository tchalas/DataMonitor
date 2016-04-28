package MD;


public class AccessPoint {
    private String macAddress;
    private String ESSID;
    private String status;
    private int signalStrength;
    private int channel;
    
    
    public void setMacAddress(String mac){
        this.macAddress = mac;
    }
    
    public void setESSID(String ESSID){
        this.ESSID = ESSID;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setSignalStrength(int strength){
        this.signalStrength = strength;
    }
    
    public void setChannel(int channel){
        this.channel = channel;
    }
    
    public String getMacAddress(){
        return macAddress;
    }

    public String getESSID(){
        return ESSID;
    }

    public int getChannel(){
        return channel;
    }

    public String getStatus(){
        return status;
    }

    public int getSignalStrength(){
        return signalStrength;
    }
}
