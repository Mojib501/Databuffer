/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppController;

import Features.Feature1;
import IMyObserver.IFeatureObserver;
import IMyObserver.Subject;
import databufferv3.DataBuffer;
import de.hsulm.cermit.commandinterface.CermitException;
import de.hsulm.cermit.commandinterface.Controller;
import de.hsulm.cermit.commandinterface.MeasurementType;
import de.hsulm.cermit.commandinterface.SensorUnit;
import de.hsulm.cermit.datastorage.DataStorer;
import de.hsulm.cermit.eventinterface.ICtrlObserver;
import de.hsulm.cermit.eventinterface.IDataObserver;
import de.hsulm.cermit.eventinterface.IDeviceSearchCallback;
import de.hsulm.cermit.eventinterface.IErrorCallback;
import de.hsulm.cermit.eventinterface.IStateChangedCallback;
import de.hsulm.cermit.exchangableinterface.IDeviceDescriptor;
import de.hsulm.cermit.implementations.BluetoothDeviceDescriptor;
import de.hsulm.cermit.implementations.DataToCsvFile;
import de.hsulm.cermit.implementations.ProtocolConstants;
import de.hsulm.cermit.messages.DataMessage;
import de.hsulm.cermit.messages.ResponseMessage;
import de.hsulm.cermit.sensorunitmodel.MeasurementDevice;
import de.hsulm.cermit.sensorunitmodel.StateSensorUnit;
import de.hsulm.cermitextensions.implementations.javaxbluetooth.BluetoothDeviceSearcher;
import de.hsulm.cermitextensions.implementations.javaxbluetooth.DefaultSensorUnitFactory;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mojib
 */
public class AppController implements IStateChangedCallback{
    
    Controller cermitController = null;
    DataBuffer dataBuffer ;
    
    public AppController(){
        cermitController = Controller.getInstance();
        // instantiate callbacks as anonymous class implementations
        IErrorCallback errorCallback = new IErrorCallback() {
            public void handleException(CermitException cermitException) {
                System.out.println("Error: " + cermitException.toString());
            }

            public void handleResponseTimeout(SensorUnit sensorUnit, int messageTypeID, byte[] msg) {
                System.out.println("Response Timeout for message id "+messageTypeID+" from SU: "+sensorUnit);
            }

            public void handleResponseError(SensorUnit sensorUnit, ResponseMessage responseMsg) {
                System.out.println("Response error: "+responseMsg +" from SU: "+sensorUnit);
            }
        };
        IDeviceSearchCallback devSearchCallback = new IDeviceSearchCallback() {
            public void receive(List<IDeviceDescriptor> deviceDescriptors) {
         
            }
        };
        
        
        //TODO: configure for app
        String dataPath = "c:/data";
        
    // Create list of data storer classes
        List<Class<? extends DataStorer>> dataStorerCls = new LinkedList<Class<? extends DataStorer>>();
        // add DataToCsvFile for storing in csv files
        dataStorerCls.add(DataToCsvFile.class);
        
        
        
        // setup the controller
        cermitController.setUp(BluetoothDeviceSearcher.class, dataStorerCls, dataPath, errorCallback, devSearchCallback, new ProtocolConstants());
        
        
        //TODO: instanziieren des DataBuffers
        dataBuffer = new DataBuffer();
    }
    
    public SensorUnit sensorUnit;
    
    public void connectSensorUnit(IDeviceDescriptor devDesc){
        
        
        // create sensor unit factory (singleton pattern)
        DefaultSensorUnitFactory suFactory = DefaultSensorUnitFactory.getInstance();

        
        
//       
//        IDataObserver dataObs = new IDataObserver() {
//            public void update(DataMessage dataMessage) {
//                System.out.println("Received data message: "+ dataMessage);
//            }
//        };
        ICtrlObserver ctrlObs = new ICtrlObserver() {
            public void update(de.hsulm.cermit.messages.ControlMessage ctrlMessage) {
                System.out.println("Received ObjectMessage: " + ctrlMessage);
            }
        };
        
        
        
        // connect to the device...
        //TODO: registrieren des DataBuffers als DataObserver
        
        this.sensorUnit = cermitController.connect(devDesc, suFactory, this, ctrlObs, dataBuffer);

        if (this.sensorUnit!=null) {
            System.out.println("Connected.");
            // open connection
            this.sensorUnit.open();
        } else {
            System.out.println("Not connected.");
            return;
        }
    }
    
    
    public void startMeasuring(){
        List<SensorUnit> sensorUnits = cermitController.getSensorUnits();        
        cermitController.cmdStartMeasurement(sensorUnits, MeasurementType.ONLINE, 0);
        
        
        List<MeasurementDevice> mdList = sensorUnits.get(0).getMeasurementDeviceClones();
        MeasurementDevice md = mdList.get(0);
        
        //md.setSamplingRateHz(50);
        //sensorUnits.get(0).cmdWriteMeasurementDeviceCfg(md);
                
    }
    
    
    public void registerFeature(IFeatureObserver feature){
        //TODO: registriere Feature im data buffer. Dazu wird benötigt: Sensor ID (SU ID oder Device Descriptor) und die MD ID --> Queue Identifier
       // dataBuffer.register(feature);
       dataBuffer.register(feature);
    }
    
    
    @Override
    public void onStateChanged(StateSensorUnit oldState, StateSensorUnit newState, SensorUnit sensorUnit){
        System.out.println("State changed: " + oldState+" -> "+ newState);
        if(newState == StateSensorUnit.CONFIGURING){
            sensorUnit.cmdAcceptCfg(0);
        }
        
    }

    void stopMeasuring() {
        cermitController.cmdStopMeasurement();
    }
}
