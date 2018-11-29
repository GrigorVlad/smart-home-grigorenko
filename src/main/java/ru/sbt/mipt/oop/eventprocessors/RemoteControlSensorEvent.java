package ru.sbt.mipt.oop.eventprocessors;

public class RemoteControlSensorEvent extends SensorEvent {

    private String buttonPressed;

    public RemoteControlSensorEvent(SensorEventType type, String objectId) {
        super(type, objectId);

    }

    public String getButtonPressed() {
        return buttonPressed;
    }
}
