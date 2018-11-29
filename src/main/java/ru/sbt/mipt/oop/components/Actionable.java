package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.eventprocessors.Action;

public interface Actionable {
    public void executeAction(Action action);
}
