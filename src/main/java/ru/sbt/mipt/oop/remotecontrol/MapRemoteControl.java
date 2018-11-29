package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.ActivateAlarmCommand;
import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.CancelCommand;
import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.Command;
import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.SetAlarmAlertCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRemoteControl implements RemoteControl {

    List<Command> pressedCommands = new ArrayList<>();

    Map<String, Command> mapRemoteControl = new HashMap<>(8);

    @Override
    public void setCommandOnSlot(String button, Command command) {
        mapRemoteControl.put(button, command);
    }

    @Override
    public void pressedOnButton(String button) {
        Command command = mapRemoteControl.get(button);
        if (command != null) {
            if (command instanceof CancelCommand) {

                int numberCommand = pressedCommands.size() - 1;

                if (numberCommand < 0) {
                    System.out.println("There is nothing to cancel");
                    return;
                } else {
                    Command earlierCommand = pressedCommands.remove(numberCommand);
                    earlierCommand.undoCommand();
                }
            } else if ((command instanceof ActivateAlarmCommand) || (command instanceof SetAlarmAlertCommand)) {
                pressedCommands.clear();
            } else {
                pressedCommands.add(command);
                command.doCommand();
            }

        } else {
            System.out.println("This button has no command");
        }
    }
}
