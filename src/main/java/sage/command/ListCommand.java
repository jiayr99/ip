package sage.command;

import sage.task.TaskList;
import sage.storage.Storage;
import sage.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
