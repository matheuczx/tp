package seedu.tutorswift;

import seedu.tutorswift.command.Command;
import seedu.tutorswift.command.EditCommand;
import seedu.tutorswift.command.ExitCommand;
import seedu.tutorswift.command.DeleteCommand;
import seedu.tutorswift.command.ListCommand;
import seedu.tutorswift.command.AddCommand;
import seedu.tutorswift.command.FindCommand;
import seedu.tutorswift.command.ScheduleCommand;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * This class contains the logic to interpret strings and return the
 * appropriate {@link Command} objects.
 */
public class Parser {

    private static final String PREFIX_NAME = "n/";
    private static final String PREFIX_LEVEL = "l/";
    private static final String PREFIX_SUBJECT = "s/";
    private static final String PREFIX_DAY = "d/";
    private static final String PREFIX_START = "s/";
    private static final String PREFIX_END = "e/";
    private static final String[] ALL_PREFIXES = {
        PREFIX_NAME,
        PREFIX_LEVEL,
        PREFIX_SUBJECT,
        PREFIX_DAY,
        PREFIX_START,
        PREFIX_END
    };

    /**
     * Parses the full user input string and returns the corresponding command.
     *
     * @param userInput The raw input string from the user.
     * @return A {@link Command} object ready for execution.
     */
    public static Command parseUserInput(String userInput)  throws TutorSwiftException {
        if (userInput.trim().isEmpty()) {
            throw new TutorSwiftException("Please enter a command."
                    + " Here are the available commands: add, edit, delete, list, exit.");
        }

        String[] parts = userInput.trim().split(" ", 2);
        String commandName = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandName) {
        case "edit":
            return parseEdit(arguments);
        case "delete":
            return parseDelete(arguments);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "add":
            return parseAdd(arguments);
        case "find":
            return parseFind(arguments);
        case "schedule":
            return parseSchedule(arguments);
        default:
            throw new TutorSwiftException("I'm sorry, but I don't know what '" + userInput + "' means :(\n");
        }
    }


    /**
     * Parses arguments in the context of the edit student command.
     *
     * @param args The raw user input following the "edit" command word.
     * @return An {@code EditCommand} with index, name , level, subject.
     */
    private static Command parseEdit(String args) throws TutorSwiftException {
        assert args != null : "Parser should not receive a null string from readUserInput";
        if (args.isEmpty()) {
            throw new TutorSwiftException("Edit command requires an index!"
                    + " Usage: edit INDEX n/NAME l/LEVEL s/SUBJECT");
        }

        // Extract the index (the first number before any prefixes)
        String[] parts = args.trim().split("\\s+", 2);
        int index;
        try {
            index = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("'" + parts[0] + "' is not a valid number."
                    + " Please provide a numeric index.");
        }

        if (index <= 0) {
            throw new TutorSwiftException("The index must be a positive integer.");
        }

        // Check that values are not just whitespaces
        String remainingArgs = parts.length > 1 ? " " + parts[1] : "";

        // Extract values based on prefixes
        String name = getValueByPrefix(remainingArgs, PREFIX_NAME);
        String level = getValueByPrefix(remainingArgs, PREFIX_LEVEL);
        String subject = getValueByPrefix(remainingArgs, PREFIX_SUBJECT);

        // At least one field must be edited
        if (name == null && level == null && subject == null) {
            throw new TutorSwiftException("Name (n/), level (l/), or subject (s/) cannot be empty");
        }
        assert (name != null || level != null || subject != null) : "name, level or subject field should not be null.";

        return new EditCommand(index, name, level, subject);
    }

    /**
     * Parses the arguments for the "add" command.
     *
     * @param args The raw argument string from the user.
     * @return An {@code AddCommand} containing the new student details.
     * @throws TutorSwiftException If any required parameters (n/, l/, s/) are missing.
     */
    private static Command parseAdd(String args) throws TutorSwiftException {
        if (args.isEmpty()) {
            throw new TutorSwiftException("The add command must have name (n/), level (l/), and subject (s/)!");
        }

        String name = getValueByPrefix(args, PREFIX_NAME);
        String level = getValueByPrefix(args, PREFIX_LEVEL);
        String subject = getValueByPrefix(args, PREFIX_SUBJECT);

        if (name == null || level == null || subject == null) {
            throw new TutorSwiftException("Missing parameters! Usage: add n/NAME l/LEVEL s/SUBJECT");
        }

        Student newStudent = new Student(name, level, subject);
        return new AddCommand(newStudent);
    }

    private static String getValueByPrefix(String args, String prefix) {
        if (!args.contains(prefix)) {
            return null;
        }
        int startIndex = args.indexOf(prefix) + prefix.length();
        int endIndex = args.length();

        // Check if there's another prefix after this one to determine the end
        for (String p : ALL_PREFIXES) {
            int nextPrefixIndex = args.indexOf(p, startIndex);
            if (nextPrefixIndex != -1 && nextPrefixIndex < endIndex) {
                endIndex = nextPrefixIndex;
            }
        }

        String result = args.substring(startIndex, endIndex).trim();
        return result.isEmpty() ? null : result;
    }

    /**
     * Parses arguments in the context of the delete student command.
     *
     * @param args The raw user input following the "delete" command word.
     * @return A {@code DeleteCommand} with the specified student index.
     * @throws TutorSwiftException If the index is missing or not a valid positive integer.
     */
    private static Command parseDelete(String args) throws TutorSwiftException {
        assert args != null : "Parser should not receive a null string from readUserInput";
        if (args.isEmpty()) {
            throw new TutorSwiftException("Delete command requires an index!");
        }

        String trimmed = args.trim();
        int index;
        try {
            index = Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("The index must be a positive integer.");
        }

        if (index <= 0) {
            throw new TutorSwiftException("The index must be a positive integer.");
        }

        return new DeleteCommand(index);
    }

    /**
     * Parses arguments in the context of the find student command.
     *
     * @param args The raw user input following the "find" command word.
     * @return A {@code FindCommand} containing the search criteria.
     * @throws TutorSwiftException If no valid search prefixes are provided.
     */
    private static Command parseFind(String args) throws TutorSwiftException {
        assert args != null : "Arguments should not be null";

        if (args.isEmpty()) {
            throw new TutorSwiftException("Find command requires at least one prefix (n/, s/, l/).");
        }

        String name = getValueByPrefix(args, PREFIX_NAME);
        String level = getValueByPrefix(args, PREFIX_LEVEL);
        String subject = getValueByPrefix(args, PREFIX_SUBJECT);

        if (name == null && subject == null && level == null) {
            throw new TutorSwiftException("Please provide at least one search field (n/(name), s/(subject), l/(lvl).");
        }

        return new FindCommand(name, subject, level);
    }

    private static Command parseSchedule(String args) throws TutorSwiftException {
        String name = getValueByPrefix(args, PREFIX_NAME);
        String dayStr = getValueByPrefix(args, PREFIX_DAY);
        String startStr = getValueByPrefix(args, PREFIX_START);
        String endStr = getValueByPrefix(args, PREFIX_END);

        if (name == null || dayStr == null || startStr == null || endStr == null) {
            throw new TutorSwiftException("Invalid format. Use: schedule n/NAME d/DAY s/HH:mm e/HH:mm");
        }

        try {
            DayOfWeek day = DayOfWeek.valueOf(dayStr.trim().toUpperCase());
            LocalTime startTime = LocalTime.parse(startStr.trim());
            LocalTime endTime = LocalTime.parse(endStr.trim());

            return new ScheduleCommand(name.trim(), day, startTime, endTime);

        } catch (IllegalArgumentException e) {
            throw new TutorSwiftException("Invalid day. Please use full day names (e.g., Monday).");
        } catch (DateTimeParseException e) {
            throw new TutorSwiftException("Invalid time format. Please use 24-hour HH:mm format (e.g., 14:00).");
        }
    }
}
