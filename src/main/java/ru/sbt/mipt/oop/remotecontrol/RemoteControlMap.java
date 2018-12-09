package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteControlMap implements RemoteControl {

    private List<Command> pressedCommands = new ArrayList<>();

    private Map<String, Command> mapRemoteControl = new HashMap<>(8);

    @Override
    public void setCommandOnSlot(String button, Command command) {
        mapRemoteControl.put(button, command);
    }

    @Override
    public void pressedOnButton(String button) {
        Command command = mapRemoteControl.get(button);

        if (command == null) {
            System.out.println("There is not command on this button");
        } else {
            command.doCommand();
        }

    }
}
