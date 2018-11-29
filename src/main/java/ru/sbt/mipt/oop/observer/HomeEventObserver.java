package ru.sbt.mipt.oop.observer;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;

public interface HomeEventObserver {

    /*Осуществляет регистрацию в общую коллекцию EventProcessor*/
    public void registerEventProcessor(EventProcessor eventProcessor);

    /*Удаляет EventProcessor из коллекции*/
    public void deleteEventProcessor(EventProcessor eventProcessor);

    /*Запускает SmartHome*/
    public void runEventCycle (SmartHome smartHome);

}
