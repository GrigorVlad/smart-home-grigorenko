package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnAllLightCommand implements Command {

    private SmartHome smartHome;

    public TurnOnAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(true);
            }
        }

    }

}
