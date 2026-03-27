package seedu.tutorswift.command;

import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {

    @Test
    public void execute_validIndex_deletesCorrectStudent() throws TutorSwiftException {
        StudentList students = new StudentList();
        students.addStudent(new Student("Alex", "Sec 1", "Math"));
        students.addStudent(new Student("Brenda", "Pri 6", "English"));
        Ui ui = new Ui();

        DeleteCommand command = new DeleteCommand(1);
        command.execute(students, ui);

        assertEquals(1, students.getSize());
        assertEquals("Brenda", students.getStudent(0).getName());
    }

    @Test
    public void execute_emptyStudentList_throwsTutorSwiftException() {
        StudentList students = new StudentList();
        Ui ui = new Ui();

        DeleteCommand command = new DeleteCommand(1);
        assertThrows(TutorSwiftException.class, () -> command.execute(students, ui));
    }

    @Test
    public void execute_indexOutOfBounds_throwsTutorSwiftException() {
        StudentList students = new StudentList();
        students.addStudent(new Student("Alex", "Sec 1", "Math"));
        Ui ui = new Ui();

        DeleteCommand command = new DeleteCommand(99);
        assertThrows(TutorSwiftException.class, () -> command.execute(students, ui));
    }
}
