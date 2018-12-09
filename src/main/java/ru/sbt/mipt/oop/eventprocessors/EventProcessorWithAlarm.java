package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.AlarmEventProcessor;

public class EventProcessorWithAlarm implements EventProcessor {

    private EventProcessor eventProcessor;

    public EventProcessorWithAlarm(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {

        if (event == null) {
            return;
        }

        Alarm alarm = smartHome.getAlarm();
        if (alarm == null) {
            eventProcessor.processEvent(smartHome, event);
            return;
        }


        if (!isAlarmEvent(event)) {
            if (alarm.isAlert()) {
                System.out.println("Alarm alert, I can't do anything");
            } else if (alarm.isActivated()) {
                alarm.alert();
                alarm.getAlarmState().print();
                smartHome.alarmAlert();
            } else if (alarm.isDeactivated()) {
                eventProcessor.processEvent(smartHome, event);
            }
        }

    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE
                || event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }

}
