package stewie.command;

import stewie.exceptions.CommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;

public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        return taskList.listTask();
    }
}
