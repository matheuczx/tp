package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

/**
 * Represents a command to delete an existing student by index.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a DeleteCommand with the given one-based student index.
     *
     * @param index The one-based index of the student to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the student at the specified index.
     *
     * @param students The current list of students.
     * @param ui       The UI handler for displaying messages.
     * @throws TutorSwiftException If the index is out of range.
     */
    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= students.getSize()) {
            throw new TutorSwiftException("Invalid! Please provide a valid index.");
        }
        Student deletedStudent = students.getStudent(zeroBasedIndex);
        students.deleteStudent(zeroBasedIndex);
        ui.showDeleteSuccess(deletedStudent);
    }
}
