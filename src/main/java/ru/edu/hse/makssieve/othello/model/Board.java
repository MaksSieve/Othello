package ru.edu.hse.makssieve.othello.model;

import ru.edu.hse.makssieve.othello.model.enums.CellState;
import ru.edu.hse.makssieve.othello.model.exceptions.IllegalTurnException;

import java.util.ArrayList;
import java.util.Arrays;

class Board implements Boardable {

    private ArrayList<ArrayList<CellState>> board;
    final private int size;

    Board(int size) {
        this.size = size;
        board = new ArrayList<ArrayList<CellState>>();
        for (int i = 0; i < this.size; i++) {
            board.add(new ArrayList<CellState>(8));
            for (int j = 0; j < this.size; j++){
                board.get(i).add(j, CellState.Free);
            }
        }
        this.init();
    }

    private Board init() {
        board.get(3).set(3, CellState.White);
        board.get(4).set(4, CellState.White);
        board.get(4).set(3, CellState.Black);
        board.get(3).set(4, CellState.Black);
        return this;
    }

    void putCell(CellState c, int x, int y) throws IllegalTurnException {
        board.get(x).set(y, c);
    }

    public int getSize() {
        return size;
    }

    public ArrayList<ArrayList<CellState>> getBoard() {
        return board;
    }

    ArrayList<CellState> getNeighbours(int x, int y) {
        ArrayList<CellState> returnList = new ArrayList<CellState>(this.size);
        for (int i = x - 1; i <= x + 1 ; i++){
            if (i > 0 && i < this.size) {
                ArrayList<CellState> line = board.get(i);
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i > 0 && i < this.size) {
                        returnList.add(line.get(j));
                    }
                }
            }
        }
        return returnList;
    }


    boolean isFree(int x, int y){
        return (board.get(x).get(y) == CellState.Free);
    }


}
