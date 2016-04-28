package TerminalData;

public class TerminalData {
    private String id;
    private long deviceTimeAddedUpdated; 
            
    public TerminalData(String id){
        this.id = id;
    }
    
    public void setId(String id){
            this.id=id;
    }

    public String getId(){
            return this.id;
    }
    
    public void setTimeStamp(long time){
        this.deviceTimeAddedUpdated = time;
    }
    
    public long getTimeAdded(){
        return deviceTimeAddedUpdated;
    }
}
