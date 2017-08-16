package ru.edu.hse.makssieve.othello.model;

import ru.edu.hse.makssieve.othello.controller.TransferObject;
import ru.edu.hse.makssieve.othello.model.enums.CellState;
import ru.edu.hse.makssieve.othello.model.enums.GameState;
import ru.edu.hse.makssieve.othello.model.enums.PlayerColor;
import ru.edu.hse.makssieve.othello.model.exceptions.IllegalTurnException;

interface Playable {

    TransferObject makeTurn(int x, int y) throws IllegalTurnException;
    boolean checkTurn(int x, int y);
    GameState changeState();
    //int score(PlayerColor c);
}
