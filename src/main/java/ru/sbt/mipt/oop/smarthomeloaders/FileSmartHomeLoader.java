package ru.sbt.mipt.oop.smarthomeloaders;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.components.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Реализует интерфейс SmartHomeLoader
//считывает данные о smartHome из файла

@Component
public class FileSmartHomeLoader implements SmartHomeLoader {

    @Override
    public SmartHome loadSmartHome() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
    }

}
