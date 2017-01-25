/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMyObserver;

import databufferv3.Measurement;
import databufferv3.QueueIdentifier;
import de.hsulm.cermit.sensorunitmodel.Channel;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author mojib
 */
public interface IFeatureObserver {
    public abstract void update(HashMap<QueueIdentifier, Queue<Measurement>> messageMap);
    public abstract List<QueueIdentifier> getQIdList();
    public abstract double getOverlabFactor();
    public abstract int getDeltaTimeStamp();
    public abstract Channel getChannel();
    //public abstract void calc();

    
}