package testremotecontrol;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlMap;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;
import ru.sbt.mipt.oop.remotecontrol.ordinarycommands.*;
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
    private RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry(smartHome);
    private EventProcessor eventProcessor;

    private RemoteControl rc = new RemoteControlMap();

    @Before
    public void init() throws IOException {
        smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setAlarm(new Alarm("alarm", 12345));
        rc.setCommandOnSlot("A", new TurnOnAllLightCommand(smartHome));
        rc.setCommandOnSlot("B", new TurnOffAllLightCommand(smartHome));
        rc.setCommandOnSlot("C", new CloseHallDoorCommand(smartHome));
        rc.setCommandOnSlot("D", new SetAlarmAlertCommand(smartHome));
        rc.setCommandOnSlot("1", new TurnOnLightInHallCommand(smartHome));
        //rc.setCommandOnSlot("2", new ActivateAlarmCommand(smartHome));
    }

    @Test
    public void testThis() {

        Alarm alarm = smartHome.getAlarm();

        rc.pressedOnButton("B");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }

        rc.pressedOnButton("A");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        rc.pressedOnButton("C");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    assertFalse(door.isOpen());
                }
            }
        }

        rc.pressedOnButton("1");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Light light : room.getLights()) {
                    assertTrue(light.isOn());
                }
            }
        }
    }

    @Test
    public void testActivateAlarmWithCorrectPassword() {
        Alarm alarm = smartHome.getAlarm();
        rc.setCommandOnSlot("2", new ActivateAlarmCommand(smartHome, 12345));
        rc.pressedOnButton("2");
        assertTrue(alarm.isActivated());
    }

    @Test
    public void testActivateAlarmWithIncorrectPassword() {
        Alarm alarm = smartHome.getAlarm();
        rc.setCommandOnSlot("2", new ActivateAlarmCommand(smartHome, 123));
        rc.pressedOnButton("2");
        assertTrue(alarm.isAlert());
    }


}
