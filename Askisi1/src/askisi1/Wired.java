package askisi1;

import java.io.*;



public class Wired implements Runnable {
    private String interfaceName,macAddress,ipAddress,defaultGateway,netAddress = "",mask,broadcastAddress;
    private int maxTransferRate,c,defaultTime,percentage,maximum;
    private double currentTransferRate,currentUsedBandwidth,packetErrorRate;

    public Wired(String name, int time, int c, int maximum, int percent) {             // o constructor tou thread
        this.defaultTime = time;
        this.c = c;
        this.maximum = maximum;
        this.interfaceName = name;
        this.percentage = percent;
        setMacAddress();
        setIpAddress();
        setBroadcastAddress();
        setMask();
        setDefaultGateway();
        setCurrentBandwidthUsage();
        setNetAddress(ipAddress, mask);
        setCurrentTransferRate();
        setPacketErrorRate();
        setMaxTransferRate();
    }

    public Wired(Wired copy){                   //copy constructor
        this.macAddress = new String(copy.macAddress);
        this.ipAddress = new String(copy.ipAddress);
        this.broadcastAddress = new String(copy.broadcastAddress);
        this.mask = new String(copy.mask);
        this.defaultGateway = new String(copy.defaultGateway);
        this.currentUsedBandwidth = copy.currentUsedBandwidth;
        this.netAddress = new String(copy.netAddress);
        this.currentTransferRate = copy.currentTransferRate;
        this.maxTransferRate = copy.maxTransferRate;
        this.packetErrorRate = copy.packetErrorRate;
    }

    @Override public void run(){
        int k = maximum;
        int i = maximum;
        int counter = 0;
        while(!Thread.currentThread().isInterrupted()){
            Wired temp = new Wired(this);               //dimiourgeitai eikona tou trexon wired gia na sigkrithei kai na paratirisoume tis allages
            long startTime = System.currentTimeMillis();
            this.setMacAddress();                   //apothikeuontai ek neou oi times tou wired
            this.setIpAddress();
            this.setBroadcastAddress();
            this.setMask();
            this.setDefaultGateway();
            this.setCurrentBandwidthUsage();
            this.setNetAddress(ipAddress, mask);
            this.setCurrentTransferRate();
            this.setPacketErrorRate();
            this.setMaxTransferRate();
            if(this.equals(temp)){                  //elegxetai me tin eikona tou ena ipirksan allages
                System.out.println("den paratirithike allagi sta dedomena tou wired interface");
                if(((counter++ == c) && (i > 1)) || (i > 1)){             //metraei poses anepitixeis epanalipseis exoun ginei
                    i--;                                        //ean exoun toses anepitixeis epanalipseis oses i parametros c, kai i metavliti i einai megaliteri i isi tou miden
                }                                       //tote metapiptei stin epomeni katastasi   
                else{
                    i = 1;
                }
            }
            else{
                System.out.println("Paratirithike Allagi sta wired");
                i = maximum;
                counter = 0;
            }
            System.out.println("Monitoring interface:" + interfaceName);
            long stopTime = System.currentTimeMillis();
            Athroistis athroistis = new Athroistis();  
            Thread t = new Thread(athroistis);
            t.start();
            long DT = stopTime - startTime;
            try{
                System.out.println("Wired Sleeping for: " + (k - i + 1) + " times the SleepTime ");
                Thread.sleep(((k - i + 1)*defaultTime) - DT);
            }catch(InterruptedException e){
                System.out.println("Termatismos thread");
            }
        }
    }

    @Override public String toString(){
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        result.append(this.getClass().getName() + newLine);
        result.append("Name:" + this.interfaceName + newLine);
        result.append("I dieuthinsi IP einai: " + this.ipAddress + newLine);
        result.append("I Broadcast Address einai: " + this.broadcastAddress + newLine);
        result.append("I maska Diktiou einai: " + this.mask + newLine);
        result.append("I Dieuthinsi Diktiou einai: " + this.netAddress + newLine);
        result.append("I mac Address einai: " + this.macAddress + newLine);
        result.append("To default Gateway einai: " + this.defaultGateway + newLine);
        result.append("To packet error rate einai: " + this.packetErrorRate + newLine);
        result.append("O megistos rithmos metadosis einai: " + this.maxTransferRate + " kbps" + newLine);
        String s = String.format("O trexon rithmos metadosis einai: %1$.2f kbps", this.currentTransferRate);
        String s1 = String.format("To katanaliskomeno euros zwnis einai: %1$.2f %%", this.currentUsedBandwidth);
        result.append(s + newLine);
        result.append(s1 + newLine);
        result.append(newLine);
        return result.toString();
    }
    

    public boolean equals(Wired temp){
            return (this.macAddress.equals(temp.macAddress) &&
                this.ipAddress.equals(temp.ipAddress) &&
                this.defaultGateway.equals(temp.defaultGateway) &&
                this.netAddress.equals(temp.netAddress) &&
                ((this.currentUsedBandwidth < percentage*temp.currentUsedBandwidth) || (this.currentUsedBandwidth == percentage*temp.currentUsedBandwidth)) &&
                ((this.packetErrorRate < percentage*temp.packetErrorRate) || (this.packetErrorRate == percentage*temp.packetErrorRate)));
        }
    

