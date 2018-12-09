package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.eventprocessors.Action;

public class Door implements Actionable {

    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    private boolean locked;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    public void print() {
        System.out.print("Door " + this.getId() + " ");

        if (this.isOpen()) {
            System.out.print("is open and ");
        } else {
            System.out.print("is close and ");
        }

        if (this.isLocked()) {
            System.out.println("locked!");
        } else {
            System.out.println("not locked!");
        }

    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }
}
