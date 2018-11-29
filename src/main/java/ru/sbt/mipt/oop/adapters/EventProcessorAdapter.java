package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;

import java.util.HashMap;
import java.util.Map;

public class EventProcessorAdapter implements EventProcessor {
    EventHandler eventHandler;

    public EventProcessorAdapter(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    private Map<SensorEventType,String> createCorrespondenceMap() {
        Map<SensorEventType, String> map = new HashMap<>();
        map.put(SensorEventType.LIGHT_ON, "LightIsOn");
        map.put(SensorEventType.LIGHT_OFF, "LightIsOff");
        map.put(SensorEventType.DOOR_OPEN, "DoorIsOpen");
        map.put(SensorEventType.DOOR_CLOSED, "DoorIsClosed");
        map.put(SensorEventType.DOOR_LOCKED, "DoorIsLocked");
        map.put(SensorEventType.DOOR_UNLOCKED, "DoorIsUnlocked");

        return map;
    }


    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        Map<SensorEventType, String> correspondenceMap = createCorrespondenceMap();
        CCSensorEvent ccEvent = new CCSensorEvent(correspondenceMap.get(event.getType()), event.getObjectId());
        eventHandler.handleEvent(ccEvent);
    }
}
