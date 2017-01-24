/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Features;
import IMyObserver.IFeatureObserver;
import databufferv3.*;
import de.hsulm.cermit.exchangableinterface.IDeviceDescriptor;
import de.hsulm.cermit.implementations.BluetoothDeviceDescriptor;
import de.hsulm.cermit.sensorunitmodel.Channel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author mojib
 */
public class Feature1 implements IFeatureObserver{
  
    private int deltaTimeStamp;
    private Channel channel;
    private List<QueueIdentifier> qId;
    private double overlabFactor;    
    private HashMap<QueueIdentifier, Queue<Measurement>> messageMap;
    private Queue<Measurement> queue;
    private QueueIdentifier queueIdentifier;
    private Iterator<Measurement> iter;
    
    
    public Feature1() {
    qId = new LinkedList<>();
    queueIdentifier = new QueueIdentifier(new BluetoothDeviceDescriptor("0007806e65b0","UNIT-Rev3-ID-4"), 2);
    qId.add(queueIdentifier);
    deltaTimeStamp = 1;
    overlabFactor = 1;
    
    }
//    
//    
//    
//    
    @Override
    public void calc() {
     queue = messageMap.get(queueIdentifier);
        iter = queue.iterator();
        while (iter.hasNext()) {
            System.out.println("Data Message is : " + iter.next().getMetrics());
        }
            
    }
    @Override
    public double getOverlabFactor(){
        return overlabFactor;
    }
    @Override
    public void update(HashMap<QueueIdentifier, Queue<Measurement>> messageMap) {
        this.messageMap = messageMap;
    }
    @Override
    public List<QueueIdentifier> getQIdList() {
        
        return this.qId;
    }
    @Override
    public int getDeltaTimeStamp() {
        return this.deltaTimeStamp;
    }
    @Override
    public Channel getChannel() {
        return this.channel;
    }
    
}
