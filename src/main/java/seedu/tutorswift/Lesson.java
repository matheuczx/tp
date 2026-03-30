package seedu.tutorswift;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Lesson(DayOfWeek day, LocalTime startTime, LocalTime endTime) throws TutorSwiftException {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new TutorSwiftException("End time must be after start time.");
        }
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Checks if this lesson overlaps with another lesson.
     */
    public boolean isOverlapping(Lesson otherLesson) {
        if (this.day != otherLesson.day) {
            return false;
        }
        return this.startTime.isBefore(otherLesson.endTime) && otherLesson.startTime.isBefore(this.endTime);
    }

    @Override
    public String toString() {
        return day + " " + startTime + " to " + endTime;
    }
}

