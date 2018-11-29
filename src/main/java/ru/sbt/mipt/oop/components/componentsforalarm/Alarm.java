package ru.sbt.mipt.oop.components.componentsforalarm;

public class Alarm{

    private AlarmState activateAlarmState;
    private AlarmState deactivateAlarmState;
    private AlarmState alertAlarmState;

    private AlarmState alarmState;

    private final int password;
    private final String id;

    public Alarm(String id, int password) {
        this.id = id;
        this.password = password;

        activateAlarmState = new ActivateAlarmState(this);
        deactivateAlarmState = new DeactivateAlarmState(this);
        alertAlarmState = new AlertAlarmState(this);

        this.alarmState = deactivateAlarmState;
    }


    public void setAlarmState(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    public void activate (int inputPassword) {
        alarmState.activate(inputPassword);
    }

    public void deactivate (int inputPassword) {
        alarmState.deactivate(inputPassword);
    }

    public void alert () {
        setAlarmState(alertAlarmState);
    }

    public AlarmState getAlarmState() {
        return alarmState;
    }

    public int getPassword() {
        return password;
    }

    public String getId() {
        return this.id;
    }

    public void print() {
        alarmState.print();
    }



    public AlarmState getActivateAlarmState() {
        return activateAlarmState;
    }

    public AlarmState getDeactivateAlarmState() {
        return deactivateAlarmState;
    }

    public AlarmState getAlertAlarmState() {
        return alertAlarmState;
    }



    public boolean isActivated() {
        return this.alarmState instanceof ActivateAlarmState;
    }

    public boolean isDeactivated() {
        return this.alarmState instanceof DeactivateAlarmState;
    }

    public boolean isAlert() {
        return this.alarmState instanceof AlertAlarmState;
    }

}
