package sage;

import sage.exception.SageException;
import sage.task.TaskList;
import sage.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface of the task management application
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Display a welcome message to the users when the application starts
     */
    public String showWelcomeMessage() {
        return "Hi~~ It's Totoro!!\n" + "How can I help you today? ;)";
    }

    /**
     * Display a goodbye message to the users when they exit the application
     */
    public String showGoodbyeMessage() {
        return "Awww :(( Hope to see you again next time!";
    }


    public String showErrorMessage(SageException e) {
        return e.toString();
    }

    /**
     * Display the list of tasks to the user
     *
     * @param tasks The TaskList object containing the tasks to be displayed
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks at the moment, hurry and add some!!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return "Here are the tasks for you:\n" + sb;
    }

    public String showMarkedTask(Task task) {
        return "YAY! Another task completed! I will mark this task as done:\n" + task;
    }

    public String showUnmarkedTask(Task task) {
        return "Aw ok, please remember to complete this task later:\n" + task;
    }

    public String showAddedTask(Task task, int numberOfTasks) {
        return "A new task? I'm on it!! I will add this task to the list:\n" + task +
                "\nNow you have " + numberOfTasks +
                (numberOfTasks > 1 ? " tasks" : " task") + " in your list";
    }

    public String showDeletedTask(Task task, int numberOfTasks) {
        return "Got it! I will remove this task:\n" + task +
                "\nNow you have " + numberOfTasks +
                (numberOfTasks > 1 ? " tasks" : " task") + " in your list";
    }

    public String showSearchedTask(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Sorry I could not find any matching tasks :(";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return "Yay yay hope these are the tasks you are looking for:\n" + sb;
    }

    public String showScheduledTask(List<Task> tasks, LocalDate date) {
        if (tasks.isEmpty()) {
            return "Sorryy I could not find any tasks scheduled for " +
                    date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }

        StringBuilder response = new StringBuilder("I found the tasks scheduled for " +
                date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":\n");
        for (Task task : tasks) {
            response.append(task.toString()).append("\n");
        }
        return response.toString();
    }

    public String showHelp() {
        return "Need a hand?\n" + "Follow these Totoro commands:\n"
                + "1. bye: Use when you want to exit\n"
                + "2. help: When you need help on the command\n"
                + "3. list: Displays all the tasks in your list\n"
                + "4. mark <task number>: Marks your task to display as done\n"
                + "5. unmark <task number>: Unmarks your task to display as not done\n"
                + "6. todo <description>: Adds a todo task to your list\n"
                + "7. deadline <description> /by <dd/MM/yyyy HH:mm>: Adds a deadine task to your list\n"
                + "8. event <description> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>: Adds an event task to your list\n"
                + "9. delete <task number>: Deletes the task from your list\n"
                + "10. find <keyword>: Searches for tasks matching your keyword";
    }
}
