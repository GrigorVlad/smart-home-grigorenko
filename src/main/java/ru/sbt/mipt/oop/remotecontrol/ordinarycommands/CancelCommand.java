package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.SmartHome;

public class CancelCommand implements Command {

    SmartHome smartHome;

    public CancelCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {}

    @Override
    public void undoCommand() {}

}
