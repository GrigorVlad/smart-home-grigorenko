package ru.sbt.mipt.oop.sensoreventproviders;

import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;

public class RandomSensorEventProvider implements SensorEventProvider {
    @Override
    public SensorEvent getNextSensorEvent() {
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (6 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
