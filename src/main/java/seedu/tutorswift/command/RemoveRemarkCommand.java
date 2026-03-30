package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;
import seedu.tutorswift.TutorSwiftException;

public class RemoveRemarkCommand extends Command {

    private final int index;

    public RemoveRemarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        if (index <= 0 || index > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid student index.");
        }

        Student student = students.getActiveStudent(index - 1);
        student.clearRemark();

        ui.showEditSuccess(student);
    }
}
