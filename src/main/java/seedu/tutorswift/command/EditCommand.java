package seedu.tutorswift.command;

import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Student;

/**
 * Represents a command to edit student attributes within the student list.
 */
public class EditCommand extends Command {
    private final int studentIndex;
    private final String newName;
    private final String newLevel;
    private final String newSubject;

    public EditCommand(int studentIndex, String name, String level, String subject) {
        assert (name != null || level != null || subject != null) : "name, level or subject field should not be null.";
        this.studentIndex= studentIndex;
        this.newName = name;
        this.newLevel = level;
        this.newSubject = subject;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";

        // Check if student index is in the list
        if (studentIndex > students.getSize() || studentIndex < 1) {
            throw new TutorSwiftException("Index out of bounds!"
                    + " Use list command to view valid student index.");
        }

        Student studentToEdit = students.getStudent(studentIndex - 1); // Subtract 1 to use 0-based indexing
        assert studentToEdit != null : "Retrieved student should not be null";

        studentToEdit.editStudent(newName, newLevel, newSubject);

        ui.showEditSuccess(studentToEdit);
    }
}
