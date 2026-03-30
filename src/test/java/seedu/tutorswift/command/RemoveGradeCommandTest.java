package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveGradeCommandTest {

    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() throws TutorSwiftException {
        students = new StudentList();
        ui = new Ui();

        Student alice = new Student("Alice", "Sec 2", "Math");
        alice.addGrade("Midterm", 85);
        alice.addGrade("Final", 90);
        students.addStudent(alice);
    }

    @Test
    public void execute_existingAssessment_gradeRemoved() throws TutorSwiftException {
        RemoveGradeCommand cmd = new RemoveGradeCommand(1, "Midterm");
        cmd.execute(students, ui);

        Student alice = students.getActiveStudent(0);
        // toString() should no longer contain Midterm
        assertTrue(!alice.toString().contains("Midterm"));
        // Final grade should still exist
        assertTrue(alice.toString().contains("Final"));
    }

    @Test
    public void execute_nonexistentAssessment_throwsException() {
        RemoveGradeCommand cmd = new RemoveGradeCommand(1, "Quiz");
        assertThrows(TutorSwiftException.class, () -> cmd.execute(students, ui));
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        RemoveGradeCommand cmd = new RemoveGradeCommand(99, "Midterm");
        assertThrows(TutorSwiftException.class, () -> cmd.execute(students, ui));
    }
}
