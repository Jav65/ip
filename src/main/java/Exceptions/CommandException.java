package Exceptions;

public class CommandException extends Exception {
    public CommandException(String message) {
        super("\t What's this? You've uttered a complete and utter nonsense phrase.\n" +
                message);
    }
}
