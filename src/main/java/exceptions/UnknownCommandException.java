package Exceptions;

public class UnknownCommandException extends CommandException {
    public UnknownCommandException(String available) {
        super("\t The available commands are listed for your feeble mind to comprehend: \n"
                    + available);
    }
}
