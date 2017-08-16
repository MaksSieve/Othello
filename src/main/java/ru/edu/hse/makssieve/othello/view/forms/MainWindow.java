package ru.edu.hse.makssieve.othello.view.forms;

import ru.edu.hse.makssieve.othello.controller.BoardListener;
import ru.edu.hse.makssieve.othello.controller.ExitListener;
import ru.edu.hse.makssieve.othello.controller.NewGameListener;
import ru.edu.hse.makssieve.othello.controller.TransferObject;
import ru.edu.hse.makssieve.othello.view.componets.BoardPanel;
import ru.edu.hse.makssieve.othello.view.componets.InfoPanel;
import ru.edu.hse.makssieve.othello.view.events.StartNewGameEvent;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    private InfoPanel infoPanel = new InfoPanel();
    private BoardPanel boardPanel = new BoardPanel();
    private NewGameListener newGameListener;

    public MainWindow(){
        super("Othello");
        this.setVisible(true);
        this.setSize(new Dimension(530,610));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(new Point(425,75));
        this.setLayout(new BorderLayout());

        this.add(infoPanel, BorderLayout.NORTH);
        this.add(boardPanel, BorderLayout.CENTER);
    }

    public void updateView(TransferObject transferObject) {
        infoPanel.update(transferObject);
        boardPanel.update(transferObject);
    }

    /*public void setCellView(CellState cellState, int x, int y){
        boardPanel.setCellView(cellState, x, y);
    }*/

    public void setBoardListener(BoardListener listener){
        boardPanel.setListeners(listener);
    }

    public void setExitGameListener(ExitListener listener) {
        infoPanel.setListener(listener);
    }
    /*public void setStartNewGameListener(NewGameListener listener) {
        infoPanel.setListener(listener);
    }*/


    public void setStartNewGameListener(NewGameListener newGameListener) {
        this.newGameListener = newGameListener;
    }

   private void fireStartNewGame(){
        newGameListener.startGame(new StartNewGameEvent(this));
    }

    public void alert(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Alert!", JOptionPane.WARNING_MESSAGE);
    }

    public int inform(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, msg, JOptionPane.YES_NO_OPTION);
    }
}
