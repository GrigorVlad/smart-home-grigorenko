package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.Command;

public interface RemoteControl {

    void setCommandOnSlot(String button, Command command);

    void pressedOnButton(String button);

}
