package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.SaverSmartHomeState;

public class CloseHallDoorCommand implements Command {

    SmartHome smartHome;
    SaverSmartHomeState saver;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
        saver = new SaverSmartHomeState();
    }

    @Override
    public void doCommand() {
        saver.saveState(smartHome);

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    door.setState(false);
                }
            }
        }

    }

    @Override
    public void undoCommand() {
        smartHome.setState(saver.returnState());
    }
}
