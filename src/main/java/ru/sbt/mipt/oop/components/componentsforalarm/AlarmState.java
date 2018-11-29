package ru.sbt.mipt.oop.components.componentsforalarm;

public interface AlarmState {

    void activate (int inputPassword);
    void deactivate (int inputPassword);

    public void print();

}
