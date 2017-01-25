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
    private static IDeviceDescriptor desc;
    private static int mdId;
    private static QueueIdentifier queueIdentifier;
    
    public QueueIdentifier(IDeviceDescriptor desc, int mdId){
     this.mdId = mdId;
     this.desc = desc;
     }
    public static QueueIdentifier getInstanz(IDeviceDescriptor desc, int mdId){
        queueIdentifier = new QueueIdentifier(desc, mdId);
        return queueIdentifier;
    }
    public IDeviceDescriptor getDesc(){
        return this.desc;
    }
    public int getmDid(){
        return this.mdId;
    }
}
