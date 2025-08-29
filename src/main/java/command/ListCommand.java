package command;

import exceptions.CommandException;
import storage.Storage;
import task.TaskList;

public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        return taskList.listTask();
    }
}
