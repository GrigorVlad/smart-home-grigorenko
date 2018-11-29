package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.SaverSmartHomeState;

public class TurnOffAllLightCommand implements Command  {

    SmartHome smartHome;
    SaverSmartHomeState saver;

    public TurnOffAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
        saver = new SaverSmartHomeState();
    }

    @Override
    public void undoCommand() {
        smartHome.setState(saver.returnState());
    }

    @Override
    public void doCommand() {
        saver.saveState(smartHome);
        smartHome.turnOffLights();
    }
}
