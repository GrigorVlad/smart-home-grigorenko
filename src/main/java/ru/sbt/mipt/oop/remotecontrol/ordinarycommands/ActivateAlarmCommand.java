package ru.sbt.mipt.oop.remotecontrol.ordinarycommands;

import ru.sbt.mipt.oop.components.SmartHome;

import java.io.*;

public class ActivateAlarmCommand implements Command{

    private SmartHome smartHome;

    public ActivateAlarmCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {
        System.out.println("Введите пароль: ");
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int password = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\студентФИВТ\\Курсы СберТех\\ДизайнООП\\smart-home-2018-master\\src\\main\\resources\\input_password.txt")));
            password = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        smartHome.getAlarm().activate(password);
    }

}
