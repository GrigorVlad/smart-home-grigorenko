package ru.sbt.mipt.oop.sensoreventproviders;

import ru.sbt.mipt.oop.eventprocessors.SensorEvent;

public interface SensorEventProvider {
    SensorEvent getNextSensorEvent();
}
