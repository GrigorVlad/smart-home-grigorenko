package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnLightInHallCommand implements Command{

    private SmartHome smartHome;

    public TurnOnLightInHallCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Light light : room.getLights()) {
                    light.setOn(true);
                }
            }
        }

    }
}
