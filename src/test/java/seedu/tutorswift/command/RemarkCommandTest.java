package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemarkCommandTest {
    private StudentList studentList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
        ui = new Ui();
        Student student = new Student("Alice Tan", "P6", "English");
        studentList.addStudent(student);
    }

    @Test
    void execute_validIndex_addsRemark() throws TutorSwiftException {
        RemarkCommand command = new RemarkCommand(1, "Needs improvement on grammar");

        command.execute(studentList, ui);

        Student student = studentList.getActiveStudent(0);
        assertEquals("Needs improvement on grammar", student.getRemark());
        assertEquals("Alice Tan | P6 | English | Remark: Needs improvement on grammar", student.toString());
    }

    @Test
    void execute_invalidIndex_throwsException() {
        RemarkCommand command = new RemarkCommand(99, "Test remark");
        assertThrows(TutorSwiftException.class, () -> command.execute(studentList, ui));
    }
}
