package ru.edu.hse.makssieve.othello.view.componets;

import ru.edu.hse.makssieve.othello.controller.BoardListener;
import ru.edu.hse.makssieve.othello.controller.TransferObject;
import ru.edu.hse.makssieve.othello.model.enums.CellState;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel implements Updatable {

    private ArrayList<ArrayList<CellView>> board = new ArrayList<ArrayList<CellView>>(8);

    public BoardPanel() {
        super();
        this.setMinimumSize(new Dimension(515,515));
        this.setBorder(new BorderUIResource.LineBorderUIResource(Color.black, 1));
        this.setBackground(Color.LIGHT_GRAY);
        GridLayout layout = new GridLayout(8, 8);
        layout.setHgap(5);
        layout.setVgap(5);
        this.setLayout(layout);
        this.setBounds(new Rectangle(480,480));

        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<CellView>(8));
            for (int j = 0; j < 8; j++) {
                board.get(i).add(j, new CellView(CellState.Free,i,j));
                this.add(board.get(i).get(j));
            }
        }
    }

    public void setListeners(BoardListener listener) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.get(i).get(j).addCellPressedListener(listener);
            }
        }
    }

   /* public void setCellView(CellState cellState, int x, int y){
        board[x][y].setState(cellState);
    }
    */
    private void updateBoard(ArrayList<ArrayList<CellState>> board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board.get(i).get(j).setState(board.get(i).get(j));
            }
        }
    }

    public void update(TransferObject transferObject) {
        updateBoard(transferObject.getBoard());
    }
}
