package AppController;

import Features.Feature1;
import IMyObserver.IFeatureObserver;
import de.hsulm.cermit.implementations.BluetoothDeviceDescriptor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mojib
 */
public class Main {
    
    public static void main(String[] args) throws InterruptedException{
        Feature1 feature = new Feature1();
        AppController ctrl = new AppController();
        ctrl.registerFeature(feature);
        
        ctrl.connectSensorUnit(new BluetoothDeviceDescriptor("0007806e65b0","UNIT-Rev3-ID-4"));
        Thread.sleep(5000L);
        
        ctrl.startMeasuring();
        feature.calc();
        Thread.sleep(3000L);
        
        ctrl.stopMeasuring();
        
    }
    
}
