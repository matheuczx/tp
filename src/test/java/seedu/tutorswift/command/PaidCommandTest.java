package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaidCommandTest {

    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        students = new StudentList();
        ui = new Ui();
        students.addStudent(new Student("Alice", "Sec 2", "Math"));
    }

    @Test
    public void execute_validIndexAndMonth_marksAsPaid() throws TutorSwiftException {
        YearMonth month = YearMonth.of(2026, 3);
        new PaidCommand(1, month).execute(students, ui);
        assertTrue(students.getActiveStudent(0).getFeeRecord().isPaidForMonth(month));
    }

    @Test
    public void execute_markMultipleMonths_allRecorded() throws TutorSwiftException {
        YearMonth jan = YearMonth.of(2026, 1);
        YearMonth feb = YearMonth.of(2026, 2);
        new PaidCommand(1, jan).execute(students, ui);
        new PaidCommand(1, feb).execute(students, ui);
        assertEquals(2, students.getActiveStudent(0).getFeeRecord().getPaidMonths().size());
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        assertThrows(TutorSwiftException.class,
                () -> new PaidCommand(99, YearMonth.of(2026, 3)).execute(students, ui));
    }

    @Test
    public void execute_emptyStudentList_throwsException() {
        assertThrows(TutorSwiftException.class,
                () -> new PaidCommand(1, YearMonth.of(2026, 3)).execute(new StudentList(), ui));
    }
}
