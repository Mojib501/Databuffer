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

    private QueueIdentifier qId = new QueueIdentifier(devDesc, 0);
    
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
    public void update(List<Measurement> measureList) {
        this.measureList = measureList;
    }
    @Override
    public QueueIdentifier get_qId() {
       return this.qId;
    }
    @Override
    public int get_deltaTimeStamp() {
        return this.deltaTimeStamp;
    }
    @Override
    public Channel get_channel() {
        return this.channel;
    }
    
}
