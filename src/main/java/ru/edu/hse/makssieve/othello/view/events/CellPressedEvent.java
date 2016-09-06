package ru.edu.hse.makssieve.othello.view.events;

import java.util.EventObject;


public class CellPressedEvent extends EventObject {
    
    private int x;
    private int y;
    public CellPressedEvent(Object source, int x, int y) {
        super(source);
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }
}
