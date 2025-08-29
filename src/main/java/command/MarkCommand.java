package command;

import exceptions.CommandException;
import exceptions.OutOfRangeException;
import storage.Storage;
import task.TaskList;
import util.Helper;

public class MarkCommand implements Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the mark command to mark a task as done.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        int idx = Helper.parseIndexOrThrow(args, "mark <index>\n\t Expected an integer index!");
        String response;
        try {
            response = taskList.markTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("\t There's no task at index " + idx);
        }
        storage.saveTasks(taskList);
        return response;
    }
}
