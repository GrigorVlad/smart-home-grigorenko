package testeventprocessors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthomeloaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DoorEventProcessorTest {

    private EventProcessor doorEventProcessor;
    private static SmartHomeLoader smartHomeLoader;
    private SmartHome smartHome;
    private SensorEvent event;

    @Before
    public void init() throws IOException {
        doorEventProcessor = new DoorEventProcessor();
        smartHomeLoader = new FileSmartHomeLoader();
        smartHome = smartHomeLoader.loadSmartHome();
        event = new SensorEvent(SensorEventType.DOOR_LOCKED, "1");

    }

    @Test
    public void testEvent() {
        doorEventProcessor.processEvent(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("1")) {
                    assertEquals(false, door.isState());
                }
            }
        }
    }


}
