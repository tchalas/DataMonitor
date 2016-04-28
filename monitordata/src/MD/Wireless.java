package MD;


public class Wireless extends Wired {
    private String essid;
    private String wMac;
    private int channel;
    private String accessPointStatus;
    private int receivedSignalStrength;
    private String connectionQuality; 
    private String transmittedSignal;
    private int noise;
    private int descartedPackages;    
    
    
    public void setEssid(String essid) {
        this.essid = essid;
     }
    
    public void setWMac(String wMac) {
        this.wMac = wMac;
    }
    
    public void setChannel(int channel) {
        this.channel = channel;
    }
    
    public void setAccessPointStatus(String status) {
        this.accessPointStatus = status;
    }
    
    public void setConnectionQuality(String quality) {
        this.connectionQuality = quality;
    }
    
    public void setReceivedSignalStrenght(int receivedSignalStrength) {
        this.receivedSignalStrength = receivedSignalStrength;
    }
    
    public void setTransmittedSignal(String tranSignal){
        this.transmittedSignal = tranSignal;
    }
    
    public void setNoise(int noise){
        this.noise = noise;
    }
    
    public void setDescartedPackages(int pack){
        this.descartedPackages = pack;
    }
    
    ////////////////////GETTERS//////////////////////
    public String getEssid() {
        return essid;
    }

    public String getWMac() {
        return wMac;
    }

    public int getChannel() {
        return channel;
    }

    public String getAccessPointStatus() {
        return accessPointStatus;
    }

    public String getConnectionQuality() {
        return connectionQuality;
    }

    public int getReceivedSignalStrenght() {
        return receivedSignalStrength;
    }
    
    public String getTransmittedSignal(){
        return transmittedSignal;
    }
    
    public int getNoise(){
        return noise;
    }
    
    public int getDescartedPackages(){
        return descartedPackages;
    }
}
