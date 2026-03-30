package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeCommandTest {
    private StudentList studentList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
        ui = new Ui();
        Student student = new Student("John Doe", "Sec 3", "Math");
        studentList.addStudent(student);
    }

    @Test
    void execute_validIndex_addsGrade() throws TutorSwiftException {
        GradeCommand command = new GradeCommand(1, "Midterm", 90);

        command.execute(studentList, ui);

        Student student = studentList.getActiveStudent(0);
        assertEquals(1, student.toString().split("Grades:").length - 1 + 0); // confirms grade exists
        assertEquals(
                "John Doe | Sec 3 | Math | Grades: [Midterm: 90] "
                        + "| Fee: Not set | No payments recorded",
                student.toString()
        );
    }

    @Test
    void execute_invalidIndex_throwsException() {
        GradeCommand command = new GradeCommand(99, "Midterm", 90);
        assertThrows(TutorSwiftException.class, () -> command.execute(studentList, ui));
    }
}
