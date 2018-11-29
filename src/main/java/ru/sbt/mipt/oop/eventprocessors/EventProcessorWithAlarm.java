package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;

public class EventProcessorWithAlarm implements EventProcessor {

    EventProcessor eventProcessor;

    public EventProcessorWithAlarm(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        Alarm alarm = smartHome.getAlarm();
        if (alarm == null) {
            eventProcessor.processEvent(smartHome, event);
            return;
        }

        if (alarm.isActivated()) {
            alarm.alert();
            alarm.getAlarmState().print();
            smartHome.alarmAlert();
        } else if (alarm.isDeactivated()) {
            eventProcessor.processEvent(smartHome, event);
        }

    }
}
