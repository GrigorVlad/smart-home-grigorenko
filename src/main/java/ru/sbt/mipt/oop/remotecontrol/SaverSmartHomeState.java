package ru.sbt.mipt.oop.remotecontrol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.components.SmartHome;

public class SaverSmartHomeState {

    Gson gson;
    String jsonString;

    public void saveState(SmartHome smartHome) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        jsonString = gson.toJson(smartHome);
    }

    public SmartHome returnState() {
        return gson.fromJson(jsonString, SmartHome.class);
    }

}