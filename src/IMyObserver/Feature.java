/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMyObserver;

import databufferv3.QueueIdentifier;
import de.hsulm.cermit.sensorunitmodel.Channel;

/**
 *
 * @author mojib
 */
public class Feature {
    private QueueIdentifier qId;
    private int deltaTimeStamp;
    private Channel channel;

    public Feature(QueueIdentifier qId, int deltaTimeStamp, Channel channel) {
        this.qId = qId;
        this.deltaTimeStamp = deltaTimeStamp;
        this.channel = channel;
    }
    
    
    
}
