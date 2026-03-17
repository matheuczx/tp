package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;

/**
 * Represents a command to add a new student to the student list.
 */
public class AddCommand extends Command {
    private final Student studentToAdd;

    /**
     * Constructs an {@code AddCommand} with the specified student.
     *
     * @param student The student to be added to the list.
     */
    public AddCommand(Student student) {
        assert student != null : "AddCommand initialized with null student";
        this.studentToAdd = student;
    }

    /**
     * Executes the add command by adding the student to the student list
     * and displaying a success message.
     *
     * @param students The list of students where the new student will be added.
     * @param ui The user interface used to show the confirmation message.
     */
    @Override
    public void execute(StudentList students, Ui ui) {
        assert students != null : "StudentList should not be null";
        assert ui != null : "Ui should not be null";
        int initialSize = students.getSize();
        students.addStudent(studentToAdd);

        assert students.getSize() == initialSize + 1 : "List size should increase by 1";
        ui.showAddSuccess(studentToAdd, students.getSize());
    }
}
