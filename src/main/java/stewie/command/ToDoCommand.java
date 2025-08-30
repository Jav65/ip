
package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.task.ToDoTask;

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