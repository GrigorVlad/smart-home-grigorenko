package ru.sbt.mipt.oop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.observer.HomeEventObserver;
import ru.sbt.mipt.oop.observer.OurHomeEventObserver;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;

public class Application {

    //@Autowired
    private static SmartHomeLoader smartHomeLoader;


    private static SmartHome smartHome;

    //private static HomeEventObserver homeEventObserver = new OurHomeEventObserver(new RandomSensorEventProvider());
    //private static HomeEventObserver homeEventObserver = new HomeEventObserverAdapter(new SensorEventsManager());

//    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
//        Application.smartHomeLoader = smartHomeLoader;
//    }

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SmartHomeConfig.class);
        context.refresh();

        smartHomeLoader = context.getBean(SmartHomeLoader.class);

        smartHome = smartHomeLoader.loadSmartHome();

        HomeEventObserver homeEventObserver = context.getBean(OurHomeEventObserver.class);

        homeEventObserver.runEventCycle(smartHome);


//        SmartHome smartHome = smartHomeLoader.loadSmartHome();
//        smartHome.setAlarm(new Alarm("alarm", 12345));
//
//        //Регистрируем EventProcessors
//        homeEventObserver.registerEventProcessor(new AlarmEventProcessor());
//        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new LightEventProcessor()));
//        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new DoorEventProcessor()));
//        homeEventObserver.registerEventProcessor(new EventProcessorWithAlarm(new HallDoorEventProcessor()));
//        homeEventObserver.registerEventProcessor(new RemoteControlEventProcessor());
//
//        //Запускаем цикл
//        homeEventObserver.runEventCycle(smartHome);

    }

}
