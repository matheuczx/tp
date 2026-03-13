package seedu.tutorswift;

import seedu.tutorswift.command.Command;
import seedu.tutorswift.command.EditCommand;
import seedu.tutorswift.command.ExitCommand;

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
        case "bye":
            return new ExitCommand();
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
}
