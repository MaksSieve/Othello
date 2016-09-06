package ru.edu.hse.makssieve.othello.model.exceptions;


public class IllegalTurnException extends RuntimeException{

    public IllegalTurnException() {
        super("Illegal turn");
    }

    IllegalTurnException(String msg) { super(msg);}
}
