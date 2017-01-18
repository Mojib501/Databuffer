///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package AppController;
//
//import Features.Feature1;
//import IMyObserver.IFeatureObserver;
//import IMyObserver.Subject;
//import databufferv3.DataBufferV3;
//import de.hsulm.cermit.commandinterface.CermitException;
//import de.hsulm.cermit.commandinterface.Controller;
//import de.hsulm.cermit.commandinterface.MeasurementType;
//import de.hsulm.cermit.commandinterface.SensorUnit;
//import de.hsulm.cermit.datastorage.DataStorer;
//import de.hsulm.cermit.eventinterface.ICtrlObserver;
//import de.hsulm.cermit.eventinterface.IDeviceSearchCallback;
//import de.hsulm.cermit.eventinterface.IErrorCallback;
//import de.hsulm.cermit.eventinterface.IStateChangedCallback;
//import de.hsulm.cermit.exchangableinterface.IDeviceDescriptor;
//import de.hsulm.cermit.exchangableinterface.IDeviceSearcher;
//import de.hsulm.cermit.implementations.BluetoothDeviceDescriptor;
//import de.hsulm.cermit.implementations.DataToCsvFile;
//import de.hsulm.cermit.implementations.ProtocolConstants;
//import de.hsulm.cermit.messages.ResponseMessage;
//import de.hsulm.cermit.sensorunitmodel.StateSensorUnit;
//import de.hsulm.cermitextensions.implementations.javaxbluetooth.BluetoothDeviceSearcher;
//import de.hsulm.cermitextensions.implementations.javaxbluetooth.DefaultSensorUnitFactory;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// *TODO  Mit CERMIT verbinden 
// *      im DataObserver registrieren 
// *      Features im Buffer registrieren
// *      Messdaten aufzeichenen/ ausgeben
// * @author mojib
// */
//public class AppController {
//        public static void main(String[] args) {
//        AppController appController = new AppController();
//        appController.startup();
//    }
//    
//    
//        public void startup() {
//        // Create list of data storer classes
//        List<Class<? extends DataStorer>> dataStorerCls = new LinkedList<Class<? extends DataStorer>>();
//        // add DataToCsvFile for storing in csv files
//        dataStorerCls.add(DataToCsvFile.class);
//        // get instance of controller (singleton pattern)
//        Controller controller = Controller.getInstance();
//        
//
//        
//        
//        String dataPath = "c:/data";
//        
//        // instantiate callbacks as anonymous class implementations
//        IErrorCallback errorCallback = new IErrorCallback() {
//            public void handleException(CermitException cermitException) {
//                System.out.println("Error: " + cermitException.toString());
//            }
//
//            public void handleResponseTimeout(SensorUnit sensorUnit, int messageTypeID, byte[] msg) {
//                System.out.println("Response Timeout for message id "+messageTypeID+" from SU: "+sensorUnit);
//            }
//
//            public void handleResponseError(SensorUnit sensorUnit, ResponseMessage responseMsg) {
//                System.out.println("Response error: "+responseMsg +" from SU: "+sensorUnit);
//            }
//        };
//        IDeviceSearchCallback devSearchCallback = new IDeviceSearchCallback() {
//            public void receive(List<IDeviceDescriptor> deviceDescriptors) {
//         
//            }
//        };
//        
//        // setup the controller
//        controller.setUp(BluetoothDeviceSearcher.class, dataStorerCls, dataPath, errorCallback, devSearchCallback, new ProtocolConstants());
//        DefaultSensorUnitFactory suFactory = DefaultSensorUnitFactory.getInstance();
//
//        // create a device descriptor for the device to connect 
//        //IDeviceDescriptor devDesc = new BluetoothDeviceDescriptor("00078063E3C5", "measurepi-1");
//        //IDeviceDescriptor devDesc = new BluetoothDeviceDescriptor("0007800f41ac", "UNIT-Rev4-ID-5");
//        //IDeviceDescriptor devDesc = new BluetoothDeviceDescriptor("0007806e65b0","UNIT-Rev3-ID-4");
//        
//        // instantiate handlers for state change, data messages and ctrlmessages as anonymous class implementations
//        IStateChangedCallback stateChangeCallback = new IStateChangedCallback() {
//            public void onStateChanged(StateSensorUnit oldState, StateSensorUnit newState, SensorUnit sensorUnit) {
//            System.out.println("State changed: " + oldState+" -> "+ newState);
//            }
//        };
////        
//        ICtrlObserver ctrlObs = new ICtrlObserver() {
//            public void update(de.hsulm.cermit.messages.ControlMessage ctrlMessage) {
//            System.out.println("Received ObjectMessage: " + ctrlMessage);
//            }
//        };
//        DataBufferV3 buffer = new DataBufferV3();
//        IFeatureObserver feature = new Feature1();
//        buffer.register(feature);
//       
//        SensorUnit sensorUnit = controller.connect(feature.get_qId().getDesc(), suFactory, stateChangeCallback, ctrlObs, buffer);
//        
//        
//        if (sensorUnit!=null) {
//            System.out.println("Connected.");
//            // open connection
//            sensorUnit.open();
//        } else {
//            System.out.println("Not connected.");
//            return;
//        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//        }
//
//        
//        // send acceptance of config
//        sensorUnit.cmdAcceptCfg(0);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//        }
//        
//        // get the list of connected sensor units
//        List<SensorUnit> suList = controller.getSensorUnits();
//        
//        // start measurement of the connected sensor units
//        controller.cmdStartMeasurement(suList,  MeasurementType.ONLINE, 0);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ex) {
//        }
//        
//        // stop the measurement
//        controller.cmdStopMeasurement();
//
//        // close connection and remove sensor unit from the controller
//        controller.disconnectSensorUnit(sensorUnit);
//    }
//}