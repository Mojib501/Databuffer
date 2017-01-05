/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databufferv3;

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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author mojib
 */
public class DataBufferV3 implements IDataObserver{
    /**
     * @param args the command line arguments
     */ 
    //Das ist nur ein test
    Map<QueueIdentifier, Queue<Measurement>> queueMap = new LinkedHashMap<QueueIdentifier, Queue<Measurement>>();
    List<Measurement> measureList = new LinkedList<Measurement>();
    public void update(DataMessage dataMessage){
           dataMessage.processDataMessage(new IDataMessageHelper() {
        // jedes mal wenn eine neues device connected wird eine neue Queue 
        // für dieses speziele Device erzeugt.
            @Override 
            public void processMessage(DataMessageSingle singleMsg){
                IDeviceDescriptor desc = singleMsg.getSensorUnit().getDeviceDescriptor();
                int mdID = singleMsg.getMeasurementDeviceID();
                long timeStamp = singleMsg.getTime();
                //prüfen ob es schon eine objekt von Measurement und QueueIdentifier gibt
                if(queueMap.containsKey(desc))
                    queueMap.get(desc).offer(new Measurement(singleMsg, timeStamp, mdID));
                else{
                    QueueIdentifier queueIdentifier = new QueueIdentifier(desc, mdID);
                    Measurement measurement = new Measurement(singleMsg, timeStamp, mdID);
                    queueMap.get(queueIdentifier).offer(measurement);
                    }
                }
        });
    }
    /**@param mdId ist die MeasurementDeviceID
     * @param TBegin ist der Zeitpunkt der der ersten Messdatei
     * @param TEnd ist der Zeitpunkt der lestetn Messdatei 
     * @param channel 
     * @param queueIdentifier eindeutige referent zu einem Sensor
     * @return Liste aus Measurements für die FearueExtraction
     */
    public List<Measurement> getData(long TBegin,long TEnd, Channel channel, QueueIdentifier queueIdentifier){
        // die queue mit der passenden id aus der map holen
        
        Queue queue = queueMap.get(queueIdentifier);
        Iterator<Measurement> iterator = queue.iterator();
        Measurement firstElement = this.getFirstElement(queue);
        Measurement lastElement = this.getLastElement(queue);
        //prüfen ob genug Messdaten im puffer sind
        if(TEnd <= lastElement.getTimeStamp() && TBegin >= firstElement.getTimeStamp() ){
            //durch die Queue iterieren und Messdaten in Liste eintragen
            while(iterator.hasNext()){
                if(TEnd<=iterator.next().getTimeStamp() && TBegin>=iterator.next().getTimeStamp())
                {measureList.add(iterator.next());}
            }
        }
        else measureList.add(null);
            return measureList;
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
}
