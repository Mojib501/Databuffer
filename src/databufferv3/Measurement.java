/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databufferv3;

import de.hsulm.cermit.messages.DataMessageSingle;

/**
 *
 * @author mojib
 */
public class Measurement {
    private DataMessageSingle singleMsg;
    private long timeStamp;
    private int mdID;
    
    
     public Measurement(DataMessageSingle singleMsg, long timeStamp, int mdID){
         this.singleMsg=singleMsg;
         this.timeStamp=timeStamp;
         this.mdID=mdID;
     }
    public long getTimeStamp(){
        return timeStamp;
    }
    public Integer[] getMetrics(){
        return singleMsg.getMetrics();
    }
    public String toString(){
        return null;
    }
}
