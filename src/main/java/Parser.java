public class Parser {
    public Command parse(String input) throws SageException {
        String parts[] = input.split(" ", 2);
        String userCommand = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (userCommand) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(arguments));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(arguments));
            case "delete":
                return new DeleteCommand(Integer.parseInt(arguments));
            case "todo":
                return new ToDoCommand(arguments);
            case "deadline":
                return new DeadlineCommand(arguments);
            case "event":
                return new EventCommand(arguments);
            case "bye":
                return new ExitCommand();
            default:
                throw new SageException("Sorry, what do you mean? :p");
        }
    }
}
