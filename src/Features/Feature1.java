/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Features;
import IMyObserver.IFeatureObserver;
import databufferv3.*;
import de.hsulm.cermit.sensorunitmodel.Channel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mojib
 */
public class Feature1 implements IFeatureObserver{
    private QueueIdentifier qId;
    private int deltaTimeStamp;
    private Channel channel;
    private List<Measurement> measureList = new ArrayList<>();

    
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
