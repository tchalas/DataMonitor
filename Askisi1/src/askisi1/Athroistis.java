package askisi1;

import javax.xml.ws.WebServiceException;

public class Athroistis implements Runnable {            
    webservice.Wired tempWired; 
    webservice.Wireless tempWireless;
    webservice.AccessPoint tempAP;
    webservice.MonitorData WebData;
    String deviceName;
    webservice.MonitorDataWS port;
    
    Athroistis(){
        WebData = new webservice.MonitorData();
        String[] comm = {"/bin/sh", "-c", "hostname"};  //pernoume to onoma tis siskeuis
        this.deviceName = Wired.parsing(comm);
        webservice.MonitorDataWSService service=new webservice.MonitorDataWSService();
        port=service.getMonitorDataWSPort();
    }
    
    public void run() {
        //while(!Thread.currentThread().isInterrupted()){
            for(int i = 0; i < MainThread.WiredVector.size(); i++){
                tempWired = new webservice.Wired();
                tempWired.setInterfaceName(MainThread.WiredVector.elementAt(i).getInterfaceName()); 
                tempWired.setIpAddress(MainThread.WiredVector.elementAt(i).getIpAddress());
                tempWired.setBroadcastAddress(MainThread.WiredVector.elementAt(i).getBroadcastAddress());
                tempWired.setMacAddress(MainThread.WiredVector.elementAt(i).getMacAddress());
                tempWired.setMask(MainThread.WiredVector.elementAt(i).getMask());
                tempWired.setDefaultGateway(MainThread.WiredVector.elementAt(i).getDefaultGateway());
                tempWired.setNetAddress(MainThread.WiredVector.elementAt(i).getNetAddress());
                tempWired.setMaxTransferRate(MainThread.WiredVector.elementAt(i).getMaxTransferRate());
                tempWired.setCurrTranferRate(MainThread.WiredVector.elementAt(i).getCurrentTransferRate());
                tempWired.setCurrUsedBandwidth(MainThread.WiredVector.elementAt(i).getCurrentUsedBandwidth());
                tempWired.setPacketErrorRate(MainThread.WiredVector.elementAt(i).getPacketErrorRate()); 
                WebData.getWiredVector().add(tempWired);
            }
            for(int i = 0; i < MainThread.WirelessVector.size(); i++){
                tempWireless = new webservice.Wireless();
                tempWireless.setInterfaceName(MainThread.WirelessVector.elementAt(i).getInterfaceName()); 
                tempWireless.setIpAddress(MainThread.WirelessVector.elementAt(i).getIpAddress());
                tempWireless.setBroadcastAddress(MainThread.WirelessVector.elementAt(i).getBroadcastAddress());
                tempWireless.setWMac(MainThread.WirelessVector.elementAt(i).getMacAddress());
                tempWireless.setMask(MainThread.WirelessVector.elementAt(i).getMask());
                tempWireless.setDefaultGateway(MainThread.WirelessVector.elementAt(i).getDefaultGateway());
                tempWireless.setNetAddress(MainThread.WirelessVector.elementAt(i).getNetAddress());
                tempWireless.setMaxTransferRate(MainThread.WirelessVector.elementAt(i).getMaxTransferRate());
                tempWireless.setCurrTranferRate(MainThread.WirelessVector.elementAt(i).getCurrentTransferRate());
                tempWireless.setCurrUsedBandwidth(MainThread.WirelessVector.elementAt(i).getCurrentUsedBandwidth());
                tempWireless.setPacketErrorRate(MainThread.WirelessVector.elementAt(i).getPacketErrorRate()); 
                /////////////Stoixeia LIstas B///////////////////////////
                tempWireless.setEssid(MainThread.WirelessVector.elementAt(i).getESSID());
                tempWireless.setMacAddress(MainThread.WirelessVector.elementAt(i).getMacBase());
                tempWireless.setChannel(MainThread.WirelessVector.elementAt(i).getChannel());
                tempWireless.setAccessPointStatus(MainThread.WirelessVector.elementAt(i).getAccessPointStatus());
                tempWireless.setConnectionQuality(MainThread.WirelessVector.elementAt(i).getConnectionQuality());
                tempWireless.setNoise(MainThread.WirelessVector.elementAt(i).getNoise());
                tempWireless.setDescartedPackages(MainThread.WirelessVector.elementAt(i).getRejectedPackets());
                tempWireless.setTransmittedSignal(MainThread.WirelessVector.elementAt(i).getTransmittedSignalStrength());
                tempWireless.setReceivedSignalStrenght(MainThread.WirelessVector.elementAt(i).getReceivedSignalStrength());
                WebData.getWirelessVector().add(tempWireless);
            }
            for(int i = 0; i < Wireless.accessPoints.size(); i++){
                tempAP = new webservice.AccessPoint();
                tempAP.setESSID(Wireless.accessPoints.elementAt(i).getESSID());
                tempAP.setChannel(Wireless.accessPoints.elementAt(i).getChannel());
                tempAP.setMacAddress(Wireless.accessPoints.elementAt(i).getMacAddress());
                tempAP.setSignalStrength(Wireless.accessPoints.elementAt(i).getSignalStrength());
                tempAP.setStatus(Wireless.accessPoints.elementAt(i).getStatus());
                WebData.getApVector().add(tempAP);
            }
            try{
                port.setMonitorData(deviceName, WebData);
            }catch(WebServiceException e){
                System.out.println("Unable to connect to server. Please check that the server is available");
            }
            //try{
            //    Thread.sleep(60000);
            //}catch(InterruptedException e){}
        //}
    }
}


