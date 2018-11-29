package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.observer.HomeEventObserver;


public class HomeEventObserverAdapter implements HomeEventObserver {
    SensorEventsManager sensorEventsManager;
    SmartHome smartHome;

    public HomeEventObserverAdapter(SensorEventsManager sensorEventsManager) {
        this.sensorEventsManager = sensorEventsManager;
    }

    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void registerEventProcessor(EventProcessor eventProcessor) {
        EventHandler eventHandler = new EventHandlerAdapter(eventProcessor, smartHome);
        sensorEventsManager.registerEventHandler(eventHandler);
    }

    @Override
    public void deleteEventProcessor(EventProcessor eventProcessor) {
        System.out.println("This operation is not Provided");
    }

    @Override
    public void runEventCycle(SmartHome smartHome) {
        sensorEventsManager.start();

    }
}
