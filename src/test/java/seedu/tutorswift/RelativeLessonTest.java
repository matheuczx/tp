package seedu.tutorswift;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelativeLessonTest {

    @Test
    public void constructor_lessonLaterThisWeek_calculatesCorrectDays() throws TutorSwiftException {
        Student student = new Student("Alice", "Primary 1", "Math");
        Lesson lesson = new Lesson(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        RelativeLesson relativeLesson = new RelativeLesson(student, lesson, DayOfWeek.MONDAY, LocalTime.of(10, 0));

        assertEquals(2, relativeLesson.getDaysFromToday());
        assertEquals(student, relativeLesson.getStudent());
        assertEquals(lesson, relativeLesson.getLesson());
    }

    @Test
    public void constructor_lessonNextWeek_wrapsAroundAndCalculatesCorrectDays() throws TutorSwiftException {
        Student student = new Student("Bob", "Secondary 3", "Physics");
        Lesson lesson = new Lesson(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        RelativeLesson relativeLesson = new RelativeLesson(student, lesson, DayOfWeek.FRIDAY, LocalTime.of(10, 0));

        assertEquals(4, relativeLesson.getDaysFromToday());
    }

    @Test
    public void constructor_lessonTodayButLater_returnsZeroDays() throws TutorSwiftException {
        Student student = new Student("Charlie", "JC 1", "Chemistry");
        Lesson lesson = new Lesson(DayOfWeek.THURSDAY, LocalTime.of(18, 0), LocalTime.of(20, 0));

        RelativeLesson relativeLesson = new RelativeLesson(student, lesson, DayOfWeek.THURSDAY, LocalTime.of(14, 0));

        assertEquals(0, relativeLesson.getDaysFromToday());
    }

    @Test
    public void constructor_lessonTodayButAlreadyPassed_pushesToNextWeek() throws TutorSwiftException {
        Student student = new Student("David", "Primary 5", "English");
        Lesson lesson = new Lesson(DayOfWeek.THURSDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        RelativeLesson relativeLesson = new RelativeLesson(student, lesson, DayOfWeek.THURSDAY, LocalTime.of(15, 0));

        assertEquals(7, relativeLesson.getDaysFromToday());
    }

    @Test
    public void constructor_lessonTodayExactlyAtCurrentTime_returnsZeroDays() throws TutorSwiftException {
        Student student = new Student("Eve", "Primary 6", "Science");
        Lesson lesson = new Lesson(DayOfWeek.THURSDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        RelativeLesson relativeLesson = new RelativeLesson(student, lesson, DayOfWeek.THURSDAY, LocalTime.of(14, 0));

        assertEquals(0, relativeLesson.getDaysFromToday());
    }
}
