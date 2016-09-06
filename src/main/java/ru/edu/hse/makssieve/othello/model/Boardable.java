package ru.edu.hse.makssieve.othello.model;


import ru.edu.hse.makssieve.othello.model.enums.CellState;

import java.util.ArrayList;

interface Boardable {
    int getSize();
    ArrayList<ArrayList<CellState>> getBoard();
}
