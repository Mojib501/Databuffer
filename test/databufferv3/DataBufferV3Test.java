/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databufferv3;

import IMyObserver.IFeatureObserver;
import de.hsulm.cermit.messages.DataMessage;
import de.hsulm.cermit.messages.DataMessageSingle;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author mojib
 */
public class DataBufferV3Test {
    
    public DataBufferV3Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DataMessageSingle singleMsg = new DataMessageSingle();
        Queue<Measurement> queue = new LinkedList<Measurement>();
        Measurement measurement1 = new Measurement(singleMsg, 5, 1);
        Measurement measurement2 = new Measurement(singleMsg, 3, 2);
        queue.add(measurement1);
        queue.add(measurement2);
        
        
    }
    
    @After
    public void tearDown() {
    }
   
    
    

    /**
     * Test of register method, of class DataBufferV3.
     */
    @Ignore 
    public void testRegister() {
        System.out.println("register");
        IFeatureObserver observer = null;
        DataBufferV3 instance = new DataBufferV3();
        instance.register(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregister method, of class DataBufferV3.
     */
    @Ignore
    public void testUnregister() {
        System.out.println("unregister");
        IFeatureObserver observer = null;
        DataBufferV3 instance = new DataBufferV3();
        instance.unregister(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserver method, of class DataBufferV3.
     */
    @Ignore
    public void testNotifyObserver() {
        System.out.println("notifyObserver");
        List<Measurement> measureList = null;
        IFeatureObserver observer = null;
        DataBufferV3 instance = new DataBufferV3();
        instance.notifyObserver(measureList, observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyAllObserver method, of class DataBufferV3.
     */
    @Ignore
    public void testNotifyAllObserver() {
        System.out.println("notifyAllObserver");
        DataBufferV3 instance = new DataBufferV3();
        instance.notifyAllObserver();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DataBufferV3.
     */
    @Ignore
    public void testUpdate() {
        System.out.println("update");
        DataMessage dataMessage = null;
        DataBufferV3 instance = new DataBufferV3();
        instance.update(dataMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendData method, of class DataBufferV3.
     */
    @Ignore
    public void testSendData() {
        System.out.println("sendData");
        IFeatureObserver key = null;
        DataBufferV3 instance = new DataBufferV3();
        instance.sendData(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstElement method, of class DataBufferV3.
     */
    @Ignore
    public void testGetFirstElement() {
        System.out.println("getFirstElement");
        Queue<Measurement> queue = null;
        DataBufferV3 instance = new DataBufferV3();
        Measurement expResult = null;
        Measurement result = instance.getFirstElement(queue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastElement method, of class DataBufferV3.
     */
    @Test
    public void testGetLastElement() {
        System.out.println("getLastElement");
        Queue<Measurement> queue = new LinkedList<>());
        DataBufferV3 instance = new DataBufferV3();
        Measurement expResult = queue.peek();
        Measurement result = instance.getLastElement(queue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
