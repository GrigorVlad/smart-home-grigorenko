package testeventprocessors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.smarthomeloaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlarmEventProcessorTest {

    private EventProcessor eventProcessor1;
    private EventProcessor eventProcessor2;
    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private SmartHome smartHome;
    private SensorEvent event1;
    private SensorEvent event2;

    @Before
    public void init() throws IOException {
        eventProcessor1 = new AlarmEventProcessor();
        eventProcessor2 = new EventProcessorWithAlarm(new LightEventProcessor());
        smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setAlarm(new Alarm("alarm", 12345));

        event1 = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "alarm");
        event2 = new SensorEvent(SensorEventType.LIGHT_ON, "1");

    }

    @Test
    public void testEvent() {

        Alarm alarm = smartHome.getAlarm();

        eventProcessor1.processEvent(smartHome, event1);

        assertTrue(alarm.isActivated());

        eventProcessor2.processEvent(smartHome, event2);

        assertTrue(alarm.isAlert());

        for (Room room: smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                assertFalse(door.isOpen());
            }
        }

    }

    @Test
    public void testEventWrong() {
        Alarm alarm = smartHome.getAlarm();

        eventProcessor1.processEvent(smartHome, event1);

        assertTrue(alarm.isAlert());

    }

    @Test
    public void testEventDisable() {
        Alarm alarm = smartHome.getAlarm();

        eventProcessor1.processEvent(smartHome, new SensorEvent(SensorEventType.ALARM_ACTIVATE, "alarm"));
        assertTrue(alarm.isActivated());
        eventProcessor1.processEvent(smartHome, new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "alarm"));
        assertTrue(alarm.isDeactivated());


    }

}