    public void setMacAddress(){
        String[] command = {"/bin/sh", "-c", "ifconfig " + interfaceName + "| awk '{if ($4 == \"HWaddr\") print $5}'"};
        String temp = parsing(command);
        if(temp == null){
            this.macAddress = "No Mac Address";
        }
        else{
            this.macAddress = temp.replace("inet addr:", "");  //apokoptei tous xaraktires stin arxi stou string gia na meinei i kathari mac address
        }
    }
    
    public void setIpAddress(){
            String[] comm = {"/bin/sh", "-c", "ifconfig " + interfaceName + "| grep -oP 'inet addr:[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}'"};
            String temp = parsing(comm);
            if(temp == null){
                this.ipAddress = "No Ip Address";
            }
            else{
                this.ipAddress = temp.replace("inet addr:", "");  //apokoptei tous xaraktires stin arxi stou string gia na meinei i kathari ip address
            }
    }

    public void setBroadcastAddress(){
            String[] command = {"/bin/sh", "-c", "ifconfig " + interfaceName + "| grep -oP 'Bcast:[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}'"};            
            String temp = parsing(command);
            if(temp == null){
                this.broadcastAddress = "No Broadcast Address";
            }
            else{
                this.broadcastAddress = temp.replace("Bcast:","");
            }
    }

    public void setMask(){
            String[] command = {"/bin/sh", "-c", "ifconfig " + interfaceName + "| grep -oP 'Mask:[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}'"};
            String temp = parsing(command);
            if(temp == null){
                this.mask = "No Mask Address";
            }
            else{
                this.mask = temp.replace("Mask:","");
            }
    }

    public void setMaxTransferRate(){  //prin ektelestei to programma ekteloume tin entoli sudo -s sto terminal gia na mporoun na trexoun oi entoles pou xreiazontai admin privilages
            String[] command = {"/bin/sh", "-c", "sudo ethtool " + interfaceName + " | awk '{if ( $1 == \"Speed:\") print $2}'"};
            String temp = parsing(command);
            if (temp == null){
                this.maxTransferRate = 0; //paradoxi: an einai virtual interface kai den exei max transfer rate thetw ti timi me miden
            }
            else{
                temp = temp.replace("Mb/s", "");
                this.maxTransferRate = ((Integer.parseInt(temp)) * 1000); //metatropi se kbps
            }
    }

    public void setDefaultGateway(){
        String[] command = {"/bin/sh", "-c", "route -n | awk '{if ( $1 == \"0.0.0.0\") print $2}'"}; //me to awk edw leme ean to prwto orisma einai 0.0.0.0 dil to default gateway,
        this.defaultGateway = parsing(command);   
    }

    public void setPacketErrorRate(){
        try{
            long startTime = System.currentTimeMillis();
            //String[] command = {"/bin/sh", "-c", "cat /proc/net/dev | grep " + interfaceName + " | sed 's/.*:\\(.*\\)/\\1/g' | awk '{print $2 \" \" $3 \" \" $10 \" \" $11}'"}; 
            String[] command = {"/bin/sh", "-c", "cat /proc/net/dev | grep " + interfaceName + " | sed 's/.*:\\(.*\\)/\\1/g' | awk '{print $3 \" \" $11}'"}; 
            String temp = parsing(command);
            String[] info = temp.split(" ");   
            //int RX = Integer.parseInt(info[0]);
            int RXerrors = Integer.parseInt(info[0]);
            //int TX = Integer.parseInt(info[2]);
            int TXerrors = Integer.parseInt(info[1]);
            int firstCount = RXerrors + TXerrors;
            Thread.sleep(2000);
            String[] command1 = {"/bin/sh", "-c", "cat /proc/net/dev | grep " + interfaceName + " | sed 's/.*:\\(.*\\)/\\1/g' | awk '{print $3 \" \" $11}'"}; 
            String temp1 = parsing(command1);
            String[] info1 = temp1.split(" ");
            int RXerrors1 = Integer.parseInt(info1[0]);
            int TXerrors1 = Integer.parseInt(info1[1]);
            int secondCount = RXerrors1 + TXerrors1;                   
            long stopTime = System.currentTimeMillis();
            long Dt = stopTime - startTime;
            this.packetErrorRate = (secondCount - firstCount)/Dt;
        }catch(InterruptedException e){e.printStackTrace();}
    }

