package askisi1;

import java.io.*;
import java.util.*;

public class Wireless extends Wired{
    private String transmittedSignalStrength,macBase,ESSID,wirelessName,accessPointStatus,connectionQuality;
    private int channel,receivedSignalStrength,noise,rejectedPackets = 0;
    private boolean isConnected;
    private int wirelessTime,max,c,tempAP;
    private static boolean checkedAccessPoints = false;
    static Vector<AccessPoint> accessPoints = new Vector<AccessPoint>();

    public Wireless(String name, int time, int c, int max, int percentage){
        super(name,time,c,max,percentage);                    //kaleitai o constructor tou Wired
        wirelessTime = getDefaultTime();
        max = getMaxChainLinks();
        c = getC();
        setWirelessName();                      //se auti ti sinartisi 'setMacBase' elegxetai episis ean to interface mas einai sindedemeno se kapoio asirmato diktio
        setMacBase();                           //ean einai arxikopoiountai ta analoga pedia, kai ean den einai, emfanizetai katallilo minima.
        String s = "Not-Assosiated";             //to status pou emfanizetai sto terminal ean den einai sindedemeno to interface
        if(macBase.equalsIgnoreCase(s)){                //elegxoume ean sto pedio macBase, einai i frasi Not-Assosiated gia na dei ean einai sindedemeno kapou
            isConnected = false;
            this.macBase = "Not-Connected";
            this.ESSID = "Not-Connected";
            this.accessPointStatus = "Not-Connected";
            this.channel = 0;
        }
        else{
            isConnected = true;
            setESSID();
            setChannel();
            if(!Wireless.checkedAccessPoints){
                setAccessPointStatus();
                tempAP = AccessPoint.getNumOfAccessPoints();
            }
        }
        setConnectionQuality();
        setReceivedSignalStrength();
        setNoise();
        setRejectedPackets();
        setTransmittedSignalStrength();
        if(!Wireless.checkedAccessPoints){       //ean den exoun akomi oristei ta access points,
            setAreaAccessPoints();               //tote kaloume tin sinartisi pou ta arxikopoiei. PARADOXI (vlepe README)
            tempAP = AccessPoint.getNumOfAccessPoints();
        }
    }

    public Wireless(Wireless copy){           //copy constructor
        super((Wired)copy);
        this.ESSID = new String(copy.ESSID);
        this.accessPointStatus = new String(copy.accessPointStatus);
        this.channel = copy.channel;
        this.connectionQuality = new String(copy.connectionQuality);
        this.isConnected = copy.isConnected;
        this.macBase = new String(copy.macBase);
        this.noise = copy.noise;
        this.receivedSignalStrength = copy.receivedSignalStrength;
        this.rejectedPackets = copy.rejectedPackets;
        this.transmittedSignalStrength = new String(copy.transmittedSignalStrength);
        this.wirelessName = new String(copy.wirelessName);
    }

