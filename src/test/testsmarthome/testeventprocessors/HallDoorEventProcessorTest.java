package testeventprocessors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.smarthomeloaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HallDoorEventProcessorTest {

    private EventProcessor hallDoorEventProcessor;
    private static SmartHomeLoader smartHomeLoader;
    private SmartHome smartHome;
    private SensorEvent event;

    @Before
    public void init() throws IOException {
        hallDoorEventProcessor = new HallDoorEventProcessor();
        smartHomeLoader = new FileSmartHomeLoader();
        smartHome = smartHomeLoader.loadSmartHome();
        event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");

    }

    @Test
    public void testEvent() {
        hallDoorEventProcessor.processEvent(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    assertEquals(false, door.isState());
                }

            }

            for (Light light : room.getLights()) {
                assertEquals(false, light.isState());
            }
        }
    }

}
