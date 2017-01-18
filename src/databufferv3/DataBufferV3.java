/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databufferv3;

import IMyObserver.IFeatureObserver;
import IMyObserver.Subject;
import de.hsulm.cermit.commandinterface.SensorUnit;
import de.hsulm.cermit.eventinterface.IDataObserver;
import de.hsulm.cermit.messages.DataMessage;
import de.hsulm.cermit.configuration.sensors.XDeviceDescriptor;
import de.hsulm.cermit.configuration.sensors.XDeviceDescriptorAssigned;
import de.hsulm.cermit.exchangableinterface.IDeviceDescriptor;
import de.hsulm.cermit.implementations.BluetoothDeviceDescriptor;
import de.hsulm.cermit.messages.DataMessageSingle;
import de.hsulm.cermit.messages.IDataMessageHelper;
import de.hsulm.cermit.messages.RspMeasurementDeviceCfg;
import de.hsulm.cermit.sensorunitmodel.MeasurementDevice;
import de.hsulm.cermit.sensorunitmodel.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
//import java.util.concurrent.BlockingDeque;
//import java.util.concurrent.LinkedBlockingQueue;

/**
 *TODO Buffer muss geleert werden
 *     Buffer grenzen festlegen
 *     Buffer Thread-sicher machen
 * @author mojib
 */
public class DataBufferV3 extends Subject implements IDataObserver{
    /**
     * @param args the command line arguments
     */ 
    //Das ist nur ein test
    //Map<IFeatureObserver, Map<Queue<Measurement>>> queueMap = new LinkedHashMap<IFeatureObserver, <Measurement>>();
    //List<Measurement> measureList;
    //List<IFeatureObserver> features = new LinkedList<>();
    HashMap<IFeatureObserver, HashMap<QueueIdentifier,Queue<Measurement>>> 
            queueMap = new LinkedHashMap<IFeatureObserver,HashMap<QueueIdentifier,Queue<Measurement>>>();
    HashMap<QueueIdentifier,Queue<Measurement>>
            deepQueueMap = new HashMap<QueueIdentifier,Queue<Measurement>>();
    //Map<QueueIdentifier,Queue<Measurement>> queueMap2 = new HashMap<QueueIdentifier,Queue<Measurement>>();
    //HashMap<String, HashMap<String,String>> gens = new HashMap<String,HashMap<String,String>>();
    
//    
//    
//    
//    
    @Override
    public void register(IFeatureObserver observer) {
        List<QueueIdentifier> qIdList = observer.getQIdList();
        //schreibt die qIDs in die deepQueueMap
        deviceController(qIdList);
        queueMap.put(observer,deepQueueMap);
    }

    @Override
    public void unregister(IFeatureObserver observer) {
        queueMap.remove(observer);
    }
    @Override
    public void notifyObserver(List<Measurement> measureList, IFeatureObserver observer){
        //bestimmten observer benachtichtigen und liste übergeben
        observer.update(measureList);
        //wenn nachricht versendet wurde dann aus dem buffer löschen
        
    }
    @Override
    public void notifyAllObserver() {
        for(Entry e: queueMap.entrySet()){
            //alle observer benachrichtigen
        }
    }
//    
//    
//    
//    
    public void update(DataMessage dataMessage){
           dataMessage.processDataMessage(new IDataMessageHelper() {
        // jedes mal wenn eine neues device connected wird eine neue Queue 
        // für dieses speziele Device erzeugt.
            @Override 
            public void processMessage(DataMessageSingle singleMsg){
                messageController(singleMsg);
                }
        });
    }
    //sortiert die messdaten zu den jeweiligen features und füllt die queue
    private void messageController(DataMessageSingle singleMsg){
        IDeviceDescriptor desc = singleMsg.getSensorUnit().getDeviceDescriptor();
        int mdId = singleMsg.getMeasurementDeviceID();
        long timeStamp = singleMsg.getTime();
        QueueIdentifier qId = new QueueIdentifier(desc,mdId);
        deepQueueMap.get(qId).offer(new Measurement(singleMsg, timeStamp, mdId));
        bufferControl();
    }
    private void bufferControl(){
        //controlliert ob eine gewisse queue genug daten hat 
        //wenn ja notifyObserver()
    }
    public Measurement getFirstElement(Queue<Measurement> queue){
        Iterator<Measurement> iter = queue.iterator();
        Measurement firstElement = iter.next();
        return firstElement;
    }
    /**
     * @param queue
     * @return letzte Measurement Element in der Queue 
     */
    public Measurement getLastElement(Queue<Measurement> queue){
        Iterator<Measurement> iterator = queue.iterator();
        Measurement lastElement=null;
            while(iterator.hasNext()){
                lastElement = iterator.next();
            }
        return lastElement;    
    }
    private void deviceController(List<QueueIdentifier> list){
        //schreibt alle qIds als key in die map 
        }
    private void isAdded(IFeatureObserver key,Measurement value){
        if(value.getCounter() < key.get_deltaTimeStamp())    
            value.count();
        else 
            sendData(key);
    }
}
