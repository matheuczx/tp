package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnpaidCommandTest {

    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() throws TutorSwiftException {
        students = new StudentList();
        ui = new Ui();
        students.addStudent(new Student("Alice", "Sec 2", "Math"));
        new PaidCommand(1, YearMonth.of(2026, 3)).execute(students, ui);
        new PaidCommand(1, YearMonth.of(2026, 4)).execute(students, ui);
    }

    @Test
    public void execute_validIndexAndMonth_removesFromPaidList() throws TutorSwiftException {
        YearMonth month = YearMonth.of(2026, 3);
        new UnpaidCommand(1, month).execute(students, ui);
        assertFalse(students.getActiveStudent(0).getFeeRecord().isPaidForMonth(month));
    }

    @Test
    public void execute_unpaidOneMonth_otherMonthsUnaffected() throws TutorSwiftException {
        new UnpaidCommand(1, YearMonth.of(2026, 3)).execute(students, ui);
        assertTrue(students.getActiveStudent(0).getFeeRecord().isPaidForMonth(YearMonth.of(2026, 4)));
    }

    @Test
    public void execute_unpaidMonthNotInList_noChangeToList() throws TutorSwiftException {
        int sizeBefore = students.getActiveStudent(0).getFeeRecord().getPaidMonths().size();
        new UnpaidCommand(1, YearMonth.of(2025, 1)).execute(students, ui);
        assertEquals(sizeBefore, students.getActiveStudent(0).getFeeRecord().getPaidMonths().size());
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        assertThrows(TutorSwiftException.class,
                () -> new UnpaidCommand(99, YearMonth.of(2026, 3)).execute(students, ui));
    }
}
