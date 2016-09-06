package ru.edu.hse.makssieve.othello.view.forms;

import ru.edu.hse.makssieve.othello.controller.BoardListener;
import ru.edu.hse.makssieve.othello.controller.ExitGameListener;
import ru.edu.hse.makssieve.othello.controller.StartNewGameListener;
import ru.edu.hse.makssieve.othello.controller.TransferObject;
import ru.edu.hse.makssieve.othello.model.enums.PlayerColor;
import ru.edu.hse.makssieve.othello.view.Alertable;
import ru.edu.hse.makssieve.othello.view.Informable;
import ru.edu.hse.makssieve.othello.view.componets.BoardPanel;
import ru.edu.hse.makssieve.othello.view.componets.InfoPanel;
import ru.edu.hse.makssieve.othello.view.events.StartNewGameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame{

    private InfoPanel infoPanel = new InfoPanel();
    private BoardPanel boardPanel = new BoardPanel();
    //private StartNewGameListener startNewGameListener;

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

    public void setExitGameListener(ExitGameListener listener) {
        infoPanel.setListener(listener);
    }
    public void setStartNewGameListener(StartNewGameListener listener) {
        infoPanel.setListener(listener);
    }


    /*public void setStartNewGameListener(StartNewGameListener startNewGameListener) {
        this.startNewGameListener = startNewGameListener;
    }*/

/*    private void fireStartNewGame(){
        startNewGameListener.startGame(new StartNewGameEvent(this));
    }*/

    public void alert(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Alert!", JOptionPane.WARNING_MESSAGE);
    }

    public int inform(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, msg, JOptionPane.YES_NO_OPTION);
    }
}
