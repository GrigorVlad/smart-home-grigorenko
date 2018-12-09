package testeventobservers;

import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.sensoreventproviders.SensorEventProvider;

import java.util.ArrayList;
import java.util.List;

public class SensorEventProviderForTest implements SensorEventProvider {

    List<SensorEvent> sensorEvents = new ArrayList<>();

    @Override
    public SensorEvent getNextSensorEvent() {

        return new SensorEvent(SensorEventType.ALARM_ACTIVATE, "alarm");
    }
}
