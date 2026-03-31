package seedu.tutorswift;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Lesson(DayOfWeek day, LocalTime startTime, LocalTime endTime) throws TutorSwiftException {
        assert day != null : "Day parameter should not be null.";
        assert startTime != null : "Start time parameter should not be null.";
        assert endTime != null : "End time parameter should not be null.";

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
        assert otherLesson != null : "Cannot check overlap against a null lesson.";
        assert otherLesson.startTime != null : "The other lesson's start time should not be null.";
        assert otherLesson.endTime != null : "The other lesson's end time should not be null.";

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

