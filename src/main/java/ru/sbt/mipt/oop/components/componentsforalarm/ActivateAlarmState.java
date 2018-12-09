package ru.sbt.mipt.oop.components.componentsforalarm;

public class ActivateAlarmState implements AlarmState {

    private Alarm alarm;

    ActivateAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int inputPassword) {
        System.out.println("Alarm has been already activated");
    }

    @Override
    public void deactivate (int inputPassword) {

        if (inputPassword == alarm.getPassword()) {
            alarm.setAlarmState(new DeactivateAlarmState(alarm));
        } else {
            alarm.setAlarmState(new AlertAlarmState(alarm));
        }

    }

    @Override
    public void print() {
        System.out.println("Alarm is activated");
    }
}
