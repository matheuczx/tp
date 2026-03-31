package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit test class for FeeCommand.
 * Tests setting per-lesson fee for a student.
 */
public class FeeCommandTest {

    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        students = new StudentList();
        ui = new Ui();
        students.addStudent(new Student("Alice", "Sec 2", "Math"));
    }

    @Test
    public void execute_validIndexAndFee_setsFeeCorrectly() throws TutorSwiftException {
        FeeCommand command = new FeeCommand(1, 60);
        command.execute(students, ui);
        assertEquals(60, students.getActiveStudent(0).getFeeRecord().getFeePerLesson());
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        FeeCommand command = new FeeCommand(99, 60);
        assertThrows(TutorSwiftException.class, () -> command.execute(students, ui));
    }

    @Test
    public void execute_zeroIndex_throwsException() {
        FeeCommand command = new FeeCommand(0, 60);
        assertThrows(TutorSwiftException.class, () -> command.execute(students, ui));
    }

    @Test
    public void execute_zeroFee_throwsException() {
        FeeCommand command = new FeeCommand(1, 0);
        assertThrows(TutorSwiftException.class, () -> command.execute(students, ui));
    }

}
