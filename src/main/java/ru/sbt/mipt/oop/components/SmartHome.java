package ru.sbt.mipt.oop.components;

//import com.sun.javafx.scene.control.skin.VirtualFlow;

import ru.sbt.mipt.oop.components.componentsforalarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.Action;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, Printable {

    private RemoteControlRegistry mapRC;
    private Alarm alarm;
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.rooms = rooms;
        this.alarm = alarm;
    }



    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public void setMapRC(RemoteControlRegistry mapRC) {
        this.mapRC = mapRC;
    }

    public RemoteControlRegistry getMapRC() {
        return mapRC;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }



    public void turnOffLights() {
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                light.setState(false);
            }
        }
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        for(Room room : rooms) {
            room.executeAction(action);
        }
    }

    @Override
    public void printToConsole() {
        System.out.println("SmartHome consists of: ");
        for (Room room : rooms) {
            room.printToConsole();
        }
    }

    @Override
    public void writeToFile(File file) {
        System.out.println("SmartHome consists of: ");
        for (Room room : rooms) {
            room.writeToFile(file);
        }
    }

    public void alarmAlert() {
        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                door.setState(false);
            }
        }
    }

    public void setState(SmartHome smartHome) {
        this.rooms = smartHome.getRooms();
        this.alarm = smartHome.getAlarm();
        this.mapRC = smartHome.getMapRC();
    }

}
