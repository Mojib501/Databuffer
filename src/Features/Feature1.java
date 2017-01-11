/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Features;
import IMyObserver.Feature;
import IMyObserver.FeatureObserver;
import IMyObserver.IFeatureObserver;
import databufferv3.*;
import de.hsulm.cermit.sensorunitmodel.Channel;
import java.util.List;

/**
 *
 * @author mojib
 */
public class Feature1 extends FeatureObserver {
    FeatureObserver observer;
    public Feature1(FeatureObserver observer) {
        this.observer = observer;
        //feature muss sich beim buffer registrieren
    }
    
    @Override 
        public void update(List<Measurement> measureList){
         }
    
}
