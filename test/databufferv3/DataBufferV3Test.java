/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databufferv3;

import Features.Feature1;
import IMyObserver.IFeatureObserver;
import de.hsulm.cermit.messages.DataMessage;
import de.hsulm.cermit.messages.DataMessageSingle;
import de.hsulm.cermit.sensorunitmodel.Channel;
import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mojib
 */
public class DataBufferV3Test {
    private Queue<Measurement> queue;
    private Measurement m1;
    private Measurement m2;
    private IFeatureObserver feature;
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
        m1 = new Measurement(new DataMessageSingle(), 2, 1);
        m2 = new Measurement(new DataMessageSingle(), 3, 2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class DataBufferV3.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        IFeatureObserver observer = null;
        DataBuffer instance = new DataBuffer();
        instance.register(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregister method, of class DataBufferV3.
     */
    @Test
    public void testUnregister() {
        System.out.println("unregister");
        IFeatureObserver observer = null;
        DataBuffer instance = new DataBuffer();
        instance.unregister(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObserver method, of class DataBufferV3.
     */
    @Test
    public void testNotifyObserver() {
        System.out.println("notifyObserver");
        IFeatureObserver observer = null;
        DataBuffer instance = new DataBuffer();
        instance.notifyObserver(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyAllObserver method, of class DataBufferV3.
     */
    @Test
    public void testNotifyAllObserver() {
        System.out.println("notifyAllObserver");
        DataBuffer instance = new DataBuffer();
        instance.notifyAllObserver();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DataBufferV3.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        DataMessage dataMessage = null;
        DataBuffer instance = new DataBuffer();
        instance.update(dataMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bufferControl method, of class DataBufferV3.
     */
    @Test
    public void testBufferControl() {
        System.out.println("bufferControl");
        IFeatureObserver feature = null;
        Queue<Measurement> queue = null;
        DataBuffer instance = new DataBuffer();
        instance.bufferControl(feature, queue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstElement method, of class DataBufferV3.
     */
    @Test
    public void testGetFirstElement() {
        System.out.println("getFirstElement");
        DataBuffer instance = new DataBuffer();
        assertEquals(queue.peek(), instance.getFirstElement(queue));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLastElement method, of class DataBufferV3.
     */
    @Test
    public void testGetLastElement() {
        System.out.println("getLastElement");
        //Queue<Measurement> queue = null;
        queue.add(m1);
        queue.add(m2);
        DataBuffer instance = new DataBuffer();
        
        Measurement expResult = m2;
        Measurement result = instance.getLastElement(queue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
