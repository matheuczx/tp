package seedu.tutorswift;

import seedu.tutorswift.command.Command;
import seedu.tutorswift.command.EditCommand;
import seedu.tutorswift.command.ExitCommand;
import seedu.tutorswift.command.DeleteCommand;
import seedu.tutorswift.command.ListCommand;
import seedu.tutorswift.command.AddCommand;

/**
 * This class contains the logic to interpret strings and return the
 * appropriate {@link Command} objects.
 */
public class Parser {

    /**
     * Parses the full user input string and returns the corresponding command.
     *
     * @param userInput The raw input string from the user.
     * @return A {@link Command} object ready for execution.
     *
     */
    public static Command parseUserInput(String userInput)  throws TutorSwiftException {
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
        if (args.isEmpty()) {
            throw new TutorSwiftException("Edit command requires an index!");
        }

        // 1. Extract the index (the first number before any prefixes)
        String[] parts = args.trim().split("\\s+", 2);
        int index;
        try {
            index = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("The index must be a positive integer.");
        }

        String remainingArgs = parts.length > 1 ? " " + parts[1] : "";

        // 2. Extract values based on prefixes
        String name = getValueByPrefix(remainingArgs, "n/");
        String level = getValueByPrefix(remainingArgs, "l/");
        String subject = getValueByPrefix(remainingArgs, "s/");

        // 3. Validation: At least one field must be edited
        if (name == null && level == null && subject == null) {
            throw new TutorSwiftException("At least one field to edit must be provided (n/, l/, or s/).");
        }

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

        String name = getValueByPrefix(args, "n/");
        String level = getValueByPrefix(args, "l/");
        String subject = getValueByPrefix(args, "s/");

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
        String[] allPrefixes = {"n/", "l/", "s/"};
        for (String p : allPrefixes) {
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

}
