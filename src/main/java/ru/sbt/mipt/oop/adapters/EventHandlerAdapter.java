package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;

import java.util.HashMap;
import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    EventProcessor eventProcessor;
    SmartHome smartHome;

    public EventHandlerAdapter(EventProcessor eventProcessor, SmartHome smartHome) {
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
    }

    private Map<String, SensorEventType> createOppositeCorrespondenceMap() {
        Map<String, SensorEventType> map = new HashMap<>();
        map.put("LightIsOn", SensorEventType.LIGHT_ON);
        map.put("LightIsOff", SensorEventType.LIGHT_OFF);
        map.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        map.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        map.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        map.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);

        return map;
    }

    @Override
    public void handleEvent(CCSensorEvent ccEvent) {
        Map<String, SensorEventType> oppositeCorrespondenceMap = createOppositeCorrespondenceMap();
        SensorEvent sensorEvent = new SensorEvent(oppositeCorrespondenceMap.get(ccEvent.getEventType()), ccEvent.getObjectId());
        eventProcessor.processEvent(smartHome, sensorEvent);
    }
}
