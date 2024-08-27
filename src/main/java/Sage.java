import java.util.ArrayList;
import java.util.Scanner;

public class Sage {
    public static void main(String[] args) {
        //Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String input;

        //Greet the user
        System.out.println("______________________________________________________");
        System.out.println("Hello! I'm Jia");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________________");

        //Read user input until they say bye
        do {
                input = scanner.nextLine().trim(); //trim the white space

                try {
                    if (input.equalsIgnoreCase("list")) {
                        System.out.println("______________________________________________________");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("______________________________________________________");
                    } else if (input.startsWith("mark")) {
                        int taskNumber = Integer.parseInt(input.substring(4).trim()) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            tasks.get(taskNumber).markAsDone();
                            System.out.println("______________________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(" " + tasks.get(taskNumber));
                            System.out.println("______________________________________________________");
                        } else {
                            System.out.println("Invalid task number :(");
                        }
                    } else if (input.startsWith("unmark")) {
                        int taskNumber = Integer.parseInt(input.substring(6).trim()) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            tasks.get(taskNumber).markAsNotDone();
                            System.out.println("______________________________________________________");
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(" " + tasks.get(taskNumber));
                            System.out.println("______________________________________________________");
                        } else {
                            System.out.println("Invalid task number :(");
                        }
                    } else if (input.startsWith("delete")) {
                        int taskNumber = Integer.parseInt(input.substring(6).trim()) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            Task removedTask = tasks.remove(taskNumber);
                            System.out.println("______________________________________________________");
                            System.out.println("OK! I will remove this task:");
                            System.out.println(" " + removedTask);
                            System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list");
                            System.out.println("______________________________________________________");
                        }
                    } else if (input.startsWith("todo")) {
                        String description = input.substring(4).trim();
                        if (description.isEmpty()) {
                            throw new JiaException("Oh No! Please tell me what task to do :(");
                        }
                        tasks.add(new ToDo(description));
                        System.out.println("______________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
                        System.out.println("______________________________________________________");
                    } else if (input.startsWith("deadline")) {
                        String[] parts = input.substring(8).split(" /by ");
                        if (parts.length == 2) {
                            String description = parts[0].trim();
                            String by = parts[1].trim();
                            tasks.add(new Deadline(description, by));
                            System.out.println("______________________________________________________");
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
                            System.out.println("______________________________________________________");
                        } else {
                            throw new JiaException("You need to add a task and a deadline!! -_-");
                        }
                    } else if (input.startsWith("event")) {
                        String[] parts = input.substring(5).split(" /from | /to ");
                        if (parts.length == 3) {
                            String description = parts[0].trim();
                            String from = parts[1].trim();
                            String to = parts[2].trim();
                            tasks.add(new Event(description, from, to));
                            System.out.println("______________________________________________________");
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
                            System.out.println("______________________________________________________");
                        } else {
                            throw new JiaException("What time is your event?? :o");
                        }
                    } else if (!input.equalsIgnoreCase("bye")) {
                        throw new JiaException("Sorry what do you mean? :p");
                    }
                } catch (JiaException e) {
                System.out.println("______________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("______________________________________________________");
            }
        } while (!input.equalsIgnoreCase("bye"));
        //Exit
        System.out.println("______________________________________________________");
        System.out.println(" " + "Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");

        scanner.close();
    }
}
