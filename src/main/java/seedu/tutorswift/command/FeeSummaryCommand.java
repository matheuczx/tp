package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;
import java.time.YearMonth;

/**
 * Represents a command to calculate and display the total fee generated
 * by a specific student for a given month.
 */

public class FeeSummaryCommand extends Command {
    private final int index;
    private final YearMonth month;

    /**
     * Constructs a {@code FeeSummaryCommand} with the specified student index and target month.
     *
     * @param studentIndex The 1-based index of the student in the active student list.
     * @param month The {@code YearMonth} representing the specific month to calculate fees for.
     */

    public FeeSummaryCommand(int index, YearMonth month) {
        assert index > 0 : "Index must be positive";
        assert month != null : "Month should not be null";
        this.index = index;
        this.month = month;
    }

    /**
     * Executes the fee summary command to calculate the student's fees and display them via the UI.
     *
     * @param students The {@code StudentList} containing the active students.
     * @param ui The {@code Ui} component used to display the result.
     * @throws TutorSwiftException If the index provided is out of bounds or calculation fails.
     */

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";

        if (index > students.getActiveSize() || index < 1) {
            throw new TutorSwiftException("StudentList does not have this index!");
        }

        Student student = students.getActiveStudent(index - 1);
        int total = student.getFeeRecord().calculateMonthlyTotal(student.getLessons(), month);
        ui.showFeeSummary(student, month, total);
    }
}
