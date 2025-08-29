package command;

import exceptions.CommandException;
import exceptions.OutOfRangeException;
import storage.Storage;
import task.TaskList;
import util.Helper;

public class DeleteCommand implements Command {
    private final String args;

    public DeleteCommand(String args) {
        this.args = args;
    }
    /**
     * Handles the delete command to remove a task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        int idx = Helper.parseIndexOrThrow(args, "delete <index>\n\t Expected an integer index!");
        String response;
        try {
            response = taskList.deleteTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("\t There's no task at index " + idx);
        }
        storage.saveTasks(taskList);
        return response;
    }
}
