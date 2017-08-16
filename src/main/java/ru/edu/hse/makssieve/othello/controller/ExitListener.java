package ru.edu.hse.makssieve.othello.controller;

import ru.edu.hse.makssieve.othello.view.events.ExitGameEvent;

import java.util.EventListener;


public interface ExitListener extends EventListener {
    void exitGame(ExitGameEvent e);
}
