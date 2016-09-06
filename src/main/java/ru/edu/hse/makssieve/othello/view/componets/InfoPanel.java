package ru.edu.hse.makssieve.othello.view.componets;


import ru.edu.hse.makssieve.othello.controller.ExitGameListener;
import ru.edu.hse.makssieve.othello.controller.StartNewGameListener;
import ru.edu.hse.makssieve.othello.controller.TransferObject;
import ru.edu.hse.makssieve.othello.view.Informable;
import ru.edu.hse.makssieve.othello.view.events.ExitGameEvent;
import ru.edu.hse.makssieve.othello.view.events.StartNewGameEvent;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;

public class InfoPanel extends JMenuBar implements Informable, Updatable{

    private JLabel currentPlayer;
    private JLabel whiteScore;
    private JLabel blackScore;
    private StartNewGameEvent startNewGameEvent;
    private ExitGameEvent exitGameEvent;
    private ArrayList eventListeners;

    public InfoPanel(){
        super();
        startNewGameEvent = new StartNewGameEvent(this);
        exitGameEvent = new ExitGameEvent(this);
        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(new JMenuItem("New Game"));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addMouseListener(new ExitClickListener());
        gameMenu.add(exitItem);
        whiteScore = new JLabel();
        blackScore = new JLabel();
        currentPlayer = new JLabel();
        this.add(gameMenu);
        this.add(new JSeparator(JSeparator.VERTICAL));
        this.add(new JLabel("Current: "));
        this.add(currentPlayer);
        this.add(new JSeparator(JSeparator.VERTICAL));
        this.add(new JLabel("White Score: "));
        this.add(whiteScore);
        this.add(new JSeparator(JSeparator.VERTICAL));
        this.add(new JLabel("Black Score: "));
        this.add(blackScore);
    }

    private void setCurrentPlayer(String player){
        currentPlayer.setText(player);
    }

    private void setScore(int white, int black){
        whiteScore.setText(String.valueOf(white));
        blackScore.setText(String.valueOf(black));
    }

    public void update(TransferObject transferObject) {
        setScore(transferObject.getWhiteScore(), transferObject.getBlackScore());
        setCurrentPlayer(transferObject.getCurrentPlayer().name());
    }

    public int inform(String msg) {
        return 0;
    }
    private void fireNewGame(){

    }

    private void fireExitGame(){
        int res = JOptionPane.showConfirmDialog(this,"Exit Othello", "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            for (Object eventListener : eventListeners) {
                ((ExitGameListener) eventListener).exitGame(exitGameEvent);
            }
        }
    }

    public void setListener(EventListener listener) {
        eventListeners.add(listener);
    }

    private class ExitClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            fireExitGame();
        }
    }
}
