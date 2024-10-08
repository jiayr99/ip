package totoro.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a task with a deadline
 */
public class Deadline extends Task{
    protected LocalDateTime by;

    /**
     * Constructs a deadline task with the given description and deadline
     *
     * @param description The description of the task
     * @param by The due date and time of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
