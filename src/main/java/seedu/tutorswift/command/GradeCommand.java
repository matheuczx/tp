package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;
import seedu.tutorswift.TutorSwiftException;

public class GradeCommand extends Command {

    private final int index;
    private final String assessment;
    private final int score;

    public GradeCommand(int index, String assessment, int score) {
        this.index = index;
        this.assessment = assessment;
        this.score = score;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        if (index <= 0 || index > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid student index.");
        }

        Student student = students.getActiveStudent(index - 1);
        student.addGrade(assessment, score);

        ui.showEditSuccess(student); // reuse existing UI
    }
}
