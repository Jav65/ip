package command;

import exceptions.CommandException;
import storage.Storage;
import task.TaskList;
import util.Helper;
import java.time.LocalDateTime;
import task.DeadlineTask;

import exceptions.InvalidCommandException;

public class DeadlineCommand implements Command {
    private final String args;

    public DeadlineCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the deadline command to add a new deadline task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        String[] parts = args.split("\\s+/by\\s+", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new InvalidCommandException("deadline <description> /by <dd/MM/yyyy> <HH:mm>");
        }
        LocalDateTime deadline = Helper.parseDateTime(parts[1].trim());
        if (deadline == null) {
            throw new InvalidCommandException("deadline <description> /by <dd/MM/yyyy> <HH:mm>");
        }
        String response = taskList.addTask(new DeadlineTask(parts[0].trim(), deadline));
        storage.saveTasks(taskList);

        return response;
    }
}
