package ru.sbt.mipt.oop.components.componentsforalarm;

public class DeactivateAlarmState implements AlarmState {

    private Alarm alarm;

    DeactivateAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int inputPassword) {

        if (inputPassword == alarm.getPassword()) {
            alarm.setAlarmState(new ActivateAlarmState(alarm));
        } else {
            alarm.setAlarmState(new AlertAlarmState(alarm));
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
