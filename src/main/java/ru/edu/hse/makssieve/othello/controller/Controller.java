package ru.edu.hse.makssieve.othello.controller;

import ru.edu.hse.makssieve.othello.model.Game;
import ru.edu.hse.makssieve.othello.model.enums.GameState;
import ru.edu.hse.makssieve.othello.model.exceptions.IllegalTurnException;
import ru.edu.hse.makssieve.othello.view.events.CellPressedEvent;
import ru.edu.hse.makssieve.othello.view.events.ExitGameEvent;
import ru.edu.hse.makssieve.othello.view.events.StartNewGameEvent;
import ru.edu.hse.makssieve.othello.view.forms.MainWindow;

public class Controller implements BoardListener, StartNewGameListener, ExitGameListener {

    private Game game;
    private MainWindow win;

    public Controller(Game game, MainWindow win) {
        this.game = game;
        this.win = win;

        this.win.updateView(this.game.getTransferObject());
        this.win.setBoardListener(this);
        //this.win.setExitGameListener(this);
        //this.win.setStartNewGameListener(this);

    }

    public void cellPressed(CellPressedEvent e) {
        try {
            win.updateView(game.makeTurn(e.getX(), e.getY()));
            if (game.state() != GameState.InProgress) {
                int res = -1;
                switch (game.state()){
                    case BlackWon: res  = win.inform("Black won!\nStart new game?");
                    case WhiteWon: res  = win.inform("White won!\nStart new game?");
                    case Draw: res  = win.inform("Draw!\nStart new game?");
                }
                if (res == 0) startGame(new StartNewGameEvent(this));
            }
        }catch (IllegalTurnException ex){
            win.alert(ex.getMessage());
        }
    }

    public void startGame(StartNewGameEvent e) {
        game = new Game();
        win.updateView(game.getTransferObject());
    }

    public void exitGame(ExitGameEvent e) {
        System.exit(0);
    }
}
