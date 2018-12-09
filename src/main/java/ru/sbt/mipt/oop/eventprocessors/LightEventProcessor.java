package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;
        smartHome.executeAction(o -> {
            if (o instanceof Light) {
                Light light = (Light) o;
                if (light.getId().equals(event.getObjectId())) {
                    changeLightState(light, event);
                }
            }
        });

    }

    private void changeLightState(Light light, SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_ON) {
            light.setOn(true);
        } else {
            light.setOn(false);
        }
        light.print();
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == SensorEventType.LIGHT_OFF || event.getType() == SensorEventType.LIGHT_ON;
    }
}
