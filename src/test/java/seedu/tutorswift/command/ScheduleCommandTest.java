package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Lesson;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduleCommandTest {
    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        students = new StudentList();
        ui = new Ui();
    }

    @Test
    public void execute_validStudent_schedulesLessonSuccessfully() throws TutorSwiftException {
        String testName = "John Doe";
        Student testStudent = new Student(testName, "Primary 1", "Math");
        students.addStudent(testStudent);

        DayOfWeek testDay = DayOfWeek.MONDAY;
        LocalTime startTime = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(16, 0);
        ScheduleCommand command = new ScheduleCommand(testName, testDay, startTime, endTime);

        command.execute(students, ui);
        Lesson scheduledLesson = testStudent.getLessons().get(0);

        assertEquals(1, testStudent.getLessons().size());
        assertEquals(testDay, scheduledLesson.getDay());
        assertEquals(startTime, scheduledLesson.getStartTime());
        assertEquals(endTime, scheduledLesson.getEndTime());
    }

    @Test
    public void execute_caseInsensitiveName_schedulesLessonSuccessfully() throws TutorSwiftException {
        String testName = "Mickey Mouse";
        Student testStudent = new Student(testName, "Secondary 3", "Physics");
        students.addStudent(testStudent);

        DayOfWeek testDay = DayOfWeek.TUESDAY;
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 0);
        ScheduleCommand command = new ScheduleCommand("mickey mouse", testDay, startTime, endTime);

        command.execute(students, ui);
        Lesson scheduledLesson = testStudent.getLessons().get(0);

        assertEquals(1, testStudent.getLessons().size());
        assertEquals(testDay, scheduledLesson.getDay());
        assertEquals(startTime, scheduledLesson.getStartTime());
        assertEquals(endTime, scheduledLesson.getEndTime());
    }

    @Test
    public void execute_studentNotFound_throwsTutorSwiftException() throws TutorSwiftException {
        Student testStudent = new Student("Charlie", "JC 1", "Chemistry");
        students.addStudent(testStudent);

        String nonExistingStudentName = "David";
        DayOfWeek testDay = DayOfWeek.WEDNESDAY;
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(11, 0);
        ScheduleCommand command = new ScheduleCommand(nonExistingStudentName, testDay, startTime, endTime);

        TutorSwiftException exception = assertThrows(TutorSwiftException.class, () -> {
            command.execute(students, ui);
        });

        assertEquals("Student '" + nonExistingStudentName + "' not found.", exception.getMessage());
    }

    @Test
    public void execute_overlappingLesson_throwsTutorSwiftException() throws TutorSwiftException {
        String testName = "Eve";
        Student testStudent = new Student(testName, "Primary 5", "English");
        students.addStudent(testStudent);

        Lesson existingLesson = new Lesson(DayOfWeek.THURSDAY, LocalTime.of(15, 0), LocalTime.of(17, 0));
        testStudent.addLesson(existingLesson);

        DayOfWeek testDay = DayOfWeek.THURSDAY;
        LocalTime startTime = LocalTime.of(16, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        ScheduleCommand command = new ScheduleCommand(testName, testDay, startTime, endTime);

        assertThrows(TutorSwiftException.class, () -> {
            command.execute(students, ui);
        });
    }
}
