package MD;


public class Wired {
    private String interfaceName;
    private String macAddress;
    private String ipAddress;
    private String defaultGateway;
    private String netAddress = "";
    private String mask; 
    private String broadcastAddress;
    private int maxTransferRate;
    private double currentTransferRate;
    private double currentUsedBandwidth;
    private double packetErrorRate;
    
    
    public void setInterfaceName(String name){
        this.interfaceName = name;
    }
    
    public void setMacAddress(String mac){
        this.macAddress = mac;
    }
    
    public void setIpAddress(String IP){
        this.ipAddress = IP;
    }
    
    public void setDefaultGateway(String gateway){
        this.defaultGateway = gateway;
    }
    
    public void setNetAddress(String netAddress){
        this.netAddress = netAddress;
    }
    
    public void setMask(String mask){
        this.mask = mask;
    }
    
    public void setBroadcastAddress(String broadAdd){
        this.broadcastAddress = broadAdd;
    }
    
    public void setMaxTransferRate(int maxTransferRate){
        this.maxTransferRate = maxTransferRate;
    }
    
    public void setCurrTranferRate(double curr ){
        this.currentTransferRate = curr;
    }
    
    public void setCurrUsedBandwidth(double broadAdd){
        this.currentUsedBandwidth = broadAdd;
    }
    
    public void setPacketErrorRate(double errorRate){
        this.packetErrorRate = errorRate;
    }
    
    ///////////////////GETTERS/////////////////////////////
    public String getInterfaceName(){
        return interfaceName;
    }
    
    public String getMacAddress(){
        return macAddress;
    }
    
    public String getIpAddress(){
        return ipAddress;
    }
    
    public String getDefaultGateway(){
        return defaultGateway;
    }
    
    public String getNetAddress(){
        return netAddress;
    }
    
    public String getMask(){
        return mask;
    }
    
    public String getBroadcastAddress(){
        return broadcastAddress;
    }
    
    public int getMaxTransferRate(){
        return maxTransferRate;
    }
    
    public double getCurrTranferRate(){
        return currentTransferRate;
    }
    
    public double getCurrUsedBandwidth(){
        return currentUsedBandwidth;
    }
    
    public double getPacketErrorRate(){
        return packetErrorRate;
    }
    
    
}
