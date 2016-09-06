package ru.edu.hse.makssieve.othello.model.exceptions;

public class WrongCoordinatesException extends IllegalTurnException {
    public WrongCoordinatesException() {super("Wrong coordinates!");}
}
