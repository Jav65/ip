package Exceptions;

public class InvalidCommandException extends CommandException {
    public InvalidCommandException(String message) {
        super("\t I've provided the correct format for your simpleton mind.\n" +
                "\t Follow it precisely: " + message);
    }
}
