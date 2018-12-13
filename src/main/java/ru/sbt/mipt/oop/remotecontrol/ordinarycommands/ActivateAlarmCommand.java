package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.SmartHome;

import java.io.*;

public class ActivateAlarmCommand implements Command{

    private SmartHome smartHome;
    private int password;

    public ActivateAlarmCommand(SmartHome smartHome, int password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void doCommand() {



        smartHome.getAlarm().activate(password);
    }

}
