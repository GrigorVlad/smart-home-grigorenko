package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.SmartHome;


//todo: abstract class, which implements this interface...
public interface EventProcessor {

    void processEvent(SmartHome smartHome, SensorEvent event);

}