    public void setNetAddress(String ip_add, String mask_addr){ //ypologismos tis dieuthinsis diktiou
        if(ip_add.equalsIgnoreCase("No Ip Address")){
            this.netAddress = "No Network Address";
        }
        else{
        String[] ipAddrParts = ip_add.split("\\.");  //xwrizei tis teleies apo tin ip address
        String[] maskParts = mask_addr.split("\\."); //xwrizei tis teleies apo tin mask
        this.netAddress = "";                  //midenismos tou net address
        for(int i=0;i<4;i++){
            int x = Integer.parseInt(ipAddrParts[i]); //metatrepoume se akeraious ta tessera pedia tis ip kai mask address pou xwrisame sto proigoumeno vima
            int y = Integer.parseInt(maskParts[i]);
            int z = x&y;  //i praksi AND me ta binary parts tis ip kai mask address paragei tin network address
            this.netAddress += z + ".";
            }
        }
    }

    public void setCurrentBandwidthUsage(){
        if(this.maxTransferRate == 0){   //ean to interface den exei megisto rithmo metadosis (einai virtual) thetw ti timi isi me miden
            this.currentUsedBandwidth = 0;
        }
        else{
            this.currentUsedBandwidth = (this.currentTransferRate / this.maxTransferRate);  //katanaliskomeno euros zwnis = trexon rithmos/megistos rithmos
        }
    }

    public void setCurrentTransferRate(){
        try{
            long startTime = System.currentTimeMillis();         //arxikos xronos
            String[] command = {"/bin/sh", "-c", "ifconfig " + interfaceName + " | grep -oP 'RX bytes:[0-9]{1,10}'"};
            String[] command1 = {"/bin/sh", "-c", "ifconfig " + interfaceName + " | grep -oP 'TX bytes:[0-9]{1,10}'"};
            String temp = parsing(command);                      //oi prwtes metriseis twn RX kai TX bytes
            temp = temp.replace("RX bytes:","");                 // afairoume to tmima 'RX bytes'
            String temp1 = parsing(command1);
            temp1 = temp1.replace("TX bytes:","");               //afairoume to tmima 'TX bytes'
            long x = Long.parseLong(temp);                       //metatrepoume ta RX byte apo string se int
            long y = Long.parseLong(temp1);                      //metatrepoume ta TX byte apo string se int
            Thread.sleep(2000);                                  //kathisteroume 1 second tin ektelesi gia pio akrivi apotelesmata
            String[] command2 = {"/bin/sh", "-c", "ifconfig " + interfaceName + " | grep -oP 'RX bytes:[0-9]{1,11}'"};
            String[] command3 = {"/bin/sh", "-c", "ifconfig " + interfaceName + " | grep -oP 'TX bytes:[0-9]{1,11}'"};
            String temp2 = parsing(command2);                    //oi deuteres metriseis RX kai TX bytes
            temp2 = temp2.replace("RX bytes:","");
            String temp3 = parsing(command3);
            temp3 = temp3.replace("TX bytes:","");
            long stopTime = System.currentTimeMillis();          // o xronos pou perase mexri ti deuteri metrisi
            long z = Long.parseLong(temp2);                      //metatrepoume ta RX byte apo string se int
            long h = Long.parseLong(temp3);
            double Dt = (double)(stopTime - startTime);          //casting se double
            Dt = Dt/1000;                                        //metatropi apo milliseconds se seconds
            double test = ((double)((z+h)-(x+y)))/1024;          // ipologismos tou trexon rithmou metadosis ginetai ws eksis: metrame 2 fores ta RX kai TX bytes,
            this.currentTransferRate = test/Dt;                  // vriskoume ti diafora tous,tin diairoume me 1024 gia tin metatropi se kbps, kai meta diairoume me to xrono pou mesolavise anamesa stis 2 metriseis
        }catch(InterruptedException e){e.printStackTrace();}
    }

    public String getInterfaceName(){
        return interfaceName;
    }

    public int getMaxChainLinks(){
        return maximum;
    }

    public int getC(){
        return c;
    }

    public int getDefaultTime(){
        return defaultTime;
    }
    
    public String getMacAddress(){
        return macAddress;
    }
    
    public String getIpAddress(){
        return ipAddress;
    }    
    
    public String getBroadcastAddress(){
        return broadcastAddress;
    }
    
    public String getMask(){
        return mask;
    }
    
    public String getDefaultGateway(){
        return defaultGateway;
    }
        
    public double getCurrentUsedBandwidth(){
        return currentUsedBandwidth;
    }
    
    public String getNetAddress(){
        return netAddress;
    }
        
    public double getCurrentTransferRate(){
        return currentTransferRate;
    }
    
    public double getPacketErrorRate(){
        return packetErrorRate;
    }
    
    public int getMaxTransferRate(){
        return maxTransferRate;
    }

    public static String parsing(String[] command){    // sinartisi pou ektelei entoles sto termatiko. tin entoli tin pername san parametro
        String output = null;
        try{                                    //xrisimopoieitai gia apofigi dublicate code
            Process child = Runtime.getRuntime().exec(command);
            BufferedReader r = new BufferedReader(new InputStreamReader(child.getInputStream()));
            output = r.readLine();             //kai epistrefei to output.
            r.close();
        }catch(IOException e){e.printStackTrace();}
    return output;
    }
}