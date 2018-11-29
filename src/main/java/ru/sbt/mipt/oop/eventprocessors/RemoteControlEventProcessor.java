package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.SmartHome;

public class RemoteControlEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.REMOTE_CONTROL_ACTION) {
            RemoteControlSensorEvent rcSensorEvent = (RemoteControlSensorEvent) event;
            smartHome.getMapRC().pressedButtonOnRC(rcSensorEvent.getObjectId(), rcSensorEvent.getButtonPressed());
        }
    }

}
