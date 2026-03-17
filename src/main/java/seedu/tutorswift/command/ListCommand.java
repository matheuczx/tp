package seedu.tutorswift.command;

import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

/**
 * Represents a command to list all students.
 */
public class ListCommand extends Command {

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";

        ui.showStudentList(students);
    }
}
