package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.time.YearMonth;

/**
 * Command to mark a month's payment as unpaid for a student.
 * Usage: unpaid INDEX [ym/YYYY-MM]
 */
public class UnpaidCommand extends Command {
    private final int index;
    private final YearMonth targetMonth;

    public UnpaidCommand(int index, YearMonth targetMonth) {
        this.index = index;
        this.targetMonth = targetMonth;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        if (index < 1 || index > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid index! Use 'list' to view active students.");
        }
        Student student = students.getActiveStudent(index - 1);
        student.markUnpaid(targetMonth);
        ui.showUnpaidSuccess(student, targetMonth);
    }
}