    @Override public void run(){
        int k = max;
        int i = max;
        int counter = 0;
        while(!Thread.currentThread().isInterrupted()){
            Wireless temp = new Wireless(this);
            long startTime = System.currentTimeMillis();
            //Kanoume ksana parsing gia na valoume ek neou tis times twn melwn, etsi wste na sigkrinoume me tin proigoumeni eikona tou interface
            //kai na paratirisoume tixwn allages
            this.setWirelessName();
            this.setMacBase();
            String s = "Not-Assosiated";
            if(this.macBase.equalsIgnoreCase(s)){
                this.isConnected = false;
                this.macBase = "Not-Connected";
                this.ESSID = "Not-Connected";
                this.accessPointStatus = "Not-Connected";
                this.channel = 0;
            }
            else{
                this.isConnected = true;
                this.setESSID();
                this.setChannel();
                this.setAccessPointStatus();
            }
            this.setConnectionQuality();
            this.setReceivedSignalStrength();
            this.setNoise();
            this.setRejectedPackets();
            this.setTransmittedSignalStrength();
            accessPoints.clear();
            setAreaAccessPoints();      //edw den prepei na elegxoume ean ta access points exoun vrethei idi, giati outos i allws theloume na ta ksanadiavasoume
            this.setMacAddress();       //gia na doume ean allakse o arithmos tus
            this.setIpAddress();         //apothikeuontai ek neou oi times tou wired
            this.setBroadcastAddress();
            this.setMask();
            this.setDefaultGateway();
            this.setCurrentBandwidthUsage();
            String ip_Address = this.getIpAddress();
            String tempMask = this.getMask();
            this.setNetAddress(ip_Address, tempMask);
            this.setCurrentTransferRate();
            this.setPacketErrorRate();
            this.setMaxTransferRate();
            if((this.equals(temp)) && (tempAP == accessPoints.size())){                  //elegxetai me tin eikona tou ena ipirksan allages kai ean exoume allagi ston arithmo twn access points
                System.out.println("den paratirithike allagi sta dedomena tou wireless interface");
                if(((counter++ == c) && (i > 1)) || (i > 1)){             //metraei poses anepitixeis epanalipseis exoun ginei
                    i--;                      //ean exoun toses anepitixeis epanalipseis oses i parametros c,
                }                             //tote metapiptei stin epomeni katastasi kai arxikopoiei ton counter
                else{
                    i = 1;
                }
            }
            else{
                if(tempAP != accessPoints.size()){
                    System.out.println("Paratirithike Allagi ston arithmo twn Access Points tis perioxis");
                }
                System.out.println("Paratirithike Allagi sta wireless");
                i = max;            //epistrefei stin arxiki katastasi
                counter = 0;
            }
            tempAP = AccessPoint.getNumOfAccessPoints();
            long stopTime = System.currentTimeMillis();
            System.out.println("Monitoring interface:" + wirelessName);
            Athroistis dedomena = new Athroistis();   
            Thread t = new Thread(dedomena);
            t.start();
            long DT = stopTime - startTime;
            try{
                System.out.println("Wireless Sleeping for: " + (k - i + 1) + "times the SleepTime");
                Thread.sleep(((k- i + 1)*wirelessTime) - DT);
            }catch(InterruptedException e){
                System.out.println("Termatismos thread");
                checkedAccessPoints = false;        //an aposinthethei to wireless, tithetai kai i sigkekrimeni metavliti false, gia na kserei to epomeno
            }                                       //wireless interface oti einai ipeuthino na vrei ta access points
        }
    }

    public void setWirelessName(){
        this.wirelessName = getInterfaceName();
    }

    public void setMacBase(){
        String[] command = {"/bin/sh", "-c", "sudo iwconfig " + wirelessName + " | awk '{if ($5 == \"Point:\") print $6}'"};
        String temp = parsing(command);
        macBase = temp;
    }

    public void setESSID(){
        String[] command = {"/bin/sh", "-c", "sudo iwconfig " + wirelessName + " | awk '/ESSID/ { print $4 }'"};    //epistrefei to string pou periexei to ESSID
        String temp = parsing(command);
        ESSID = temp.replace("ESSID:", "");
    }

    public void setChannel(){
        String[] command = {"/bin/sh", "-c", "sudo iwlist " + wirelessName + " channel | awk '{if ($1 == \"Current\") print $5}'"};
        String temp = parsing(command);
        temp = temp.replace(")", "");
        channel = Integer.parseInt(temp);
    }

    public void setAccessPointStatus(){
        String[] command = {"/bin/sh", "-c", "sudo iwconfig " + wirelessName + " | awk '/Mode:/ { print $1}'"};
        String temp = parsing(command);
        accessPointStatus = temp.replace("Mode:", "");
    }

    public void setTransmittedSignalStrength(){         //PARADOXI #1
        String[] command = {"/bin/sh", "-c", "sudo iwconfig " + wirelessName + " | grep -oP 'Tx-Power:[0,9]{1,4}'"};
        String temp = parsing(command);
        if(temp != null && !temp.isEmpty()){
            temp = temp.replace("Tx-Power:", "");           
            transmittedSignalStrength = temp + " dbm";

        }
        else{
            transmittedSignalStrength = "Not-Supported";
        }
    }

    public void setConnectionQuality(){
        String[] command = {"/bin/sh", "-c", "sudo iwconfig " + wirelessName + " | awk '/Quality=/ { print $2 }'"};    //epistrefei to string pou periexei ti poiotita tis sindesis
        String temp = parsing(command);
        connectionQuality = temp.replace("Quality=","");
    }

    public void setReceivedSignalStrength(){
        String[] command = {"/bin/sh", "-c", "sudo iwconfig " + wirelessName + " | awk '/Signal/ { print $4 }'"};
        String temp = parsing(command);
        temp = temp.replace("level:","");
        this.receivedSignalStrength = Integer.parseInt(temp);
    }

    public void setNoise(){
        String[] command = {"/bin/sh", "-c", "sudo iwconfig " + wirelessName + " | awk '/Noise/ { print $7 }'"};
        String temp = parsing(command);
        temp = temp.replace("level:","");
        noise = Integer.parseInt(temp);
    }

