package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.*;
import java.time.YearMonth;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * JUnit test class for FeeSummaryCommand.
 * Ensures fee summary calculation targets correct student and handles boundary cases.
 */
public class FeeSummaryCommandTest {

    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() throws TutorSwiftException {
        students = new StudentList();
        ui = new Ui();

        // Add one student for testing
        Student s = new Student("Alice", "S2", "Math");
        s.getFeeRecord().setFeePerLesson(50);
        s.addLesson(new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0)));
        students.addStudent(s);
    }

    @Test
    public void execute_validIndex_executesSuccessfully() {
        FeeSummaryCommand command = new FeeSummaryCommand(1, YearMonth.of(2026, 4));
        assertDoesNotThrow(() -> command.execute(students, ui));
    }

    @Test
    public void execute_invalidIndex_throwsTutorSwiftException() {
        FeeSummaryCommand command = new FeeSummaryCommand(99, YearMonth.of(2026, 4));
        assertThrows(TutorSwiftException.class, () -> command.execute(students, ui));
    }

    @Test
    public void execute_zeroIndex_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new FeeSummaryCommand(0, YearMonth.of(2026, 4));
        });
    }
}