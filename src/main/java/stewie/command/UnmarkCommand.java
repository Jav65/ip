package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.OutOfRangeException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.util.Helper;

public class UnmarkCommand implements Command {
    private final String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the unmark command to mark a task as not done.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        int idx = Helper.parseIndexOrThrow(args, "unmark <index>\n\t Expected an integer index!");
        String response;

        try {
            response = taskList.unmarkTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("\t There's no task at index " + idx + ".");
        }

        storage.saveTasks(taskList);
        return response;
    }
}