package sage;

import sage.storage.Storage;
import sage.ui.Ui;
import sage.task.TaskList;
import sage.parser.Parser;
import sage.command.Command;
import sage.exception.SageException;

import java.util.Scanner;

public class Sage {
    public static final String DIRECTORY_PATH = "data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "/sage.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    public Sage(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showMessage("Unable to load tasks. Starting with an empty list");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (SageException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Sage chatbot = new Sage(FILE_PATH);
        chatbot.run();
    }
}
