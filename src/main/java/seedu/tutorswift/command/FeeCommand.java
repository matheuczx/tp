package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

/**
 * Command to set the per-lesson fee for a student.
 * Usage: fee INDEX f/AMOUNT
 */
public class FeeCommand extends Command {

    private final int index;
    private final int feePerLesson;

    public FeeCommand(int index, int feePerLesson) {
        this.index = index;
        this.feePerLesson = feePerLesson;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        if (index < 1 || index > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid index! Use 'list' to view active students.");
        }
        if (feePerLesson <= 0) {
            throw new TutorSwiftException("Fee per lesson must be a positive integer.");
        }
        Student student = students.getActiveStudent(index - 1);
        student.setFeePerLesson(feePerLesson);
        ui.showFeeSuccess(student, feePerLesson);
    }
}
