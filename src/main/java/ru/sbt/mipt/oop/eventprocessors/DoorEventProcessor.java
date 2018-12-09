package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.SmartHome;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;
        smartHome.executeAction(o -> {
            if (o instanceof Door) {
                Door door = (Door) o;
                if (door.getId().equals(event.getObjectId())) {
                    changeDoorState(door, event);
                }
            }
        });
    }

    private void changeDoorState(Door door, SensorEvent event) {

        if (event.getType() == SensorEventType.DOOR_LOCKED) {
            door.setOpen(false);
            door.setLocked(true);
        } else if (event.getType() == SensorEventType.DOOR_UNLOCKED) {
            door.setLocked(false);
        } else if (event.getType() == SensorEventType.DOOR_CLOSED) {
            door.setOpen(false);
        } else {
            door.setOpen(true);
        }
        door.print();
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_OPEN
                || event.getType() == SensorEventType.DOOR_CLOSED
                || event.getType() == SensorEventType.DOOR_LOCKED
                || event.getType() == SensorEventType.DOOR_UNLOCKED;
    }

}