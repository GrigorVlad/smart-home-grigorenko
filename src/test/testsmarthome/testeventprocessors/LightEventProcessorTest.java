package testeventprocessors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.LightEventProcessor;
import ru.sbt.mipt.oop.smarthomeloaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LightEventProcessorTest {

    private EventProcessor lightEventProcessor;
    private static SmartHomeLoader smartHomeLoader;
    private SmartHome smartHome;
    private SensorEvent event;

    @Before
    public void init() throws IOException {
        lightEventProcessor = new LightEventProcessor();
        smartHomeLoader = new FileSmartHomeLoader();
        smartHome = smartHomeLoader.loadSmartHome();
        event = new SensorEvent(SensorEventType.LIGHT_ON, "1");

    }

    @Test
    public void testEvent() {
        lightEventProcessor.processEvent(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("1")) {
                    assertEquals(true, light.isState());
                }
            }
        }
    }


}
