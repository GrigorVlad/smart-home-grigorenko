package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.eventprocessors.Action;

public class Door extends Component{

    private boolean locked;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Door(String idName, boolean isOpen) {
        super(idName);
        this.setState(isOpen);
        this.setLocked(false);
    }

    @Override
    public void print() {
        System.out.print("Door " + this.getId() + " ");

        if (this.isState()) {
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
}
