package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.SmartHome;

public interface EventProcessor {

    void processEvent(SmartHome smartHome, SensorEvent event);

}
