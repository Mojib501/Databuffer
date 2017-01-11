/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMyObserver;

import databufferv3.DataBufferV3;
import databufferv3.Measurement;
import java.util.List;

/**
 *
 * @author mojib
 */
public abstract class FeatureObserver {
    public Feature feature;
    public DataBufferV3 buffer;
    public abstract void update(List<Measurement> measureList);
        
    
    
}
