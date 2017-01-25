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
        
        AppController ctrl = new AppController();
        ctrl.connectSensorUnit(new BluetoothDeviceDescriptor("0007806e65b0","UNIT-Rev3-ID-4"));
        Feature1 feature = new Feature1(ctrl.sensorUnit.getDeviceDescriptor(), ctrl.sensorUnit.getMeasurementDeviceClones().get(0).getID());
        ctrl.registerFeature(feature);
        //Thread.sleep(5000L);
        
        ctrl.startMeasuring();
    for(int i=0 ; i<=10 ; i++){
        feature.calc();
        //Thread.sleep(1000L);
    }
        ctrl.stopMeasuring();
        
    }
    
}
