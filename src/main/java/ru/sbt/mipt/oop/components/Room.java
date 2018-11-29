package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.eventprocessors.Action;

import java.io.File;
import java.util.Collection;

public class Room implements Actionable, Printable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room (Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        for (Door door : doors) {
            door.executeAction(action);
        }
        for (Light light : lights) {
            light.executeAction(action);
        }
    }

    @Override
    public void printToConsole() {
        System.out.println("Room: " + getName());
    }

    @Override
    public void writeToFile(File file) {

    }
}
