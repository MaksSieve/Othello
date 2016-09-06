package ru.edu.hse.makssieve.othello.controller;

import ru.edu.hse.makssieve.othello.view.events.StartNewGameEvent;

import java.util.EventListener;

public interface StartNewGameListener extends EventListener {
    void startGame(StartNewGameEvent e);
}
