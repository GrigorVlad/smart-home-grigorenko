package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.eventprocessors.Action;

public abstract class Component implements Actionable {
    private final String id;
    private boolean state;

    protected Component(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean newState) {
        this.state = newState;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }

    public abstract void print();
}
