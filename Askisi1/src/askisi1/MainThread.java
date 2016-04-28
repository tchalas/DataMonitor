package askisi1;

import java.io.*;
import java.util.*;


public class MainThread implements Runnable {
    private int interfaceNum = 0;
    public final int defaultSleepTime,c,max,percent;
    List<String> interfaceList = new ArrayList<String>();           //i lista me ola ta interfaces
    List<String> tempInterfaceList = new ArrayList<String>();       //eikona tis listas me ta interfaces
    static HashMap<String, Thread> wiredMap = new HashMap();               //hashmap me ta wired interfaces
    static HashMap<String, Thread> wirelessMap = new HashMap();            //hashmap me ta wireless interfaces
    HashMap<String, Thread> tempWired = new HashMap();
    HashMap<String, Thread> tempWireless = new HashMap();
    static Vector<Wired> WiredVector = new Vector<Wired>();
    static Vector<Wireless> WirelessVector = new Vector<Wireless>();

    public MainThread(int sleeptime, int c, int max, int percent) {
        this.defaultSleepTime = sleeptime;          //o default sleep time pou pernoume apo to property file
        this.c = c;         
        this.max = max;             //arithmos katastasewn tis alisidas k
        this.percent = percent;     //posostiaia metavoli X
    }

    public void run() {
        String temp,temp1 = "";
        int k = max;                                                      //arxiki katastasi
        int i = max;
        try {
            String[] command = {"/bin/sh", "-c","cat /proc/net/dev | awk 'NR>2{print $1}'"};            //diavazoume ti lista me ta onomata olwn twn interfaces, afairoume tis 2 prwtes grammes tou output.
            String[] command1 = {"/bin/sh", "-c","cat /proc/net/wireless | awk 'NR>2{print $1}'"};
            Process child = Runtime.getRuntime().exec(command);
            Process child1 = Runtime.getRuntime().exec(command1);
            BufferedReader r = new BufferedReader(new InputStreamReader(child.getInputStream()));
            BufferedReader r1 = new BufferedReader(new InputStreamReader(child1.getInputStream()));
            while((temp = r.readLine()) != null){
                temp = temp.split(":")[0];                  //afairoume apo to string pou pirame olous tous xaraktires meta kai simperilamvanomenou tou xaraktira ':'
                tempInterfaceList.add(temp);
            }
            r.close();
            if(tempInterfaceList.isEmpty()){                    //ean den exoume katholou interfaces
                System.out.println("No Interfaces!");       //tipose katallilo minima
            }
            else{
                while((temp1 = r1.readLine()) != null ){        //diavazoume ta onomata twn wireless interfaces
                    temp1 = temp1.split(":")[0];
                    Wireless wirelessInterface  = new Wireless(temp1,defaultSleepTime,c,max,percent);
                    Thread t1 = new Thread(wirelessInterface);      //apothikeuoume sto map mas ta wireless interfaces me key to onoma tou wireless
                    tempWireless.put(temp1,t1);                         //kai value to thread tou ekastote wireless

                }
                r1.close();
                if (tempWireless.isEmpty()){                        //an den iparxoun wireless interfaces sto map
                    for(String s : interfaceList){              //topothetoume ola ta interfaces sto wired map (exoume idi elegksei ti pithanotita na min iparxoun katholou interfaces
                        Wired wiredInterface = new Wired(s,defaultSleepTime,c,max,percent);
                        Thread t1 = new Thread(wiredInterface);
                        tempWired.put(s,t1);        //ara sti periptwsi pou den iparxoun wireless, auta pou iparxoun stin interface list einai profanws wired kai mpenoun sti lista tus

                    }
                }
                else{
                    for(String s : tempInterfaceList){                      //to diplo for edw ekteleitai gia na afairesoume ta onomata twn wireless apo tin interface list
                        boolean flag = false;                           //gia na exoume 2 ksexwristous hash maps me ta wired kai wireless threads. Ekteleitai sti
                        for(String key : tempWireless.keySet()){            //periptwsi pou exoume kai wired kai wireless interfaces
                            if(s.equals(key)){
                                flag = true;
                                break;
                            }
                        }
                        if(!flag){
                            Wired wiredInterface = new Wired(s,defaultSleepTime,c,max,percent);
                            Thread t1 = new Thread(wiredInterface);
                            tempWired.put(s,t1);
                        }
                    }
                }
            }
        }catch (IOException e) {e.printStackTrace();}
        int counter = 0;
        while(!Thread.currentThread().isInterrupted()){
            long startTime = System.currentTimeMillis();        //xronos pou arxise i metrisi
            try {
                interfaceList.clear();
                wiredMap.clear();
                wirelessMap.clear();
                WiredVector.clear();
                WirelessVector.clear();
                String[] command = {"/bin/sh", "-c","cat /proc/net/dev | awk 'NR>2{print $1}'"};            //diavazoume ti lista me ta onomata olwn twn interfaces, 
                String[] command1 = {"/bin/sh", "-c","cat /proc/net/wireless | awk 'NR>2{print $1}'"};      //afairoume tis 2 prwtes grammes tou output.
                Process child = Runtime.getRuntime().exec(command);
                Process child1 = Runtime.getRuntime().exec(command1);
                BufferedReader r = new BufferedReader(new InputStreamReader(child.getInputStream()));
                BufferedReader r1 = new BufferedReader(new InputStreamReader(child1.getInputStream()));
                while((temp = r.readLine()) != null){
                    temp = temp.split(":")[0];                  //afairoume apo to string pou pirame olous tous xaraktires meta kai simperilamvanomenou tou xaraktira ':'
                    if(!interfaceList.contains(temp)){           //an to onoma tou interface den iparxei idi sti lista
                        interfaceList.add(temp);                //to prosthetoume
                    }
                }
                r.close();
                if(interfaceList.isEmpty()){                    //ean den exoume katholou interfaces
                    System.out.println("No Interfaces!");       //tipose katallilo minima
                }
                else{
                    while((temp1 = r1.readLine()) != null ){        //diavazoume ta onomata twn wireless interfaces
                        temp1 = temp1.split(":")[0];
                        if(!tempWireless.containsKey(temp1)){           //ean to wireless pou diavasame den periexetai idi sto map, to prosthetei. alliws to afinoume ws exei
                            Wireless wirelessInterface  = new Wireless(temp1,defaultSleepTime,c,max,percent);
                            Thread t1 = new Thread(wirelessInterface);      //apothikeuoume sto map mas ta wireless interfaces me key to onoma tou wireless
                            wirelessMap.put(temp1,t1);                         //kai value to thread tou ekastote wireless
                            WirelessVector.add(wirelessInterface);
                        }
                        else{
                            Wireless wirelessInterface  = new Wireless(temp1,defaultSleepTime,c,max,percent);
                            WirelessVector.add(wirelessInterface);
                            Thread t = tempWireless.get(temp1);
                            wirelessMap.put(temp1, t);
                        }
                    }
                    r1.close();
                    if (wirelessMap.isEmpty()){                        //an den iparxoun wireless interfaces sto map
                        for(String s : interfaceList){              //topothetoume ola ta interfaces sto wired map (exoume idi elegksei ti pithanotita na min iparxoun katholou interfaces
                            if(!tempWired.containsKey(s)){
                                Wired wiredInterface = new Wired(s,defaultSleepTime,c,max,percent);
                                WiredVector.addElement(wiredInterface);
                                Thread t1 = new Thread(wiredInterface);
                                wiredMap.put(s,t1);          //ara sti periptwsi pou den iparxoun wireless, auta pou iparxoun stin interface list einai profanws wired kai mpenoun sti lista tus
                            }
                            else{
                                Thread t = tempWired.get(s);
                                wiredMap.put(s, t);
                            }
                        }
                    }
                    else{
                        for(String s : interfaceList){                      //to diplo for edw ekteleitai gia na afairesoume ta onomata twn wireless apo tin interface list
                            boolean flag = false;                           //gia na exoume 2 ksexwristous hash maps me ta wired kai wireless threads. Ekteleitai sti
                            for(String key : wirelessMap.keySet()){            //periptwsi pou exoume kai wired kai wireless interfaces
                                if(s.equals(key)){
                                    flag = true;
                                    break;
                                }
                            }
                            if((!flag) && (!wiredMap.containsKey(s))){
                                Wired wiredInterface = new Wired(s,defaultSleepTime,c,max,percent);
                                WiredVector.addElement(wiredInterface);
                                Thread t1 = new Thread(wiredInterface);
                                wiredMap.put(s,t1);
                            }
                        }
                    }
                }
                if(interfaceList.size() == tempInterfaceList.size()){   
                    System.out.println("Den paratirithike allagi");
                    if(((counter++ == c) && (i > 1)) || (i > 1)){    //metraei poses anepitixeis epanalipseis exoun ginei
                        i--;                                                 //ean exoun toses anepitixeis epanalipseis oses i parametros c,
                    }                                               //tote metapiptei stin epomeni katastasi kai arxikopoiei ton counter
                    else{
                        i = max;
                    }
                }
                else{
                    System.out.println("Paratirithike Allagi sta interfaces");
                    i = max;                      //epistrofi stin arxiki katastasi afou paratirithike epitixia
                    counter = 0;            //midenismos tou counter gia tis anepitixeis epanalipseis
                    if(wiredMap.size() > tempWired.size()){                                //prwta vlepoume gia allages sta wired Interfaces
                        System.out.println("Emfanistike kainourgio Wired Interfaces");
                        for(Map.Entry<String, Thread> map : wiredMap.entrySet())
                            if(!(tempWired.containsKey(map.getKey()))){                 //ean i eikona tou wired map den periexei kapoio key apo ton kainourgio wired map
                                wiredMap.get(map.getKey()).start();    //simainei emfanistike kainourgio wired Interface kai prepei na ksekinisoume to thread tu
                            }
                    }
                    else if(wiredMap.size() < tempWired.size()){
                        System.out.println("Aposindethike kapoio Wired Interfaces");     
                        for(Map.Entry<String, Thread> map : tempWired.entrySet())       //ean o kainourgios wired map den periexei kapoio key apo tin eikona tou wired map
                            if(!(wiredMap.containsKey(map.getKey()))){                     //aposindethike kapoio wired interface kai prepei na stamatisoume to thread tu
                                tempWired.get(map.getKey()).interrupt();                   //stamatame to thread tou wired pou den iparxei pleon
                            }
                    }
                    else if(wirelessMap.size() > tempWireless.size()){                                 //an den iparxoun allages sta wired, elegxoume gia ta wireless interfaces
                        System.out.println("Emfanistike kainourgio Wireless Interfaces");
                        for(Map.Entry<String, Thread> map : wirelessMap.entrySet())
                            if(!(tempWireless.containsKey(map.getKey()))){                          //ean i eikona tou wireless map den periexei kapoio key apo ton kainourgio wired map
                                wirelessMap.get(map.getKey()).start();                                 //simainei emfanistike kainourgio wireless Interface kai prepei na ksekinisoume to thread tu                                                                                                  
                            }
                    }
                    else if(wirelessMap.size() < tempWireless.size()){
                        System.out.println("Aposindethike kapoio Wireless Interfaces");
                        for(Map.Entry<String, Thread> map : tempWireless.entrySet())        //ean o kainourgios wireless map den periexei kapoio key apo tin eikona tou wired map
                            if(!(wirelessMap.containsKey(map.getKey()))){                      //aposindethike kapoio wireless interface kai prepei na stamatisoume to thread tu
                                Thread t = tempWireless.get(map.getKey());                 //stamatame to thread tou wireless pou den iparxei pleon
                                tempWireless.get(map.getKey()).interrupt();
                            }
                    }
                }
                tempInterfaceList.clear();                //adiazoume tin proswrini lista me ta onomata twn interfaces
                for(String s : interfaceList){            //apothikeuoume tin eikona tou interface list se ena temp
                    tempInterfaceList.add(s);             //to opoio tha sigkrinoume me tin epomeni eikona tou interface list
                }                                         //gia na kseroume ean ipirksan allages ston arithmo twn interfaces
                if(wiredMap.isEmpty()){                                //Ekkinisi twn threads
                    System.out.println("No Wired Interfaces");
                }
                else{
                    for(Thread t : wiredMap.values()){
                        if(!t.isAlive()){               //ean to trexon thread den trexei idi..tote kai mono tote to ksekina
                            t.start();
                        }
                    }
                }
                if(wirelessMap.isEmpty()){
                    System.out.println("No Wireless Interfaces");
                }
                else{
                    for(Thread t : wirelessMap.values()){
                        if(!t.isAlive()){
                            t.start();                   //ean to trexon thread den trexei idi..tote kai mono tote to ksekina
                        }
                    }
                }
                tempWired.clear();                          //midenismos twn proswrinwn maps
                for(Map.Entry<String, Thread> map : wiredMap.entrySet()){  //kai epanarxikopoiisi tus me tin trexon eikona twn kanonikwn listwn
                    tempWired.put(map.getKey(),map.getValue());
                }
                tempWireless.clear();
                for(Map.Entry<String, Thread> map : wirelessMap.entrySet()){
                    tempWireless.put(map.getKey(),map.getValue());
                }
                long stopTime = System.currentTimeMillis();                 //xronos pou stamatise i metrisi
                long DT = stopTime - startTime;                             //xronos pou diirkise i metrisi
                System.out.println("MainThread Sleeping for: " + (k - i + 1) + " times the SleepTime");
                Thread.sleep((k - i + 1)*defaultSleepTime - DT);                        //xronos pou tha koimithei to thread (k-katastasi * sleeptime - DT)
        } catch (IOException e) {e.printStackTrace();}
        catch (InterruptedException e){e.printStackTrace();}
        }
    }

    public int getInterfaceNum(){
        return interfaceNum;
    }

    public static void main(String[] args) {
        Properties prop = new Properties();
        int defaultTime = 0, c = 0, max = 0, percent = 0;
        try{
            prop.load(new FileInputStream("config.properties"));
            defaultTime = Integer.parseInt(prop.getProperty("StandardSleepTime"));
            c = Integer.parseInt(prop.getProperty("c"));
            max = Integer.parseInt(prop.getProperty("maximum"));          //arithmos katastasewn tis alisidas k
            percent = Integer.parseInt(prop.getProperty("percentage"));   //posostiaia metavoli
        }catch(IOException e){e.printStackTrace();}
        MainThread primaryThread = new MainThread(defaultTime,c,max,percent);
        Thread t1 = new Thread(primaryThread);
        t1.start();
        final Thread t1_2 = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override public void run(){
                for(Thread t : wiredMap.values()){
                    t.interrupt();
                }
                for(Thread t : wirelessMap.values()){
                    t.interrupt();
                }
                t1_2.interrupt();
                System.gc();
                System.out.println("To programma termatistike epitixws");
            }
        });
    }
}