package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;

import java.io.*;

public class AlarmEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isAlarmEvent(event)) return;

        changeAlarmState(smartHome.getAlarm(), (SensorEventForAlarm) event);

    }

    private void changeAlarmState(Alarm alarm, SensorEventForAlarm event) {

        switch (event.getType()) {
            case ALARM_ACTIVATE:
                alarm.activate(event.getPassword());
                break;

            case ALARM_DEACTIVATE:
                alarm.deactivate(event.getPassword());
                break;

        }

        alarm.print();
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE
                || event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }

}
