package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.components.SmartHome;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlRegistry {

    private Map<String, RemoteControl> mapOfRemoteControls = new HashMap<>();

    public void registerRemoteControl(RemoteControl remoteControl, String rcId) {
        mapOfRemoteControls.put(rcId, remoteControl);
    }

    public void pressedButtonOnRC(String rcId, String button) {
        RemoteControl remoteControl =  mapOfRemoteControls.get(rcId);

        if (remoteControl != null) {
            remoteControl.pressedOnButton(button);
        }

    }

}
