package seedu.tutorswift.command;

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
        this.studentIndex= studentIndex;
        this.newName = name;
        this.newLevel = level;
        this.newSubject = subject;
    }

    @Override
    public void execute(StudentList students, Ui ui) {
        Student studentToEdit = students.getStudent(studentIndex - 1); // Subtract 1 to use 0-based indexing

        studentToEdit.editStudent(newName, newLevel, newSubject);

        ui.showEditSuccess(studentToEdit);
    }
}