    public void setRejectedPackets(){
        rejectedPackets = 0;
        String[] command = {"/bin/sh", "-c", "cat /proc/net/wireless | grep " + wirelessName + "| awk '{print $6 \" \" $7 \" \" $8 \" \" $9 \" \" $10}'"}; //metaferoume olous tous arithmous twn aporrifthentwn packets
        String temp = parsing(command);
        String[] info = temp.split(" ");
        for (int i=0; i<5; i++){
            rejectedPackets += Integer.parseInt(info[i]);  //prostithentai oi arithmoi pou pernoume ap ta pedia Discarded packets pou vriskoume sto arxeio /proc/net/wireless
        }
    }

    public boolean getStatus(){
        return isConnected;
    }

    public String getESSID(){
        return ESSID;
    }

    public String getWirelessName(){
        return wirelessName;
    }

    public String getMacBase(){
        return macBase;
    }

    public int getChannel(){
        return channel;
    }

    public String getConnectionQuality(){
        return connectionQuality;
    }

    public int getReceivedSignalStrength(){
        return receivedSignalStrength;
    }

    public int getNoise(){
        return noise;
    }

    public int getRejectedPackets(){
        return rejectedPackets;
    }

    public String getAccessPointStatus(){
        return accessPointStatus;
    }

    public String getTransmittedSignalStrength(){
        return transmittedSignalStrength;
    }

    public boolean equals(Wireless temp){ 
        return (super.equals((Wired)temp) &&
                this.transmittedSignalStrength.equals(temp.transmittedSignalStrength) &&
                this.noise == temp.noise &&
                this.receivedSignalStrength == temp.receivedSignalStrength);
    }

    @Override public String toString(){
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String s = super.toString();
        result.append(s + newLine);
        result.append("Name :" + wirelessName + newLine);
        result.append("Mac tou Stathmou Vasis: " + macBase + newLine);
        result.append("ESSID: " + ESSID + newLine);
        result.append("Channel: " + channel + newLine);
        result.append("Connection Quality: " + connectionQuality + newLine);
        result.append("Received Signal Strength: " + receivedSignalStrength + " dBm " + newLine);
        result.append("Noise Strength: " + noise + " dBm " + newLine);
        result.append("Access Point Status: " + accessPointStatus + newLine);
        result.append("Isxis Ekpompis tou interface: " + transmittedSignalStrength + newLine);
        result.append("Number of Rejected Packets: " + rejectedPackets + newLine);
        return result.toString();
    }

    public void setAreaAccessPoints(){
        String mac = "",essid = "",status = "";
        int strength = 0,kanali = 0;
        String temp;
        AccessPoint.setNumofAccessPoints(0); //arxikopoieitai kathe fora pou kaleitai auti i sinartisi 
        try{
            String[] command = {"/bin/sh", "-c", "sudo iwlist " + wirelessName + " scanning | grep -A5 \"Cell\" "};
            Process child = Runtime.getRuntime().exec(command);
            BufferedReader r = new BufferedReader(new InputStreamReader(child.getInputStream()));
            temp = r.readLine();        //arxikopoiisi tis temp
            while(temp != null){
                if(temp.contains("Cell")){
                    temp = temp.trim();
                    String[] info = temp.split(" ");
                    mac = info[4];
                    while((temp = r.readLine()) != null && !(temp.contains("Cell"))){
                        if(temp.contains("ESSID:")){
                            temp = temp.trim();
                            essid = temp.replace("ESSID:","");
                        }
                        if(temp.contains("Frequency:")){
                            temp = temp.trim();
                            String[] info1 = temp.split(" ");
                            info1[3] = info1[3].replace(")","");
                            kanali = Integer.parseInt(info1[3]);
                        }
                        if(temp.contains("Mode:")){
                            temp = temp.trim();
                            status = temp.replace("Mode:","");
                        }
                        if(temp.contains("Quality=")){
                            temp = temp.trim();
                            String[] info2 = temp.split(" ");
                            info2[3] = info2[3].replace("level=","");
                            strength = Integer.parseInt(info2[3]);
                        }
                    }
                    AccessPoint newAP = new AccessPoint(mac,essid,kanali,status,strength); //dimiourgeitai ena access point me stoixeia auta pou pirame apo to parsing tis iwlist scanning
                    Wireless.accessPoints.addElement(newAP);        
                }
            }
            r.close();
        }catch(IOException e){e.printStackTrace();}
    }
}