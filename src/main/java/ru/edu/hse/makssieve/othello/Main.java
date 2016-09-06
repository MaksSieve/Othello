package ru.edu.hse.makssieve.othello;

import ru.edu.hse.makssieve.othello.controller.Controller;
import ru.edu.hse.makssieve.othello.model.Game;
import ru.edu.hse.makssieve.othello.view.forms.MainWindow;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new Game(), new MainWindow());
            }
        });
    }
}
