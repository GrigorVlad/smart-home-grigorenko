package ru.sbt.mipt.oop.components;

import java.io.File;

public interface Printable {
    void printToConsole();
    void writeToFile(File file);
}
