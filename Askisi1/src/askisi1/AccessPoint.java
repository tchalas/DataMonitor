package askisi1;


public class AccessPoint {
    private String macAddress,ESSID,status;
    private int signalStrength,channel;
    private static int numOfAccessPoints = 0 ;

    public AccessPoint(String mac, String essid, int chann, String katastasi, int power ){
        ++numOfAccessPoints;
        this.macAddress = mac;
        this.ESSID = essid;
        this.channel = chann;
        this.status = katastasi;
        this.signalStrength = power;
    }

    @Override public String toString(){
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        result.append(this.getClass().getName() + newLine);
        result.append("# " + numOfAccessPoints + " Access Point " + newLine);
        result.append("Dieuthinsi Mac tou Access Point: " + macAddress  + newLine);
        result.append("ESSID tou Access Point: " + ESSID  + newLine);
        result.append("To Kanali sto opoio ekpempei: " + channel + newLine);
        result.append("H katastasi leitourgias tou: " + status + newLine);
        result.append("H isxis toy lamvanomenou simatos: " + signalStrength + " dbm" + newLine);
        result.append(newLine);
        return result.toString();
    }

    public static int getNumOfAccessPoints(){
        return numOfAccessPoints;
    }

    public static void setNumofAccessPoints(int x){
        numOfAccessPoints = x;
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
