package ru.edu.hse.makssieve.othello.controller;

import ru.edu.hse.makssieve.othello.model.enums.CellState;
import ru.edu.hse.makssieve.othello.model.enums.PlayerColor;

import java.util.ArrayList;

public class TransferObject {
    private int whiteScore;
    private int blackScore;
    private ArrayList<ArrayList<CellState>> board;
    private PlayerColor currentPlayer;

    public TransferObject(int whiteScore, int blackScore, ArrayList<ArrayList<CellState>> board, PlayerColor currentPlayer) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public ArrayList<ArrayList<CellState>> getBoard() {
        return board;
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }
}
