package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.eventprocessors.Action;

public class Light implements Actionable {

    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void print() {
        if (this.isOn()) {
            System.out.println("Light " + this.getId() + " is on!");
        } else {
            System.out.println("Light " + this.getId() + " is off!");
        }
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }
}
