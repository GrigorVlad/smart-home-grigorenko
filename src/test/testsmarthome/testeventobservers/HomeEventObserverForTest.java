package testeventobservers;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventForAlarm;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.observer.HomeEventObserver;
import ru.sbt.mipt.oop.sensoreventproviders.SensorEventProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeEventObserverForTest implements HomeEventObserver {

    private final List<EventProcessor> eventProcessorCollections = new ArrayList<EventProcessor>();

    @Override
    public void registerEventProcessor(EventProcessor eventProcessor) {
        eventProcessorCollections.add(eventProcessor);
    }

    @Override
    public void deleteEventProcessor(EventProcessor eventProcessor) {
        eventProcessorCollections.remove(eventProcessor);
    }

    List<SensorEvent> sensorEvents = Arrays.asList(new SensorEvent(SensorEventType.DOOR_CLOSED, "4"),
            new SensorEventForAlarm(SensorEventType.ALARM_ACTIVATE, "alarm", 12345));

    @Override
    public void runEventCycle(SmartHome smartHome) {
        for (SensorEvent event : sensorEvents) {

            for (EventProcessor eventProcessor : eventProcessorCollections) {
                eventProcessor.processEvent(smartHome, event);
            }

        }

    }

}
