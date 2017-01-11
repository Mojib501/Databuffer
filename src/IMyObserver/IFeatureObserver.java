/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMyObserver;

import databufferv3.Measurement;
import databufferv3.QueueIdentifier;
import java.util.List;

/**
 *
 * @author mojib
 */
public interface IFeatureObserver {
    
    public abstract void update(List<Measurement> measureList);
}
