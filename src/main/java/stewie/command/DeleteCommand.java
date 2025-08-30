package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.OutOfRangeException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.util.Helper;

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
