package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;
import seedu.tutorswift.TutorSwiftException;

public class RemarkCommand extends Command {

    private final int index;
    private final String remark;

    public RemarkCommand(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        if (index <= 0 || index > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid student index.");
        }

        Student student = students.getActiveStudent(index - 1);
        student.setRemark(remark);

        ui.showRemarkSuccess(student);
    }
}
