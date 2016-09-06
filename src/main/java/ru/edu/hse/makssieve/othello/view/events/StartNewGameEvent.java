package ru.edu.hse.makssieve.othello.view.events;

import java.util.EventObject;

public class StartNewGameEvent extends EventObject {
    public StartNewGameEvent(Object source) {
        super(source);
    }
}
