package ru.sbt.mipt.oop.components.componentsforalarm;

public class DeactivateAlarmState implements AlarmState {

    Alarm alarm;

    public DeactivateAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int inputPassword) {

        if (inputPassword == alarm.getPassword()) {
            alarm.setAlarmState(alarm.getActivateAlarmState());
        } else {
            alarm.setAlarmState(alarm.getAlertAlarmState());
        }

    }

    @Override
    public void deactivate(int inputPassword) {
        System.out.println("Alarm is deactivated");
    }

    @Override
    public void print() {
        System.out.println("Alarm is deactivated");
    }
}
