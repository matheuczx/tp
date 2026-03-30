package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveRemarkCommandTest {

    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() throws TutorSwiftException {
        students = new StudentList();
        ui = new Ui();

        Student alice = new Student("Alice", "Sec 2", "Math");
        alice.setRemark("Needs improvement in problem-solving.");
        students.addStudent(alice);
    }

    @Test
    public void execute_existingRemark_remarkCleared() throws TutorSwiftException {
        RemoveRemarkCommand cmd = new RemoveRemarkCommand(1);
        cmd.execute(students, ui);

        Student alice = students.getActiveStudent(0);
        assertTrue(!alice.toString().contains("Needs improvement"));
    }

    @Test
    public void execute_noRemark_throwsException() throws TutorSwiftException {
        // Clear remark first
        students.getActiveStudent(0).clearRemark();

        RemoveRemarkCommand cmd = new RemoveRemarkCommand(1);
        assertThrows(TutorSwiftException.class, () -> cmd.execute(students, ui));
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        RemoveRemarkCommand cmd = new RemoveRemarkCommand(0);
        assertThrows(TutorSwiftException.class, () -> cmd.execute(students, ui));
    }
}
