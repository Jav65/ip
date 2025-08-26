package Exceptions;

public class OutOfRangeException extends CommandException {
    public OutOfRangeException(String detail) {
        super("\t The numbers you've provided are outside the acceptable parameters.\n"
                    + detail);
    }
}
