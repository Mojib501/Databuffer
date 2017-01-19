/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMyObserver;



/**
 *
 * @author mojib
 */
public abstract class Subject {
    public abstract void register(IFeatureObserver observer);
    public abstract void unregister(IFeatureObserver observer);
    public abstract void notifyObserver(IFeatureObserver observer);
    public abstract void notifyAllObserver();
    
}
