package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.SmartHome;

public class SetAlarmAlertCommand implements Command {

    private SmartHome smartHome;

    public SetAlarmAlertCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void doCommand() {
        smartHome.getAlarm().alert();
    }

}
