package ru.sbt.mipt.oop.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.observer.HomeEventObserver;
import ru.sbt.mipt.oop.observer.OurHomeEventObserver;
import ru.sbt.mipt.oop.sensoreventproviders.RandomSensorEventProvider;
import ru.sbt.mipt.oop.sensoreventproviders.SensorEventProvider;
import ru.sbt.mipt.oop.smarthomeloaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;


@Configuration
@ComponentScan
public class SmartHomeConfig {

    @Bean
    public SmartHomeLoader smartHomeLoader() {
        return new FileSmartHomeLoader();
    }

    @Bean
    public SensorEventProvider sensorEventProvider() {
        return new RandomSensorEventProvider();
    }


    @Bean
    public HomeEventObserver homeEventObserver() {
        HomeEventObserver homeEventObserver = new OurHomeEventObserver(sensorEventProvider());
        homeEventObserver.registerEventProcessor(new AlarmEventProcessor());
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new LightEventProcessor()));
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new DoorEventProcessor()));
        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new HallDoorEventProcessor()));
        homeEventObserver.registerEventProcessor(new RemoteControlEventProcessor());
        return homeEventObserver;
    }

}
