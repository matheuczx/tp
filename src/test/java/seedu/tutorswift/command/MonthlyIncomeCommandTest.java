package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;
import seedu.tutorswift.Lesson;
import java.time.YearMonth;
import java.time.DayOfWeek;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonthlyIncomeCommandTest {
    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        students = new StudentList();
        ui = new Ui();
    }

    @Test
    public void execute_emptyStudentList_returnsZero() throws TutorSwiftException {
        // Equivalence Partitioning: Test the "Empty" boundary
        MonthlyIncomeCommand command = new MonthlyIncomeCommand(YearMonth.of(2026, 4));
        command.execute(students, ui);

        int total = 0;
        for (Student s : students.getAllActive()) {
            total += s.getFeeRecord().calculateMonthlyTotal(s.getLessons(), YearMonth.of(2026, 4));
        }
        assertEquals(0, total, "Income should be 0 when no students exist");
    }

    @Test
    public void execute_studentWithNoLessons_returnsZero() throws TutorSwiftException {
        // Boundary: Student exists but contributes no revenue
        Student s = new Student("Bob", "S4", "Math");
        s.getFeeRecord().setFeePerLesson(60);
        students.addStudent(s);

        MonthlyIncomeCommand command = new MonthlyIncomeCommand(YearMonth.of(2026, 4));
        command.execute(students, ui);

        int total = 0;
        for (Student st : students.getAllActive()) {
            total += st.getFeeRecord().calculateMonthlyTotal(st.getLessons(), YearMonth.of(2026, 4));
        }
        assertEquals(0, total, "Income should be 0 when student has no lessons");
    }

    @Test
    public void execute_multipleStudents_handlesDifferentFeesCorrectly() throws TutorSwiftException {
        // Full Integration: Two students, different fees, mixed schedules
        Student s1 = new Student("Bob", "S4", "Math");
        s1.getFeeRecord().setFeePerLesson(100);
        s1.addLesson(new Lesson(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));

        Student s2 = new Student("Charlie", "P5", "Science");
        s2.getFeeRecord().setFeePerLesson(10);
        s2.addLesson(new Lesson(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));

        students.addStudent(s1);
        students.addStudent(s2);

        YearMonth month = YearMonth.of(2026, 4);
        int total = 0;
        for (Student s : students.getAllActive()) {
            total += s.getFeeRecord().calculateMonthlyTotal(s.getLessons(), month);
        }
        // April 2026 has 4 Mondays. (4*100) + (4*10) = 440
        assertEquals(440, total, "Calculation should correctly aggregate different fees");
    }
}
