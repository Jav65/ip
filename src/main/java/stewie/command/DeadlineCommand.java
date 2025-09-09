package stewie.command;

import java.time.LocalDateTime;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.DeadlineTask;
import stewie.task.TaskList;
import stewie.util.Helper;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand implements Command {
    private static final String USAGE_MESSAGE = "deadline <description> /by <dd/MM/yyyy> <HH:mm>";
    private final String args;

    /**
     * Creates a new deadline command with the given arguments.
     *
     * @param args The command arguments containing task description and deadline.
     */
    public DeadlineCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the deadline command to add a new deadline task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        String[] parts = args.split("\\s+/by\\s+", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new InvalidCommandException(USAGE_MESSAGE);
        }
        LocalDateTime deadline = Helper.parseDateTime(parts[1].trim());
        if (deadline == null) {
            throw new InvalidCommandException(USAGE_MESSAGE);
        }
        String response = taskList.addTask(new DeadlineTask(parts[0].trim(), deadline));
        storage.saveTasks(taskList);

        assert response != null : "Final response should not be null";
        return response;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DEADLINE;
    }
}
