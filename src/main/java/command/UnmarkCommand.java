package command;

import exceptions.CommandException;
import exceptions.OutOfRangeException;
import storage.Storage;
import task.TaskList;
import util.Helper;

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
