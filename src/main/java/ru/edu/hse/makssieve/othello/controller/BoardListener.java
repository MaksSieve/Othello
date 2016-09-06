package ru.edu.hse.makssieve.othello.controller;

import ru.edu.hse.makssieve.othello.view.events.CellPressedEvent;

import java.util.EventListener;


public interface BoardListener extends EventListener {
    void cellPressed(CellPressedEvent e);
}
