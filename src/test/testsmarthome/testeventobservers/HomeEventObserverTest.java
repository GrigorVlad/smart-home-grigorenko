package testeventobservers;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.observer.HomeEventObserver;
import ru.sbt.mipt.oop.smarthomeloaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HomeEventObserverTest {

    private static SmartHomeLoader smartHomeLoader; // = new FileSmartHomeLoader();
    private static HomeEventObserver homeEventObserver; // = new HomeEventObserverAdapter(new SensorEventsManager());
    private static SmartHome smartHome;

    @Before
    public void init() throws IOException {
        smartHomeLoader = new FileSmartHomeLoader();
        smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setAlarm(new Alarm("alarm", 12345));
        homeEventObserver = new HomeEventObserverForTest();

        homeEventObserver.registerEventProcessor(new AlarmEventProcessor());
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new LightEventProcessor()));
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new DoorEventProcessor()));
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new HallDoorEventProcessor()));
    }

    @Test
    public void testThis() {
        homeEventObserver.runEventCycle(smartHome);

        assertTrue(smartHome.getAlarm().isActivated());

        for(Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }

            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    assertFalse(door.isOpen());
                }
            }

        }

    }



}