package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.OutOfRangeException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.util.Helper;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand implements Command {
    private final String args;

    /**
     * Creates a new delete command with the given arguments.
     *
     * @param args The command arguments containing the task index to delete.
     */
    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the delete command to remove a task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        int idx = Helper.parseIndexOrThrow(args, "delete <index>\nExpected an integer index!");
        String response;
        try {
            response = taskList.deleteTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("There's no task at index " + idx);
        }
        storage.saveTasks(taskList);
        return response;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE;
    }
}
