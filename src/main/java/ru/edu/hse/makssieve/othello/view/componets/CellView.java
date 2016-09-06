package ru.edu.hse.makssieve.othello.view.componets;

import ru.edu.hse.makssieve.othello.controller.BoardListener;
import ru.edu.hse.makssieve.othello.model.enums.CellState;
import ru.edu.hse.makssieve.othello.view.events.CellPressedEvent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class CellView extends JLabel{

    private BoardListener listener;
    private CellPressedEvent event;

    private CellState state;
    private final int x;
    private final int y;

    private ImageIcon greyIcon;
    private ImageIcon whiteIcon;
    private ImageIcon blackIcon;

    CellView(CellState state, int x, int y) {
        this.state = state;
        this.x = x;
        this.y = y;

        event = new CellPressedEvent(this, this.x, this.y);

        greyIcon = new ImageIcon(CellView.class.getResource("/gray.png"));
        whiteIcon = new ImageIcon(CellView.class.getResource("/white.png"));
        blackIcon = new ImageIcon(CellView.class.getResource("/black.png"));

        changeView();

        event = new CellPressedEvent(this, this.x, this.y);
        addMouseListener(new MouseClickListener());

    }

    private void changeView(){
        if (state == CellState.Black) this.setIcon(blackIcon);
        if (state == CellState.White) this.setIcon(whiteIcon);
        if (state == CellState.Free) this.setIcon(greyIcon);
    }

    void setState(CellState cellState) {
        this.state = cellState;
        changeView();
    }

    void addCellPressedListener(BoardListener listener){
        this.listener = listener;
    }

    /*public void removeCellPressedListener(){
        this.listener = null;
    }*/

    private void fireCellPressed() {
        listener.cellPressed(event);
    }

    private class MouseClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            fireCellPressed();
        }
    }
}
