package seedu.tutorswift.command;

import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;
import java.time.YearMonth;

/**
 * Represents a command to calculate and display the total income generated
 * across all active students for a given month.
 */

public class MonthlyIncomeCommand extends Command {
    private final YearMonth month;

    /**
     * Constructs a {@code MonthlyIncomeCommand} with the specified target month.
     *
     * @param month The {@code YearMonth} representing the specific month to calculate total income for.
     */

    public MonthlyIncomeCommand(YearMonth month) {
        // Assert that the input is valid before the object is even created
        assert month != null : "Target month for income calculation should not be null.";
        this.month = month;
    }

    /**
     * Executes the monthly income command to aggregate fees from all active students
     * and display the grand total via the UI.
     *
     * @param students The {@code StudentList} containing the active students.
     * @param ui The {@code Ui} component used to display the aggregated result.
     * @throws TutorSwiftException If the calculation logic encounters an unexpected error.
     */
    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        // Assert that dependencies provided at runtime are valid
        assert students != null : "StudentList should not be null";
        assert ui != null : "Ui should not be null";

        int totalIncome = 0;

        // Loop through all active students
        for (seedu.tutorswift.Student student : students.getAllActive()) {
            assert student != null : "Student object in list should not be null";

            totalIncome += student.getFeeRecord().calculateMonthlyTotal(student.getLessons(), month);
        }

        ui.showMonthlyIncome(month, totalIncome);
    }
}
