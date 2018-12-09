package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;

import java.io.*;

public class AlarmEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isAlarmEvent(event)) return;

        changeAlarmState(smartHome.getAlarm(), event);

    }

    private void changeAlarmState(Alarm alarm, SensorEvent event) {

        int inputPassword;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\студентФИВТ\\Курсы СберТех\\ДизайнООП\\smart-home-2018-master\\src\\main\\resources\\input_password.txt")));
            System.out.print("Введите пароль: ");
            inputPassword = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            inputPassword = 0;
        }

        switch (event.getType()) {
            case ALARM_ACTIVATE:
                alarm.activate(inputPassword);
                break;

            case ALARM_DEACTIVATE:
                alarm.deactivate(inputPassword);
                break;

        }

        alarm.print();
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE
                || event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }

}
