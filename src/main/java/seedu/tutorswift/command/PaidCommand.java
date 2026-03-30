package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.time.YearMonth;

/**
 * Command to mark a month's payment as paid for a student.
 * Usage: paid INDEX [ym/YYYY-MM]
 */
public class PaidCommand extends Command {

    private final int index;
    private final YearMonth targetMonth;

    public PaidCommand(int index, YearMonth targetMonth) {
        this.index = index;
        this.targetMonth = targetMonth;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        if (index < 1 || index > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid index! Use 'list' to view active students.");
        }
        Student student = students.getActiveStudent(index - 1);
        student.markPaid(targetMonth);
        ui.showPaidSuccess(student, targetMonth);
    }
}
