package testeventobservers;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.observer.HomeEventObserver;
import ru.sbt.mipt.oop.observer.OurHomeEventObserver;
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
        homeEventObserver = new HomeEventObserverForTest(new SensorEventProviderForTest());

        homeEventObserver.registerEventProcessor(new AlarmEventProcessor());
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new LightEventProcessor()));
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new DoorEventProcessor()));
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new HallDoorEventProcessor()));
    }

    @Test
    public void testThis() {

        homeEventObserver.runEventCycle(smartHome);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("3")) {
                    assertTrue(door.isLocked());
                    assertFalse(door.isState());
                }
            }
        }

    }
}