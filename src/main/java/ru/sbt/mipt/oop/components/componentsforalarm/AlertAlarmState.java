package ru.sbt.mipt.oop.components.componentsforalarm;

public class AlertAlarmState implements AlarmState {

    Alarm alarm;

    public AlertAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int inputPassword) {
        System.out.println("You can't change alarm state, when it in alert state");
    }

    @Override
    public void deactivate(int inputPassword) {
        System.out.println("You can't change alarm state, when it in alert state");
    }

    @Override
    public void print() {
        System.out.println("----WARNING!!!----");
        System.out.println("Alarm is in alert");
    }
}
