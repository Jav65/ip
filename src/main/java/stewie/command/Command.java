package stewie.command;

import stewie.exceptions.CommandException;
import stewie.task.TaskList;
import stewie.storage.Storage;

public interface Command {
    String execute(TaskList taskList, Storage storage) throws CommandException;

    /**
     * Indicates if this stewie.command should exit the program.
     * Most commands don't exit
     */
    default boolean isExit() {
        return false;
    }
}
