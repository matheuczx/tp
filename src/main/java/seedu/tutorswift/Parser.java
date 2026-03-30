package seedu.tutorswift;

import seedu.tutorswift.command.Command;
import seedu.tutorswift.command.EditCommand;
import seedu.tutorswift.command.ExitCommand;
import seedu.tutorswift.command.DeleteCommand;
import seedu.tutorswift.command.ListCommand;
import seedu.tutorswift.command.AddCommand;
import seedu.tutorswift.command.FindCommand;
import seedu.tutorswift.command.ArchiveCommand;
import seedu.tutorswift.command.UnarchiveCommand;
import seedu.tutorswift.command.ListArchiveCommand;
import seedu.tutorswift.command.DeleteArchiveCommand;
import seedu.tutorswift.command.ScheduleCommand;
import seedu.tutorswift.command.GradeCommand;
import seedu.tutorswift.command.RemarkCommand;
import seedu.tutorswift.command.FeeCommand;
import seedu.tutorswift.command.PaidCommand;
import seedu.tutorswift.command.UnpaidCommand;
import seedu.tutorswift.command.UpcomingCommand;
import seedu.tutorswift.command.RemoveGradeCommand;
import seedu.tutorswift.command.RemoveRemarkCommand;

import java.time.YearMonth;
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
    private static final String PREFIX_SUBJECT = "sub/";
    private static final String PREFIX_DAY = "day/";
    private static final String PREFIX_START = "start/";
    private static final String PREFIX_END = "end/";
    private static final String PREFIX_MARK = "m/";
    private static final String PREFIX_GRADE = "g/";
    private static final String PREFIX_REMARK = "r/";
    private static final String PREFIX_FEE = "f/";
    private static final String PREFIX_PERIOD = "ym/";
    private static final String[] ALL_PREFIXES = {
        PREFIX_NAME,
        PREFIX_LEVEL,
        PREFIX_SUBJECT,
        PREFIX_DAY,
        PREFIX_START,
        PREFIX_END,
        PREFIX_MARK,
        PREFIX_GRADE,
        PREFIX_REMARK,
        PREFIX_FEE,
        PREFIX_PERIOD
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
        case "archive":
            return new ArchiveCommand(parseIndex(arguments));
        case "unarchive":
            return new UnarchiveCommand(parseIndex(arguments));
        case "list-archive":
            return new ListArchiveCommand();
        case "delete-archive":
            return new DeleteArchiveCommand(parseIndex(arguments));
        case "schedule":
            return parseSchedule(arguments);
        case "grade":
            return parseGrade(arguments);
        case "remark":
            return parseRemark(arguments);
        case "fee":
            return parseFee(arguments);
        case "paid":
            return parsePaid(arguments);
        case "unpaid":
            return parseUnpaid(arguments);
        case "upcoming":
            return new UpcomingCommand();
        case "remove-grade":
            return parseRemoveGrade(arguments);
        case "remove-remark":
            return parseRemoveRemark(arguments);
        default:
            throw new TutorSwiftException("I'm sorry, but I don't know what '" + userInput + "' means :(\n");
        }
    }

    /**
     * Helper to parse and validate index arguments.
     */
    private static int parseIndex(String args) throws TutorSwiftException {
        if (args.trim().isEmpty()) {
            throw new TutorSwiftException("This command requires a numeric index!");
        }
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Please provide a valid positive integer as index.");
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
            throw new TutorSwiftException("Name (n/), level (l/), or subject (sub/) cannot be empty");
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
            throw new TutorSwiftException("Invalid format. Use: schedule n/NAME day/DAY start/HH:mm end/HH:mm");
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

    private static Command parseGrade(String args) throws TutorSwiftException {
        if (args.isEmpty()) {
            throw new TutorSwiftException("Usage: grade INDEX m/ASSESSMENT g/SCORE");
        }

        String[] parts = args.trim().split("\\s+", 2);

        int index;
        try {
            index = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Invalid index.");
        }

        String remaining = parts.length > 1 ? parts[1] : "";

        String assessment = getValueByPrefix(remaining, PREFIX_MARK);
        String scoreStr = getValueByPrefix(remaining, PREFIX_GRADE);

        if (assessment == null || scoreStr == null) {
            throw new TutorSwiftException("Usage: grade INDEX m/ASSESSMENT g/SCORE");
        }

        int score;
        try {
            score = Integer.parseInt(scoreStr);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Score must be a number.");
        }

        return new GradeCommand(index, assessment, score);
    }

    private static Command parseRemoveGrade(String args) throws TutorSwiftException {
        if (args.isEmpty()) {
            throw new TutorSwiftException("Usage: remove-grade INDEX m/ASSESSMENT");
        }

        String[] parts = args.trim().split("\\s+", 2);

        int index;
        try {
            index = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Invalid index.");
        }

        String remaining = parts.length > 1 ? parts[1] : "";
        String assessment = getValueByPrefix(remaining, PREFIX_MARK);

        if (assessment == null) {
            throw new TutorSwiftException("Usage: remove-grade INDEX m/ASSESSMENT");
        }

        return new RemoveGradeCommand(index, assessment);
    }

    private static Command parseRemark(String args) throws TutorSwiftException {
        if (args.isEmpty()) {
            throw new TutorSwiftException("Usage: remark INDEX r/REMARK");
        }

        String[] parts = args.trim().split("\\s+", 2);

        int index;
        try {
            index = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Invalid index.");
        }

        String remaining = parts.length > 1 ? parts[1] : "";

        String remark = getValueByPrefix(remaining, PREFIX_REMARK);

        if (remark == null) {
            throw new TutorSwiftException("Usage: remark INDEX r/REMARK");
        }

        return new RemarkCommand(index, remark);
    }

    private static Command parseRemoveRemark(String args) throws TutorSwiftException {
        if (args.isEmpty()) {
            throw new TutorSwiftException("Usage: remove-remark INDEX");
        }

        int index;
        try {
            index = Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Invalid index.");
        }

        return new RemoveRemarkCommand(index);
    }

    /**
     * Parses the "fee" command: fee INDEX f/AMOUNT
     */
    private static Command parseFee(String args) throws TutorSwiftException {
        if (args.trim().isEmpty()) {
            throw new TutorSwiftException("Usage: fee INDEX f/AMOUNT");
        }

        String[] parts = args.trim().split("\\s+", 2);
        int index;
        try {
            index = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Invalid index. Usage: fee INDEX f/AMOUNT");
        }

        if (index <= 0) {
            throw new TutorSwiftException("Index must be a positive integer.");
        }

        String remaining = parts.length > 1 ? parts[1] : "";
        String feeStr = getValueByPrefix(remaining, PREFIX_FEE);

        if (feeStr == null) {
            throw new TutorSwiftException("Missing fee prefix. Usage: fee INDEX f/AMOUNT");
        }

        int fee;
        try {
            fee = Integer.parseInt(feeStr);
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Fee must be a positive integer.");
        }

        if (fee <= 0) {
            throw new TutorSwiftException("Fee must be a positive integer.");
        }

        return new FeeCommand(index, fee);
    }

    /**
     * Parses the "paid" command: paid INDEX [ym/YYYY-MM]
     */
    private static Command parsePaid(String args) throws TutorSwiftException {
        int index = parseIndexFromArgs(args);
        YearMonth month = parseYearMonth(args);
        return new PaidCommand(index, month);
    }

    /**
     * Parses the "unpaid" command: unpaid INDEX [ym/YYYY-MM]
     */
    private static Command parseUnpaid(String args) throws TutorSwiftException {
        int index = parseIndexFromArgs(args);
        YearMonth month = parseYearMonth(args);
        return new UnpaidCommand(index, month);
    }

    /**
     * Extracts the index from the start of args (before any prefixes).
     */
    private static int parseIndexFromArgs(String args) throws TutorSwiftException {
        if (args.trim().isEmpty()) {
            throw new TutorSwiftException("This command requires a numeric index!");
        }
        String[] parts = args.trim().split("\\s+", 2);
        try {
            int index = Integer.parseInt(parts[0]);
            if (index <= 0) {
                throw new TutorSwiftException("Index must be a positive integer.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TutorSwiftException("Please provide a valid positive integer as index.");
        }
    }

    /**
     * Extracts ym/YYYY-MM from args, defaulting to current month if not present.
     * Validates that the format is exactly YYYY-MM and month is between 01 and 12.
     */
    private static YearMonth parseYearMonth(String args) throws TutorSwiftException {
        boolean prefixPresent = args.contains(PREFIX_PERIOD);
        String ymStr = getValueByPrefix(args, PREFIX_PERIOD);

        if (!prefixPresent) {
            throw new TutorSwiftException("Missing ym/ prefix. Usage: paid/unpaid INDEX ym/YYYY-MM");
        }

        if (ymStr == null) {
            throw new TutorSwiftException("ym/ requires a value. Usage: paid/unpaid INDEX ym/YYYY-MM");
        }

        if (!ymStr.trim().matches("\\d{4}-\\d{2}")) {
            throw new TutorSwiftException("Invalid format. Use ym/YYYY-MM (e.g. ym/2026-03).");
        }

        try {
            YearMonth ym = YearMonth.parse(ymStr.trim());
            int month = ym.getMonthValue();
            if (month < 1 || month > 12) {
                throw new TutorSwiftException("Invalid month. Month must be between 01 and 12.");
            }
            return ym;
        } catch (TutorSwiftException e) {
            throw e;
        } catch (Exception e) {
            throw new TutorSwiftException("Invalid year-month. Use ym/YYYY-MM (e.g. ym/2026-03).");
        }
    }
}
