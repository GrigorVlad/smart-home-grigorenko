package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.SmartHome;

public class ActivateAlarmCommand implements Command{

    int password;
    SmartHome smartHome;

    public ActivateAlarmCommand(SmartHome smartHome, int password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void undoCommand() {
        System.out.println("Operation of alarm activation can't be canceled");
    }

    @Override
    public void doCommand() {
        smartHome.getAlarm().activate(password);
    }

}
