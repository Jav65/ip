package command;

import exceptions.CommandException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand implements Command {

    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        return Ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
