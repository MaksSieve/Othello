package ru.edu.hse.makssieve.othello.view.componets;


import ru.edu.hse.makssieve.othello.controller.ExitListener;
import ru.edu.hse.makssieve.othello.controller.NewGameListener;
import ru.edu.hse.makssieve.othello.controller.TransferObject;
import ru.edu.hse.makssieve.othello.view.Informable;
import ru.edu.hse.makssieve.othello.view.events.ExitGameEvent;
import ru.edu.hse.makssieve.othello.view.events.StartNewGameEvent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventListener;

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
        
        // Create main menu
        JMenu gameMenu = new JMenu("Game");
        
        // Create elements of main menu
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        // Addin' listenters to elements of main menu
        newGameItem.addMouseListener(new NewGameClickListener());
        exitItem.addMouseListener(new ExitClickListener());
        
        // Addin' elements into menu
        gameMenu.add(exitItem);
        gameMenu.add(newGameItem);
        
        
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
        int res = JOptionPane.showConfirmDialog(this,"Start new game?", "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            for (Object eventListener : eventListeners) {
                ((NewGameListener) eventListener).startGame(startNewGameEvent);
            }
        }
    }

    private void fireExitGame(){
        int res = JOptionPane.showConfirmDialog(this,"Exit Othello", "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            for (Object eventListener : eventListeners) {
                ((ExitListener) eventListener).exitGame(exitGameEvent);
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
    
    private class NewGameClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            fireNewGame();
        }
    }
}
