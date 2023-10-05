package br.java.projeto.poo.exceptions;

public class InvalidIdException extends Exception {
    public InvalidIdException() {
        super("id inválido");
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
