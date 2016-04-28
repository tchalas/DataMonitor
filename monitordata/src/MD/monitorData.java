package MD;

import java.util.*;

public class monitorData {
    public Vector<Wired> wiredVector;
    public Vector<Wireless> wirelessVector;
    public Vector<AccessPoint> apVector;   
    

    monitorData(){
        wiredVector = new Vector<Wired>();
        wirelessVector = new Vector<Wireless>();
        apVector = new Vector<AccessPoint>();
    }
}
