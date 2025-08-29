package command;

import exceptions.CommandException;
import exceptions.InvalidCommandException;
import storage.Storage;
import task.TaskList;
import task.ToDoTask;

public class ToDoCommand implements Command {
    private final String args;

    public ToDoCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the todo command to add a new todo task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        if (args.isBlank()) {
            throw new InvalidCommandException("todo <description>");
        }
        String response = taskList.addTask(new ToDoTask(args.trim()));
        storage.saveTasks(taskList);
        return response;
    }
}