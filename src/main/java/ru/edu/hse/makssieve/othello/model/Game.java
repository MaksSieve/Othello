package ru.edu.hse.makssieve.othello.model;

import com.sun.istack.internal.Nullable;
import javafx.scene.control.Cell;
import ru.edu.hse.makssieve.othello.controller.TransferObject;
import ru.edu.hse.makssieve.othello.model.enums.CellState;
import ru.edu.hse.makssieve.othello.model.enums.GameState;
import ru.edu.hse.makssieve.othello.model.enums.PlayerColor;
import ru.edu.hse.makssieve.othello.model.exceptions.IllegalTurnException;

import java.util.ArrayList;

import static ru.edu.hse.makssieve.othello.model.enums.CellState.Black;
import static ru.edu.hse.makssieve.othello.model.enums.CellState.White;
import static ru.edu.hse.makssieve.othello.model.enums.PlayerColor.black;
import static ru.edu.hse.makssieve.othello.model.enums.PlayerColor.white;


public class Game implements Playable {

    private Board board;
    private PlayerColor currentPlayer;
    private int blackScore;
    private int whiteScore;

    public Game(){
        board = new Board(8);
        currentPlayer = black;
        blackScore = whiteScore = 2;
    }

    public GameState changeState() {
        if (blackScore + whiteScore != board.getSize()*board.getSize()) return GameState.InProgress;
        if (blackScore == whiteScore) return GameState.Draw;
        if (blackScore > whiteScore) return GameState.BlackWon;
        if (whiteScore > blackScore) return GameState.WhiteWon;
        return null;
    }

   /* public int score(PlayerColor c) {
        if (c == PlayerColor.white) return whiteScore;
        if (c == PlayerColor.black) return blackScore;
        return 0;
    }*/
    
   
    @Nullable
    public TransferObject makeTurn(int x, int y) throws IllegalTurnException{

        CellState state = getCurrentPlayerColor();
        
        if (checkTurn(x, y)){
            board.putCell(state, x, y);
            int curDelta = 1;
            int oppDelta = 0;
            CellState oppColor = getOpponentColor();
            
            ArrayList<CellState> neighbs = board.getNeighbours(x, y);
    
            for (int i = x - 1; i <= x + 1 ; i++){
                if (i > 0 && i < board.getSize()) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (j > 0 && j < board.getSize()) {
                            if (board.getCellState(i, j) == oppColor) {
                                oppDelta--;
                                curDelta++;
                                board.putCell(getCurrentPlayerColor(), i, j);
                            }
                        }
                    }
                }
            }
            /*
            for (CellState st : board.getNeighbours(x, y)) {
                if (st == oppColor) {
                    oppDelta--;
                    curDelta++;
                    
                }
            }
            */
            switch (currentPlayer) {
                case white: {
                    whiteScore += curDelta;
                    blackScore += oppDelta;
                    currentPlayer = black;
                    break;
                }
                case black:{
                    blackScore += curDelta;
                    whiteScore += oppDelta;
                    currentPlayer = white;
                    break;
                }
            }
            return getTransferObject();
        }else{
            throw new IllegalTurnException();
        }
       /*
        if (checkTurn(x, y)){
            if (currentPlayer == black) {
                board.putCell(state, x, y);
                blackScore++;
                currentPlayer = PlayerColor.white;
            } else {
                board.putCell(state, x, y);
                whiteScore++;
                currentPlayer = black;
            }
            
            
            return getTransferObject();
        } else {
            throw new IllegalTurnException();
        }
        */
    }

    public boolean checkTurn(int x, int y) {
        if (x < 0 || x > 7) return false;
        if (y < 0 || y > 7) return false;
        if (!isOpponentColorNear(x, y) || ! board.isFree(x,y))return false;
        return true;
    }

    public TransferObject getTransferObject(){
        return new TransferObject(whiteScore, blackScore, board.getBoard(), currentPlayer);
    }

    private CellState getOpponentColor(){
        return (currentPlayer == black)? White: Black;
    }

    private CellState getCurrentPlayerColor(){
        return (currentPlayer == black)? Black: White;
    }


    private boolean isOpponentColorNear(int x, int y) {
        CellState color = getOpponentColor();
        for (CellState state : board.getNeighbours(x, y)) {
            if (state == color) return true;
        }
        return false;
    }

}
