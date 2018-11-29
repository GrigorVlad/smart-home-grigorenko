package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.SmartHome;

public class SetAlarmAlertCommand implements Command {

    SmartHome smartHome;

    public SetAlarmAlertCommand(SmartHome smartHome, int password) {
        this.smartHome = smartHome;
    }

    @Override
    public void undoCommand() {
        System.out.println("Operation of alarm activation can't be canceled");
    }

    @Override
    public void doCommand() {
        smartHome.getAlarm().alert();
    }

}
