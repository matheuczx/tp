package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;
import seedu.tutorswift.TutorSwiftException;

public class RemoveGradeCommand extends Command {

    private final int index;
    private final String assessment;

    public RemoveGradeCommand(int index, String assessment) {
        this.index = index;
        this.assessment = assessment;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        if (index <= 0 || index > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid student index.");
        }

        Student student = students.getActiveStudent(index - 1);
        student.removeGrade(assessment);

        ui.showEditSuccess(student);
    }
}
