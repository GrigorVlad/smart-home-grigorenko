package testremotecontrol;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.RemoteControlSensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.remotecontrol.MapRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;
import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.CancelCommand;
import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.TurnOffAllLightCommand;
import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.TurnOnAllLightCommand;
import ru.sbt.mipt.oop.smarthomeloaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoteControlTest {

    private SmartHome smartHome;
    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private Map<String, RemoteControl> mapRC = new HashMap<>();
    private RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
    private EventProcessor eventProcessor;

    private RemoteControl rc = new MapRemoteControl();

    @Before
    public void init() throws IOException {
        smartHome = smartHomeLoader.loadSmartHome();
        rc.setCommandOnSlot("A", new TurnOffAllLightCommand(smartHome));
        //rc.setCommandOnSlot("B", new TurnOnAllLightCommand(smartHome));
        rc.setCommandOnSlot("C", new CancelCommand(smartHome));
        //remoteControlRegistry.registerRemoteControl(rc, "1");
        //smartHome.setMapRC(remoteControlRegistry);
        //eventProcessor.processEvent(smartHome, new RemoteControlSensorEvent());
    }

    @Test
    public void testThis() {

        rc.pressedOnButton("A");

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isState());
            }
        }

        rc.pressedOnButton("B");

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertTrue(light.isState());
            }
        }


        rc.pressedOnButton("C");

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isState());
            }
        }

    }

}
