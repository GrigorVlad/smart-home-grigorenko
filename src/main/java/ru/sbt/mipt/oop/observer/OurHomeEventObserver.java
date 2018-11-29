package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.sensoreventproviders.SensorEventProvider;

import java.util.ArrayList;
import java.util.List;

public class OurHomeEventObserver implements HomeEventObserver {

    private final List<EventProcessor> eventProcessorCollections = new ArrayList<EventProcessor>();
    private SensorEventProvider sensorEventProvider;

    public OurHomeEventObserver(SensorEventProvider sensorEventProvider) {
        this.sensorEventProvider = sensorEventProvider;
    }

    @Override
    public void registerEventProcessor(EventProcessor eventProcessor) {
        eventProcessorCollections.add(eventProcessor);
    }

    @Override
    public void deleteEventProcessor(EventProcessor eventProcessor) {
        eventProcessorCollections.remove(eventProcessor);
    }

    @Override
    public void runEventCycle(SmartHome smartHome) {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();

            while (event != null) {
                System.out.println("Got event: " + event);
                for (EventProcessor eventProcessor : eventProcessorCollections) {
                    eventProcessor.processEvent(smartHome, event);
                }
                event = sensorEventProvider.getNextSensorEvent();
        }
    }
}
