package stewie.command;

import java.time.LocalDateTime;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.EventTask;
import stewie.task.TaskList;
import stewie.util.Helper;

/**
 * Represents a command to add a event task.
 */
public class EventCommand implements Command {
    private final String args;

    /**
     * Creates a new event command with the given arguments.
     *
     * @param args The command arguments containing task description, start time, and end time.
     */
    public EventCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the event command to add a new event task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        String[] parts = args.split("\\s+/from\\s+|\\s+/to\\s+");
        if (parts.length < 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
            throw new InvalidCommandException("event <description> /from <dd/MM/yyyy> <HH:mm> "
                                                + "/to <dd/MM/yyyy> <HH:mm>");
        }
        LocalDateTime startTime = Helper.parseDateTime(parts[1].trim());
        LocalDateTime endTime = Helper.parseDateTime(parts[2].trim());
        if (startTime == null || endTime == null) {
            throw new InvalidCommandException("event <description> /from <dd/MM/yyyy> <HH:mm> "
                                                + "/to <dd/MM/yyyy> <HH:mm>");
        }

        String response = taskList.addTask(new EventTask(parts[0].trim(), startTime, endTime));
        storage.saveTasks(taskList);

        return response;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.EVENT;
    }
}
