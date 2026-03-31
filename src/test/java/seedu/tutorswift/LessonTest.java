package seedu.tutorswift;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LessonTest {

    @Test
    public void lessonConstructor_validTime_createsLessonSuccessfully() throws TutorSwiftException {
        Lesson lesson = new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        assertEquals(DayOfWeek.MONDAY, lesson.getDay());
        assertEquals(LocalTime.of(14, 0), lesson.getStartTime());
        assertEquals(LocalTime.of(16, 0), lesson.getEndTime());
    }

    @Test
    public void lessonConstructor_startEqualsEnd_throwsTutorSwiftException() {
        TutorSwiftException exception = assertThrows(TutorSwiftException.class, () -> {
            new Lesson(DayOfWeek.TUESDAY, LocalTime.of(15, 0), LocalTime.of(15, 0));
        });

        assertEquals("End time must be after start time.", exception.getMessage());
    }

    @Test
    public void lessonConstructor_startAfterEnd_throwsTutorSwiftException() {
        TutorSwiftException exception = assertThrows(TutorSwiftException.class, () -> {
            new Lesson(DayOfWeek.WEDNESDAY, LocalTime.of(16, 0), LocalTime.of(14, 0));
        });

        assertEquals("End time must be after start time.", exception.getMessage());
    }

    @Test
    public void isOverlapping_differentDays_returnsFalse() throws TutorSwiftException {
        Lesson lesson1 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));
        Lesson lesson2 = new Lesson(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        assertFalse(lesson1.isOverlapping(lesson2));
    }

    @Test
    public void isOverlapping_sameDayNoOverlap_returnsFalse() throws TutorSwiftException {
        Lesson lesson1 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        Lesson lesson2 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        assertFalse(lesson1.isOverlapping(lesson2));
    }

    @Test
    public void isOverlapping_sameDayAdjacentTimes_returnsFalse() throws TutorSwiftException {
        // One lesson ends exactly when the other begins
        Lesson lesson1 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(14, 0));
        Lesson lesson2 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        assertFalse(lesson1.isOverlapping(lesson2));
    }

    @Test
    public void isOverlapping_sameDayPartialOverlap_returnsTrue() throws TutorSwiftException {
        Lesson lesson1 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(13, 0), LocalTime.of(15, 0));
        Lesson lesson2 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        assertTrue(lesson1.isOverlapping(lesson2));
    }

    @Test
    public void isOverlapping_sameDayExactOverlap_returnsTrue() throws TutorSwiftException {
        Lesson lesson1 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));
        Lesson lesson2 = new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));

        assertTrue(lesson1.isOverlapping(lesson2));
    }
}
