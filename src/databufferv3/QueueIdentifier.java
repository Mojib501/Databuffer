/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databufferv3;

import de.hsulm.cermit.exchangableinterface.IDeviceDescriptor;

/**
 *
 * @author mojib
 */
public class QueueIdentifier {
    private IDeviceDescriptor desc;
    private int measurmentDeviceID;
    
    public QueueIdentifier(IDeviceDescriptor desc, int measurmentDeviceId){
        this.desc=desc;
        this.measurmentDeviceID=measurmentDeviceId;
    }
    public IDeviceDescriptor getDesc(){
        return desc;
    }
    public int getMDid(){
        return measurmentDeviceID;
    }
    public String toString(){
        return null;
    }
    
    
}
