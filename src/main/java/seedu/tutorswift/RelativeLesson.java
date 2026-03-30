package seedu.tutorswift;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Represents a specific instance of a lesson on the tutor upcoming lesson schedule,
 * bundling the student, the lesson details, and its chronological distance from current day and time.
 */
public class RelativeLesson {
    private final Student student;
    private final Lesson lesson;
    private final int daysFromToday;

    public RelativeLesson(Student student, Lesson lesson, DayOfWeek today, LocalTime currentTime) {
        this.student = student;
        this.lesson = lesson;

        // Calculate how many days away this lesson is from today
        int diff = lesson.getDay().getValue() - today.getValue();
        int calculatedDays = diff < 0 ? diff + 7 : diff;

        // Push to next week if the lesson is today but has already started/passed
        if (calculatedDays == 0 && lesson.getStartTime().isBefore(currentTime)) {
            calculatedDays = 7;
        }

        this.daysFromToday = calculatedDays;
    }

    public Student getStudent() {
        return student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public int getDaysFromToday() {
        return daysFromToday;
    }
}
