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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import jdk.nashorn.internal.runtime.JSType;

//import java.util.concurrent.BlockingDeque;
//import java.util.concurrent.LinkedBlockingQueue;

/**
 *TODO Buffer muss geleert werden
 *     Buffer grenzen festlegen
 *     Buffer Thread-sicher machen
 *     Buffer in measageController eine exception auswerfen fall keine qID
 * @author mojib
 */
public class DataBuffer extends Subject implements IDataObserver{
    /**
     * @param args the command line arguments
     */ 
    HashMap<IFeatureObserver,HashMap<QueueIdentifier,Queue<Measurement>>> queueMap = new LinkedHashMap<>();
//    
//    
//    
//    
    @Override
    public void register(IFeatureObserver observer) {
        //pro observer/Feature eine map mit key=qid und value=queue
        queueMap.put(observer, deviceController(observer.getQIdList()));
    }
    @Override
    public void unregister(IFeatureObserver observer) {
        queueMap.remove(observer);
    }//HashMap<IFeatureObserver,HashMap<QueueIdentifier,Queue<Measurement>>> map, IFeatureObserver observer
    @Override
    public void notifyObserver(IFeatureObserver observer){
        //bestimmten observer benachtichtigen und liste übergeben
        observer.update(queueMap.get(observer));
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
    @Override
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
        //äussere Map
        for(Map.Entry<IFeatureObserver,HashMap<QueueIdentifier,Queue<Measurement>>> map:queueMap.entrySet()){
            //innere Map
            //Measurement zu der jeweiligen qId erstellen
            map.getValue().get(qId).offer(new Measurement(singleMsg, timeStamp, mdId));
            bufferControl(map.getKey(),map.getValue().get(qId));
        }
    }
    public void bufferControl(IFeatureObserver feature,Queue<Measurement> queue){
        int deltaTime = feature.getDeltaTimeStamp();
        //if(delaTime <= letztesElement - erstesElement)
        if(deltaTime <= (getLastElement(queue).getTimeStamp()-getFirstElement(queue).getTimeStamp()))
            notifyObserver(feature);
        //überlappungs algorithmus
        //noch zu überarbeiten!!!
            int cutBorder = queue.size()*feature.getDeltaTimeStamp();
        while(cutBorder > queue.size()){
            queue.remove();
        }
    }       
    public Measurement getFirstElement(Queue<Measurement> queue){
        return queue.peek();
    }
    public Measurement getLastElement(Queue<Measurement> queue){
        Iterator<Measurement> iterator = queue.iterator();
        Measurement lastElement=null;
            while(iterator.hasNext()){
                lastElement = iterator.next();
            }
        return lastElement;    
    }
    //erzeugt eine map mit key= qId , value = Queue
    private HashMap<QueueIdentifier,Queue<Measurement>> deviceController(List<QueueIdentifier> list){
        HashMap<QueueIdentifier,Queue<Measurement>>
        deepQueueMap = new HashMap<QueueIdentifier,Queue<Measurement>>();
   
        for(QueueIdentifier e : list){
            deepQueueMap.put(e, new LinkedBlockingQueue<>());
            }
        return deepQueueMap;
    }
}
