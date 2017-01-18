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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mojib
 */
public class Feature1 implements IFeatureObserver{
    private int deltaTimeStamp = 1;
    private Channel channel;
    IDeviceDescriptor devDesc = new BluetoothDeviceDescriptor("0007806e65b0","UNIT-Rev3-ID-4");
    private List<QueueIdentifier> qId;
    
    
    private List<Measurement> measureList = new LinkedList<>();

//    
//    
//    
//    
//  
    public void calc() {
       System.out.println(measureList);
       measureList.removeAll(measureList);
    }
    @Override
    public double getOverlabFactor(){
        return 0;
    }
    @Override
    public void update(List<Measurement> measureList) {
        this.measureList = measureList;
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
