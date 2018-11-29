package testeventobservers;

import ru.sbt.mipt.oop.eventprocessors.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.SensorEventType;
import ru.sbt.mipt.oop.sensoreventproviders.SensorEventProvider;

public class SensorEventProviderForTest implements SensorEventProvider {

    @Override
    public SensorEvent getNextSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_LOCKED, "3");
    }
}
