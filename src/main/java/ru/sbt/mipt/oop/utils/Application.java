package ru.sbt.mipt.oop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.observer.HomeEventObserver;
import ru.sbt.mipt.oop.observer.OurHomeEventObserver;
import ru.sbt.mipt.oop.smarthomeloaders.SmartHomeLoader;

import java.io.IOException;

public class Application {

    private static SmartHome smartHome;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SmartHomeConfig.class);
        context.refresh();

        //smartHomeLoader = context.getBean(SmartHomeLoader.class);
        smartHome = context.getBean(SmartHome.class);


        HomeEventObserver homeEventObserver = context.getBean(HomeEventObserver.class);
        homeEventObserver.runEventCycle(smartHome);

        context.registerShutdownHook();

    }

}
