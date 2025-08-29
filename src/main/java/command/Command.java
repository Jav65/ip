package command;

import exceptions.CommandException;
import task.TaskList;
import storage.Storage;

public interface Command {
    String execute(TaskList taskList, Storage storage) throws CommandException;

    /**
     * Indicates if this command should exit the program.
     * Most commands don't exit
     */
    default boolean isExit() {
        return false;
    }
}
